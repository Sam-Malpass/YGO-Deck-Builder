/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
public class PendulumMonster extends EffectMonster {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * spellEffect holds the effect of the Pendulum if used as a spell
     */
    private String spellEffect;
    /**
     * redScale holds the value of the red side pendulum scale
     */
    private int redScale;
    /**
     * blueScale holds the value of the blue side pendulum scale
     */
    private int blueScale;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the MonsterCard constructor
     */
    public PendulumMonster() {
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
     * @param SE is the spellEffect
     * @param RS is the redScale
     * @param BS is the blueScale
     */
    public PendulumMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S, String SE, int RS, int BS) {
        /*Calls MonsterCard constructor*/
        super(N, D, AP, DP, L, A, T, S);
        /*Set the spellEffect to SE*/
        spellEffect = SE;
        /*Set the redScale to RS*/
        redScale = RS;
        /*Set the blueScale to BS*/
        blueScale = BS;
    }
    /**
     * Function definition for getSpellEffect()
     * <p>
     *     Returns the string of the spellEffect
     * </p>
     * @return spellEffect
     */
    public String getSpellEffect() {
        /*Return spellEffect*/
        return spellEffect;
    }
    /**
     * Function definition for getRedScale()
     * <p>
     *     Returns the redScale
     * </p>
     * @return redScale
     */
    public int getRedScale() {
        /*Return redScale*/
        return redScale;
    }
    /**
     * Function definition for getBlueScale()
     * <p>
     *     Returns the blueScale
     * </p>
     * @return blueScale
     */
    public int getBlueScale() {
        /*Return blueScale*/
        return blueScale;
    }
    /**
     * Function definition for setSpellEffect()
     * <p>
     *     Sets the spellEffect to a given string
     * </p>
     * @param S is the string to set spellEffect to
     */
    public void setSpellEffect(String S) {
        /*Set spellEffect to S*/
        spellEffect = S;
    }
    /**
     * Function definition for setRedScale()
     * <p>
     *     Sets the redScale to S
     * </p>
     * @param S is the value to make redScale
     */
    public void setRedScale(int S) {
        /*Set the redScale to S*/
        redScale = S;
    }
    /**
     * Function definition for setBlueScale()
     * <p>
     *     Set the blueScale to S
     * </p>
     * @param S is the value to make blueScale
     */
    public void setBlueScale(int S) {
        /*Set the blueScale to S*/
        blueScale = S;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the PendulumMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public PendulumMonster clone() throws CloneNotSupportedException {
        PendulumMonster clone = new PendulumMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID(), this.spellEffect, this.redScale, this.blueScale);
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
