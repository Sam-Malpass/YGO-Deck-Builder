/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
import java.util.ArrayList;
public class MonsterCard extends Card {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * attackPoints holds the attack point value of a monster
     */
    private int attackPoints;
    /**
     * defencePoints holds the defence point value of a monster
     */
    private int defencePoints;
    /**
     * level holds the level of a monster
     */
    private int level;
    /**
     * attribute holds the attribute of a monster
     */
    private MonsterAttribute attribute;
    /**
     * type holds the type of a monster
     */
    private MonsterType type;
    /**
     * subTypes holds the sub-types of the card
     */
    private ArrayList<MonsterSubType> subTypes = new ArrayList<>();
    /**
     * Constructor with no arguments
     * <p>
     * Calls the constructor for Card and sets all variables to default values
     */
    public MonsterCard() {
        /*Calls Card constructor*/
        super();
        /*Sets attackPoints to 0*/
        this.attackPoints = 0;
        /*Sets defencePoints to 9*/
        this.defencePoints = 0;
        /*Sets level to 0*/
        this.level = 0;
        /*Sets attribute to null*/
        this.attribute = null;
        /*Sets type to null*/
        this.type = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls the constructor for Card using passed name and description, and sets variables
     * to passed values
     * <p>
     * @param N is the name of the card passed to Card constructor
     * @param D is the description of the card passed to Card constructor
     * @param AP is for attackPoints
     * @param DP is for defencePoints
     * @param L is for level
     * @param A is for attribute
     * @param T is for type
     * @param S is for the setID
     */
    public MonsterCard(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S) {
        /*Calls Card constructor*/
        super(N, D, S);
        /*Sets attackPoints to AP*/
        this.attackPoints = AP;
        /*Sets defencePoints to DP*/
        this.defencePoints = DP;
        /*Sets level to L*/
        this.level = L;
        /*Sets attribute to A*/
        this.attribute = A;
        /*Sets type to T*/
        this.type = T;
    }
    /**
     * Function definition for getAttackPoints()
     * <p>
     * Return the attackPoints of a given Card
     * <p>
     * @return the attackPoints
     */
    public int getAttackPoints() {
        /*Return attackPoints*/
        return this.attackPoints;
    }
    /**
     * Function definition for getDefencePoints()
     * <p>
     * Return the defencePoints for a given Card
     * <p>
     * @return the defencePoints
     */
    public int getDefencePoints() {
        /*Return defencePoints*/
        return this.defencePoints;
    }
    /**
     * Function definition for getLevel()
     * <p>
     * Return the level for a given Card
     * <p>
     * @return the level
     */
    public int getLevel() {
        /*Return level*/
        return this.level;
    }
    /**
     * Function definition for getMonsterAttribute()
     * <p>
     * Return the attribute for a given Card
     * <p>
     * @return the attribute
     */
    public MonsterAttribute getMonsterAttribute() {
        /*Return attribute*/
        return this.attribute;
    }
    /**
     * Function definition for getMonsterType()
     * <p>
     * Return the type for a given Card
     * <p>
     * @return the type
     */
    public MonsterType getMonsterType() {
        /*Return type*/
        return this.type;
    }
    /**
     * Function definition for getSubTypes()
     * <p>
     *     Returns the subTypes of the card
     * </p>
     * @return
     */
    public ArrayList<MonsterSubType> getSubTypes() {
        return subTypes;
    }
    /**
     * Function definition for setAttackPoints()
     * <p>
     * Sets the attackPoints to A
     * <p>
     * @param A the value to set attackPoints to
     */
    public void setAttackPoints(int A) {
        /*Set attackPoints to A*/
        this.attackPoints = A;
    }
    /**
     * Function definition for setDefencePoints()
     * <p>
     * Set defencePoints to D
     * <p>
     * @param D is the value to set the defencePoints to
     */
    public void setDefencePoints(int D) {
        /*Set defencePoints to D*/
        this.defencePoints = D;
    }
    /**
     * Function definition for setLevel()
     * <p>
     * Sets the level to L
     * <p>
     * @param L is the value to set level to
     */
    public void setLevel(int L) {
        /*Set level to L*/
        this.level = L;
    }
    /**
     * Function definition for setMonsterAttribute()
     * <p>
     * Set the attribute to A
     * <p>
     * @param A is the value to set attribute to
     */
    public void setMonsterAttribute(MonsterAttribute A) {
        /*Set attribute to A*/
        this.attribute = A;
    }
    /**
     * Function definition for setMonsterType()
     * <p>
     * Sets the type to T
     * <p>
     * @param T is the value to set type to
     */
    public void setMonsterType(MonsterType T) {
        /*Set type to T*/
        this.type = T;
    }
    /**
     * Function defintion for clone()
     * <p>
     *     Clones the MonsterCard object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public MonsterCard clone() throws CloneNotSupportedException {
        MonsterCard clone = new MonsterCard(this.getCardName(), this.getCardDescription(), this.attackPoints, this.defencePoints, this.level, this.attribute, this.type, this.getCardID());
        for(MonsterSubType S : this.subTypes) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
