/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
public class EffectMonster extends MonsterCard {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the MonsterCard constructor
     */
    public EffectMonster() {
        /*Calls MonsterCard constructor*/
        super();
    }
    /**
     * Constructor with arguments
     * <p>
     * Passes all passed values to the MonsterCard constructor
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param DP is the defence point value of the Card
     * @param L is the level of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param S is the setID
     */
    public EffectMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S) {
        /*Calls MonsterCard constructor*/
        super(N, D, AP, DP, L, A, T, S);
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the EffectMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public EffectMonster clone() throws CloneNotSupportedException {
        EffectMonster clone = new EffectMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID());
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
