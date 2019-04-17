/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
public class RitualMonster extends EffectMonster {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * spellName is the name of the spell card required to summon the monster
     */
    private String spellName;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the EffectMonster constructor
     */
    public RitualMonster() {
        /*Calls EffectMonster constructor*/
        super();
    }
    /**
     * Constructor with arguments
     * <p>
     * Passes all passed values to the EffectMonster constructor
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param DP is the defence point value of the Card
     * @param L is the level of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param S is the setID
     * @param SN is the spellName
     */
    public RitualMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S, String SN) {
        /*Calls EffectMonster constructor*/
        super(N, D, AP, DP, L, A, T, S);
        /*Set the spellName to SN*/
        spellName = SN;
    }
    /**
     * Function definition for getSpellName()
     * <p>
     *     Return the spellName
     * </p>
     * @return spellName
     */
    public String getSpellName() {
        /*Return spellName*/
        return spellName;
    }
    /**
     * Function definition for setSpellName()
     * <p>
     *     Set the spellName to passed String
     * </p>
     * @param S it the passed string
     */
    public void setSpellName(String S) {
        /*Set spellName to S*/
        spellName = S;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the RitualMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public RitualMonster clone() throws CloneNotSupportedException {
        RitualMonster clone = new RitualMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID(), this.spellName);
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
