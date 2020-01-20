import java.io.File;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Nuo Xu
 *
 */
public final class GlossaryTerm {

    /**
     * The name of GlossaryTerm.
     */
    private String name;

    /**
     * The definition of GlossaryTerm.
     */
    private String definition;

    /**
     * Constructor of GlossaryTerm with no parameter.
     */
    public GlossaryTerm() {
        super();
    }

    /**
     * Constructor of GlossaryTerm with name and definition.
     *
     * @param name
     *            name of the GlossaryTerm
     * @param definition
     *            definition of the GlossaryTerm
     */
    public GlossaryTerm(String name, String definition) {
        super();
        this.name = name;
        this.definition = definition;
    }

    /**
     * Get the name of this GlossaryTerm.
     *
     * @return name of this GlossaryTerm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set {@code name} to be the name of this GlossaryTerm.
     *
     * @param name
     *            set to be the name of this GlossaryTerm
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the definition of this GlossaryTerm.
     *
     * @return the definition of this GlossaryTerm
     */
    public String getDefinition() {
        return this.definition;
    }

    /**
     * Set {@code definition} to be the definition of this GlossaryTerm.
     *
     * @param definition
     *            set to be the definition of this GlossaryTerm
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * Generate a HTML page for this GlossaryTerm.
     *
     * @param path
     *            intended folder to save this file
     * @return HTML file for this GlossaryTerm
     */
    public File generateTermPage(String path) {
        File termPage = new File(path + "/" + this.getName() + ".html");
        SimpleWriter out = new SimpleWriter1L(termPage.toString());

        out.println("<html>");
        out.println("<head>");
        out.println("<hr/>");
        out.println("<title>" + this.getName() + "</title>");
        out.println("</head>");
        out.println("<p>\n<font color=\"red\"><b><i><strong>" + this.getName()
                + "</strong></i></b></font>\n</p>");

        out.println("<body>");
        out.println("<p>" + this.getDefinition() + "</p>");
        out.println("<hr/>");
        out.println("<p>Return to <a href=index.html>index</a></p>");

        out.println("</body>");
        out.println("</html>");

        out.close();

        return termPage;
    }

}
