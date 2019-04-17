/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
public class SynchroMonster extends EffectMonster {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * nTuners holds the number of tuners required
     */
    private int nTuners;
    /**
     * nNTuners holds the number of non-tuners required
     */
    private int nNTuners;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the MonsterCard constructor and sets the variables to default
     * values
     */
    public SynchroMonster() {
        /*Call MonsterCard constructor*/
        super();
        /*Set nTuners to 0*/
        nTuners = 0;
        /*Set nNTuners to 0*/
        nNTuners = 0;
    }
    /**
     * Constructor with arguments
     * <p>
     * Passes most passed values to the MonsterCard constructor, and sets the variable
     * to the last to passed values
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param DP is the defence point value of the Card
     * @param L is the level of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param S is the setID
     * @param NT is the number of tuner monsters required
     * @param NNT is the number of non-tuner monsters required
     */
    public SynchroMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S, int NT, int NNT) {
        /*Calls MonsterCard constructor*/
        super(N, D, AP, DP, L, A, T, S);
        /*Sets nTuners to NT*/
        nTuners = NT;
        /*Sets nNTuners to NNT*/
        nNTuners = NNT;
    }
    /**
     * Function definition for getNumberOfTuners()
     * <p>
     * Return the nTuners for a given Card
     * <p>
     * @return the nTuners
     */
    public int getNumberOfTuners() {
        /*Return nTuners*/
        return this.nTuners;
    }
    /**
     * Function definition for getNumberOfNonTuners()
     * <p>
     * Return the nNTuners for a given Card
     * <p>
     * @return the nNTuners
     */
    public int getNumberOfNonTuners() {
        /*Return nNTuners*/
        return this.nNTuners;
    }
    /**
     * Function definition for setNumberOfNonTuners()
     * <p>
     * Set nNTuners to N
     * <p>
     * @param N is the value to set nNTuners to
     */
    public void setNumberOfNonTuners(int N) {
        /*Set nNTuners to N*/
        this.nNTuners = N;
    }
    /**
     * Function definition for setNumberOfTuners()
     * <p>
     * Set nTuners to N
     * <p>
     * @param N is the value to set nTuners to
     */
    public void setNumberOfTuners(int N) {
        /*Set nTuners to N*/
        this.nTuners = N;
    }
    /**
     * Function definition for listSynchroMaterials()
     * <p>
     * Returns a string detailing the synchro materials
     * <p>
     * @return a string
     */
    public String listSynchroMaterials() {
        /*Return a string detailing the materials*/
        return this.nTuners + " tuners + " + this.nNTuners + " non-tuner monsters";
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the SynchroMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public SynchroMonster clone() throws CloneNotSupportedException {
        SynchroMonster clone = new SynchroMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID(), this.nTuners, this.nNTuners);
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
