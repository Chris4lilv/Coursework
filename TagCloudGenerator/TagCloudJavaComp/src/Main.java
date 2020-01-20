import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 *
 * @author Erik Gustafson, Nuo Xu
 */
public final class Main {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Main() {
    }

    /**
     * static variable for the 'slope' of the font size. Used to calculate the
     * font size for each word pair.
     */
    private static double slope = -1;
    /**
     * Separators for a "SINGLE POINT OF CONTROL!".
     */
    private static String separators = " 0123456789\'.,!_*?\":-()[]{}<>/;\\\n\t\r";

    /**
     * Parse the given text file.
     *
     * @param filepath
     *            of the file
     * @param numEntries
     *            the number of words user want in the tag cloud
     * @return the list of tag cloud
     */
    private static List<Entry<String, Integer>> parseTextFile(String filepath,
            int numEntries) {
        //map to track word occurrence pairs
        Map<String, Integer> map = new TreeMap<String, Integer>();
        List<Entry<String, Integer>> pairs = new ArrayList<>();
        String s = "";
        try {
            BufferedReader input = new BufferedReader(new FileReader(filepath));
            boolean previousSep = false;
            int characterByte = input.read();
            while (characterByte != -1) {
                //cast the unicode character to a char
                char c = (char) characterByte;
                if (separators.indexOf(c) != -1) {
                    previousSep = true;
                } else {
                    if (previousSep && !s.equals("")) {
                        s = s.toLowerCase();
                        if (map.containsKey(s)) {
                            map.replace(s, map.get(s) + 1);
                        } else {
                            map.put(s, 1);
                        }
                        s = "" + c;
                        previousSep = false;
                    } else {
                        s = s + c;
                        previousSep = false;
                    }
                }
                characterByte = input.read();
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
        }
        //add the last string from the file to the list
        s = s.toLowerCase();
        if (map.containsKey(s)) {
            map.replace(s, map.get(s) + 1);
        } else {
            map.put(s, 1);
        }
        //add everything from the map to the arraylist
        System.out.println(map);
        pairs.addAll(map.entrySet());
        //sort in order of occurrences
        Collections.sort(pairs, new NumComparator());
        //take the number of entries the user requested
        pairs = pairs.subList(0, numEntries);
        //calculate the slope to determine FONT SIZEs
        slope = (48.0 - 11.0) / (pairs.get(0).getValue()
                - pairs.get(pairs.size() - 1).getValue());
        //sort the sublist in alphabetical order
        Collections.sort(pairs, new WordComparator());
        return pairs;
    }

    /**
     * output the html file header.
     *
     * @param out
     *            the print writer to print to
     * @param filepath
     *            the file path of the input file being parsed.
     * @param numWords
     *            the number of words being printed to the output file.
     * @requires out is not closed
     */
    private static void outputHtmlHeader(PrintWriter out, String filepath,
            int numWords) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Top " + numWords + " words in " + filepath
                + "</title>");
        out.println("<link href=\"http://web.cse.ohio-state.edu/software/"
                + "2231/web-sw2/assignments/projects/tag-cloud-generator"
                + "/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Top " + numWords + " words in " + filepath + "</h2>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");
    }

    /**
     * output the html line for a word pair.
     *
     * @param out
     *            the print writer to print to
     * @param pair
     *            the word occurrence pair to output.
     * @requires out is not closed
     */
    private static void outputWordPair(PrintWriter out,
            Map.Entry<String, Integer> pair) {
        int font = (int) (pair.getValue() * slope) + 11;
        //account for possible rounding errors that could give an invalid font size
        if (font > 48) {
            font = 48;
        }
        if (font < 11) {
            font = 11;
        }
        out.println("<span style=\"cursor:default\" class=\"f" + font
                + "\" title=\"count: " + pair.getValue() + "\">" + pair.getKey()
                + "</span>");
    }

    /**
     * output the html file footer.
     *
     * @param out
     *            the print writer to print to
     * @requires out is not closed
     */
    private static void outputHtmlFooter(PrintWriter out) {
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Comparator to sort the entry pairs based on decreasing word occurrence.
     *
     */
    static class NumComparator
            implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }

    }

    /**
     * Comparator to sort the entry pairs based on alphabetical order, ignoring
     * case.
     *
     */
    static class WordComparator
            implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Entry<String, Integer> o1,
                Entry<String, Integer> o2) {
            return o1.getKey().compareToIgnoreCase(o2.getKey());
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        Scanner incmd = new Scanner(System.in);
        boolean fileExists = false;
        //needs to have an init value, will not be -1 after input loop
        int numwords = -1;
        //needs to have an init value, will not be "" after input loop
        String outfileName = "";
        //needs to have an init value, will not be "" after input loop
        String filepath = "";
        //loop until valid input is received from the user
        while (!fileExists) {
            System.out.println("Welcome to Tag Cloud Generator!");
            System.out.print("Enter an absolute or relative file path: ");
            filepath = incmd.nextLine();
            System.out
                    .print("How many words would you like in the word cloud: ");
            numwords = incmd.nextInt();
            System.out.print("Enter the name of the output file: ");
            outfileName = incmd.next();
            //validate the users file input
            File file = new File(filepath);
            fileExists = file.exists();
            if (!fileExists) {
                System.out.println("This is not a valid input file path");
            }
        }
        incmd.close();
        List<Entry<String, Integer>> words = parseTextFile(filepath, numwords);
        try {
            PrintWriter output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outfileName)));
            outputHtmlHeader(output, filepath, numwords);
            for (int i = 0; i < words.size(); i++) {
                outputWordPair(output, words.get(i));
            }
            outputHtmlFooter(output);
            output.close();
        } catch (IOException e) {
            //print the error to the console.
            e.printStackTrace();
            //tell the user an error happened outputting to the file.
            System.err.println("Error outputting to file.");
        }

    }
}
