/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.TrapType;
public class TrapCard extends Card {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * trapType holds the type of Trap the Card is
     */
    private TrapType trapType;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the constructor for Card and sets trapType to null
     */
    public TrapCard() {
        /*Calls Card constructor*/
        super();
        /*Sets trapType to null*/
        trapType = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls the constructor for Card and sets trapType to the passed TrapType
     * <p>
     * @param N is the name of the card to be passed
     * @param D is the description of the card to be passed
     * @param T is the TrapType for trapType
     * @param S is the setID
     */
    public TrapCard(String N, String D, TrapType T, String S) {
        /*Call Card constructor*/
        super(N, D, S);
        /*Set trapType to T*/
        trapType = T;
    }
    /**
     * Function definition for getTrapType()
     * <p>
     * Returns the trapType of a given card
     * <p>
     * @return the trapType
     */
    public TrapType getTrapType() {
        /*Return trapType*/
        return this.trapType;
    }
    /**
     * Function definition for setTrapType()
     * <p>
     * Sets trapType to the passed TrapType T
     * <p>
     * @param T is the TrapType to set trapType to
     */
    public void setTrapType(TrapType T) {
        /*Set trapType to T*/
        this.trapType = T;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones a TrapCard object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public TrapCard clone() throws CloneNotSupportedException {
        TrapCard clone = new TrapCard(this.getCardName(), this.getCardDescription(), this.trapType, this.getCardID());
        clone.setEffValue(this.getEffValue());
        return clone;
    }
}
