/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.SpellType;
public class SpellCard extends Card {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * spellType holds the type of spell the card is
     */
    private SpellType spellType;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the Card default constructor and sets spellType to null
     */
    public SpellCard() {
        /*Call Card constructor*/
        super();
        /*Set spellType to null*/
        spellType = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls the Card constructor and sets spellType to T
     * <p>
     * @param N is the name that will be passed to the Card constructor
     * @param D is the description that will be passed to the Card constructor
     * @param T is the SpellType for spellType
     * @param S is the setID
     */
    public SpellCard(String N, String D, SpellType T, String S) {
        /*Call Card constructor*/
        super(N, D, S);
        /*Set spellType to T*/
        spellType = T;
    }
    /**
     * Function definition for getSpellType()
     * <p>
     * Returns the spellType of a given Card
     * <p>
     * @return the spellType
     */
    public SpellType getSpellType() {
        /*Return spellType*/
        return this.spellType;
    }
    /**
     * Function definition for setSpellType()
     * <p>
     * Sets the spellType to the passed SpellType
     * <p>
     * @param T is the SpellType to set spellType to
     */
    public void setSpellType(SpellType T) {
        /*Set spellType to T*/
        this.spellType = T;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the spell card
     * </p>
     * @return the clone
     * @throws CloneNotSupportedException
     */
    @Override
    public SpellCard clone() throws CloneNotSupportedException {
        SpellCard clone = new SpellCard(this.getCardName(), this.getCardDescription(), this.spellType, this.getCardID());
        clone.setEffValue(this.getEffValue());
        return clone;
    }
}
