/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
public class Utils {
    /**
     * sorter holds the Sorters object
     */
    private Sorters sorter;
    /**
     * filter holds the Filters object
     */
    private Filters filter;
    /**
     * outputter holds the ListOutputter object
     */
    private Outputters outputter;
    /**
     * fileCollector holds the FileCollector object
     */
    private FileCollector fileCollector;
    /**
     * fileHandler holds the FileHandler object
     */
    private FileHandler fileHandler;
    /**
     * authenticator holds the PasswordAuthenticator object
     */
    private PasswordAuthenticator authenticator;
    /**
     * Constructor with no arguments
     * <p>
     *     Creates the appropriate objects
     * </p>
     */
    public Utils() {
        sorter = new Sorters();
        filter = new Filters();
        outputter = new Outputters();
        fileCollector = new FileCollector();
        fileHandler = new FileHandler();
        authenticator = new PasswordAuthenticator();
    }
    /**
     * Function definition for getFilter()
     * <p>
     *     Returns the Filters object
     * </p>
     * @return the Filters object
     */
    public Filters getFilter() {
        return filter;
    }
    /**
     * Function definition for getSorter()
     * <p>
     *     Returns the Sorters object
     * </p>
     * @return the Sorters object
     */
    public Sorters getSorter() {
        return sorter;
    }
    /**
     * Function definition for getOutputter()
     * <p>
     *     Returns the ListOutputter object
     * </p>
     * @return the ListOutputter object
     */
    public Outputters getOutputter() {
        return outputter;
    }
    /**
     * Function definition for getFileCollector()
     * <p>
     *     Return fileCollector
     * </p>
     * @return fileCollector
     */
    public FileCollector getFileCollector() {
        return fileCollector;
    }
    /**
     * Function definition for getFileHandler()
     * <p>
     *     Return fileHandler
     * </p>
     * @return fileHandler
     */
    public FileHandler getFileHandler() {
        return fileHandler;
    }
    /**
     * Function definition for getAuthenticator()
     * <p>
     *     Return authenticator
     * </p>
     * @return authenticator
     */
    public PasswordAuthenticator getAuthenticator() {
        return authenticator;
    }
    /**
     * Function definition for setFilter()
     * <p>
     *     Sets the Filters object to the passed object
     * </p>
     * @param filter is the new Filters object
     */
    public void setFilter(Filters filter) {
        this.filter = filter;
    }
    /**
     * Function definition for setSorters()
     * <p>
     *     Sets the Sorters object to the passed object
     * </p>
     * @param sorter is the new Sorters object
     */
    public void setSorter(Sorters sorter) {
        this.sorter = sorter;
    }
    /**
     * Function definition for setFileCollector()
     * <p>
     *     Sets the fileCollector to the passed object
     * </p>
     * @param fileCollector is the new FileCollector to use
     */
    public void setFileCollector(FileCollector fileCollector) {
        this.fileCollector = fileCollector;
    }
    /**
     * Function definition for setFileHandler()
     * <p>
     *     Sets the fileHandler to the passed object
     * </p>
     * @param fileHandler is the new FileHandler to use
     */
    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    /**
     * Function definition for setAuthenticator()
     * <p>
     *     Set the authenticator to the passed object
     * </p>
     * @param authenticator is the PasswordAuthenticator to use
     */
    public void setAuthenticator(PasswordAuthenticator authenticator) {
        this.authenticator = authenticator;
    }
    /**
     * Function definition for setOutputter()
     * <p>
     *     Sets the ListOutputter object to the passed object
     * </p>
     * @param outputter is the new ListOutputter object
     */
    public void setOutputter(Outputters outputter) {
        this.outputter = outputter;
    }
}
