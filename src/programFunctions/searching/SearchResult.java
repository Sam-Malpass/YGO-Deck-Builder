/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.searching;
public class SearchResult {
    /**
     * cardName holds the name of the card that has been found
     */
    private String cardName;
    /**
     * containerName holds the name of the container the card resides in
     */
    private String containerName;
    /**
     * setID holds the card's setID to ensure that should the card be moved or removed
     * it is the right object
     */
    private String setID;
    /**
     * Constructor without arguments
     * <p>
     *     Creates a null object
     * </p>
     */
    SearchResult() {
        /*Set the cardName*/
        cardName = null;
        /*Set the containerName*/
        containerName = null;
        /*Set the setID*/
        setID = null;
    }
    /**
     * Constructor with arguments
     * <p>
     *     Creates a search result object
     * </p>
     * @param A is the cardName
     * @param B is the containerName
     * @param C is the setID
     */
    public SearchResult(String A, String B, String C) {
        /*Set the cardName*/
        cardName = A;
        /*Set the containerName*/
        containerName = B;
        /*Set the setID*/
        setID = C;
    }
    /**
     * Function definition for getCardName()
     * <P>
     *     Returns the cardName for a given object
     * </P>
     * @return the cardName
     */
    public String getCardName() {
        /*Return the cardName*/
        return cardName;
    }
    /**
     * Function definition for getContainerName()
     * <P>
     *     Return the containerName
     * </P>
     * @return the containerName
     */
    public String getContainerName() {
        /*Return the containerName*/
        return containerName;
    }
    /**
     * Function definition for setCardName()
     * <P>
     *     Set the cardName to the given String
     * </P>
     * @param cardName is the new name
     */
    public void setCardName(String cardName) {
        /*Set the cardName*/
        this.cardName = cardName;
    }
    /**
     * Function definition for getSetID()
     * <P>
     *     Return the setID
     * </P>
     * @return the SetID
     */
    public String getSetID() {
        /*Return the SetID*/
        return setID;
    }
    /**
     * Function definition for setContainerName()
     * <p>
     *     Sets the containerName to the passed String
     * </p>
     * @param containerName is the new containerName
     */
    public void setContainerName(String containerName) {
        /*Set the containerName*/
        this.containerName = containerName;
    }
    /**
     * Function definition for setSetID()
     * <P>
     *     Set the setID to the passed String
     * </P>
     * @param setID is the new setID
     */
    public void setSetID(String setID) {
        /*Set the setID*/
        this.setID = setID;
    }
    /**
     * Function definition for outputResult()
     * <P>
     *     Returns a constructed string
     * </P>
     * @return the result
     */
    public String outputResult() {
        /*Return the String*/
        return cardName + " (" + setID + ") found in " + containerName;
    }
}
