import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Nuo Xu
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Generate the index page.
     *
     * @param folderName
     *            name of the intended folder
     * @param words
     *            queue that contains words
     */
    private static void generateIndexPage(String folderName,
            Queue<GlossaryTerm> words) {
        SimpleWriter out = new SimpleWriter1L(folderName + "/index.html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Index</title>");
        out.println("</head>");
        out.println("<p><strong>Glossary</strong></p>");
        out.println("<hr/>");

        out.println("<body>");
        out.println("<p><strong>Index</strong></p>");
        out.println("<ul>");
        for (GlossaryTerm term : words) {
            out.println("<li><a href=" + term.getName() + ".html>"
                    + term.getName() + "</a></li>");
        }
        out.println("</body>");
        out.println("</html>");

    }

    /**
     * Process the words in the inputFile and store them in a queue.
     *
     * @param inputFile
     *            the file stores words
     * @return a queue contains the words included in the {@code inputFile}
     */
    private static Queue<GlossaryTerm> processWords(String inputFile) {
        SimpleReader fileReader = new SimpleReader1L(inputFile);
        Queue<GlossaryTerm> words = new Queue1L<>();

        while (!fileReader.atEOS()) {
            GlossaryTerm term = new GlossaryTerm();
            String name = fileReader.nextLine();
            String definition = "";
            String str = "";
            //Get definition from the input file
            while (!(str = fileReader.nextLine()).isEmpty()) {
                definition = definition + str;
            }
            //Set name and definition for the term
            term.setName(name);
            term.setDefinition(definition);
            words.enqueue(term);
        }
        Comparator<GlossaryTerm> comparator = new GlossaryTermLT();
        words.sort(comparator);

        fileReader.close();
        return words;
    }

    /**
     * Customized GlossaryTerm comparator to sort the queue.
     *
     */
    private static class GlossaryTermLT implements Comparator<GlossaryTerm> {
        @Override
        public int compare(GlossaryTerm o1, GlossaryTerm o2) {
            return (o1.getName()).compareTo(o2.getName());
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the input file: ");
        String inputFile = in.nextLine();
        Queue<GlossaryTerm> glossary = processWords(inputFile);
        out.print("Enter the folder of output: ");
        String folder = in.nextLine();
        for (GlossaryTerm term : glossary) {
            term.generateTermPage(folder);
        }
        generateIndexPage(folder, glossary);

        in.close();
        out.close();
    }

}
