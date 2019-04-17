/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.banList;
public class LimitedCard {
    /**
     * cardName holds the name of a given card
     */
    private String cardName;
    /**
     * numberOfCopies holds the number of copies that it is limited to
     */
    private int numberOfCopies;
    /**
     * status determines whether it is forbidden, limited or semi-limited
     */
    private String status;
    /**
     * Constructor with no arguments
     * <p>
     *     Creates an object with default values
     * </p>
     */
    public LimitedCard() {
        cardName = null;
        numberOfCopies = 0;
        status = null;
    }
    /**
     * Constructor with argmuents
     * <p>
     *     Creates an object with passed values
     * </p>
     * @param name is the name of the card that is being added
     * @param number is the number of copies allowed
     */
    public LimitedCard(String name, int number) {
        cardName = name;
        numberOfCopies = number;
        switch (numberOfCopies) {
            case 0:
                status = "FORBIDDEN";
                break;
            case 1:
                status = "LIMITED";
                break;
            default:
                status = "SEMI-LIMITED";
                break;
        }
    }
    /**
     * Function definition for getCardName()
     * <p>
     *     Return the cardName for a given object
     * </p>
     * @return the cardName
     */
    public String getCardName() {
        /*Return the cardName*/
        return cardName;
    }
    /**
     * Function definition for getNumberOfCopies()
     * <P>
     *     Returns the numberOfCopies
     * </P>
     * @return the numberOfCopies
     */
    public int getNumberOfCopies() {
        /*Return the numberOfCopies*/
        return numberOfCopies;
    }
    /**
     * Function definition for getStatus()
     * <P>
     *      Return the status
     * </P>
     * @return the status
     */
    public String getStatus() {
        /*Return the status*/
        return status;
    }
    /**
     * Function definition for setCardName()
     * <p>
     *     Sets the cardName to a passed variable
     * </p>
     * @param cardName is the new cardName
     */
    public void setCardName(String cardName) {
        /*Set cardName to the passed name*/
        this.cardName = cardName;
    }
    /**
     * Function definition for setNumberOfCopies()
     * <P>
     *     Sets the numberOfCopies to a passed value
     * </P>
     * @param n is the new numberOfCopies
     */
    public void setNumberOfCopies(int n) {
        /*Set numberOfCopies to n*/
        this.numberOfCopies = n;
    }
    /**
     * Function definition for setStatus()
     * <P>
     *      Sets the status to a passed value;
     * </P>
     * @param status is the new status
     */
    public void setStatus(String status) {
        /*Set the status to the new status*/
        this.status = status;
    }
}
