/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import java.io.Serializable;
public abstract class Card implements Serializable, Cloneable {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * cardName holds the name of the card
     */
    private String cardName;
    /**
     * cardDescription holds the description/effect of the card
     */
    private String cardDescription;
    /**
     * setID holds the name of the set that the card belongs to
     */
    private String setID;
    /**
     * value holds the value
     */
    private double value;
    /**
     * effValue holds the value of the card's effect (if there is one)
     */
    private int effValue;
    /**
     * Constructor with no arguments
     * <p>
     * Sets variables to default values
     */
    Card() {
        /*Sets the cardName to null*/
        cardName = null;
        /*Sets the cardDescription to null*/
        cardDescription = null;
        /*Sets the setID to null*/
        setID = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Sets the cardName and cardDescription to the strings passed
     * <p>
     * @param N is the string for the cardName
     * @param D is the string for the cardDescription
     * @param S is the string for the setID
     */
    Card(String N, String D, String S) {
        /*Sets the cardName to N*/
        cardName = N;
        /*Sets the cardDescription to D*/
        cardDescription = D;
        /*Sets the setID to S*/
        setID = S;
    }
    /**
     * Function definition for getCardName()
     * <p>
     * Returns the cardName of a given cardHierarchy.Card
     * <p>
     * @return the cardName
     */
    public String getCardName() {
        /*Return cardName*/
        return this.cardName;
    }
    /**
     * Function definition for getCardDescription()
     * <p>
     * Returns the cardDescription of a given cardHierarchy.Card
     * <p>
     * @return the cardDescription
     */
    public String getCardDescription() {
        /*Return cardDescription*/
        return this.cardDescription;
    }
    /**
     * Function definition for setCardID()
     * <p>
     *     Sets the setID of a given cardHierarchy.Card to a given String
     * </p>
     * @param ID is the string for setID
     */
    public void setCardID(String ID) {
        /*Set setID to ID*/
        setID = ID;
    }
    /**
     * Function definition for getCardID()
     * <P>
     *     Returns the setID of a given cardHierarchy.Card
     * </P>
     * @return the setID
     */
    public String getCardID() {
        /*Return setID*/
        return setID;
    }
    /**
     * Function definition for getEffValue()
     * <p>
     *     Returns the effValue of the card
     * </p>
     * @return effValue
     */
    public int getEffValue() {
        return effValue;
    }
    /**
     * Function definition for getValue()
     * <p>
     *     Returns the card's value
     * </p>
     * @return the value
     */
    public double getValue() {
        return value;
    }
    /**
     * Function definition for setValue()
     * <p>
     *     Takes a value to use as the card's value
     * </p>
     * @param value is the value to use
     */
    public void setValue(double value) {
        this.value = value;
    }
    /**
     * Function definition for setEffValue()
     * <p>
     *     Sets the effValue for the card to the passed value
     * </p>
     * @param effValue
     */
    public void setEffValue(int effValue) {
        this.effValue = effValue;
    }
    /**
     * Function definition for setCardName()
     * <p>
     * Sets the cardName variable to the passed string
     * <p>
     * @param N is the string for cardName
     */
    public void setCardName(String N) {
        /*Sets cardName to N*/
        this.cardName = N;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones a card and returns the clone
     * </p>
     */
    @Override
    public Card clone() throws CloneNotSupportedException {
        Card card = (Card) super.clone();
        return card;
    }
}
