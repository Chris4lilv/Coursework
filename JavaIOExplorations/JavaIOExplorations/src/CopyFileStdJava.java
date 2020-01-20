import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program to copy a text file into another file.
 *
 * @author Nuo Xu
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {
        BufferedReader input;
        PrintWriter output;
        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }
        try {
            String s = input.readLine();
            output.println(s);
            while (s != null) {
                s = input.readLine();
                output.println(s);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }

    }

}
