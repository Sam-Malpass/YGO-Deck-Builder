/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
public class XYZMonster extends EffectMonster {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * nMonsters holds the number of monsters needed as XYZ materials
     */
    private int nMonsters;
    /**
     * lMonsters holds the level of the monsters needed as XYZ materials
     */
    private int lMonsters;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the MonsterCard constructor, then sets the variables to default
     * values
     */
    public XYZMonster() {
        /*Calls MonsterCard constructor*/
        super();
        /*Set nMonsters to 0*/
        nMonsters = 0;
        /*Set lMonsters to 0*/
        lMonsters = 0;
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls the MonsterCard constructor, then sets variables to remaining passed
     * values
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param DP is the defence point value of the Card
     * @param L is the level of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param S is the setID
     * @param NM is the number of materials
     * @param LM is the level of the materials
     */
    public XYZMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S, int NM, int LM) {
        /*Calls MonsterCard constructor*/
        super(N, D, AP, DP, L, A, T, S);
        /*nMonsters is set to NM*/
        nMonsters = NM;
        /*lMonsters is set to LM*/
        lMonsters = LM;
    }
    /**
     * Function definition for getNumberXYZMaterials()
     * <p>
     * Returns the nMonsters value of a given Card
     * <p>
     * @return the nMonsters
     */
    public int getNumberXYZMaterials() {
        /*Return nMonsters*/
        return this.nMonsters;
    }
    /**
     * Function definition for getLevelXYZMaterials()
     * <p>
     * Returns the lMonsters for a given Card
     * <p>
     * @return the lMonsters
     */
    public int getLevelXYZMaterials() {
        /*Return lMonsters*/
        return this.lMonsters;
    }
    /**
     * Function definition for setNumberXYZMaterials()
     * <p>
     * Sets the nMonsters to N
     * <p>
     * @param N is value to set nMonsters to
     */
    public void setNumberXYZMaterials(int N) {
        /*Set nMonsters to N*/
        this.nMonsters = N;
    }
    /**
     * Function definition for setLevelXYZMaterials()
     * <p>
     * Set lMonsters to L
     * <p>
     * @param L is the value to set lMonsters to
     */
    public void setLevelXYZMaterials(int L) {
        /*Set lMonsters to L*/
        this.lMonsters = L;
    }
    /**
     * Function definition for listXYZMaterials()
     * <p>
     * Returns a string created from the nMonsters and lMonsters
     * <p>
     * @return a string
     */
    public String listXYZMaterials() {
        /*Return a string*/
        return this.nMonsters + " level " + this.lMonsters + " monsters";
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the XYZMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public XYZMonster clone() throws CloneNotSupportedException {
        XYZMonster clone = new XYZMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID(), this.nMonsters, this.lMonsters);
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
