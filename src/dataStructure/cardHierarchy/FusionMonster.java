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
public class FusionMonster extends EffectMonster {
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * fusionMaterial is the list of material cards needed to make the monster
     */
    private ArrayList<String> fusionMaterial;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the MonsterCard constructor and sets the variable to default value
     */
    public FusionMonster() {
        /*Call MonsterCard constructor*/
        super();
        /*Create new ArrayList*/
        fusionMaterial = new ArrayList<>();
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls the constructor for MonsterCard using require passed values and then sets
     * fusionMaterials to passed list
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param DP is the defence point value of the Card
     * @param L is the level of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param C is the list of fusion materials requires
     * @param S is the setID
     */
    public FusionMonster(String N, String D, int AP, int DP, int L, MonsterAttribute A, MonsterType T, String S, ArrayList<String> C) {
        /*Call MonsterCard constructor*/
        super(N, D, AP, DP, L, A, T, S);
        /*Set fusionMaterial to C*/
        fusionMaterial = C;
    }
    /**
     * Function definition for getFusionMaterial()
     * <p>
     * Return the fusionMaterial list for a given Card
     * <p>
     * @return the fusionMaterial
     */
    public ArrayList<String> getFusionMaterial() {
        /*Return fusionMaterial*/
        return this.fusionMaterial;
    }
    /**
     * Function definition for setFusionMaterial()
     * <p>
     * Set the fusionMaterial list to C
     * <p>
     * @param C is a list to set fusionMaterial to
     */
    public void setFusionMaterial(ArrayList<String> C) {
        /*Set fusionMaterial to C*/
        this.fusionMaterial = C;
    }
    /**
     * Function definition for listFusionMaterial()
     * <p>
     * Creates and amends a string with the name(s) of the fusion material(s)
     * <p>
     * @return the string created in the function
     */
    public String listFusionMaterial() {
        /*Creates empty string*/
        StringBuilder S = new StringBuilder();
        /*For all materials in the list*/
        for (int i = 0; i < fusionMaterial.size(); i++) {
            /*Add the material name*/
            S.append(fusionMaterial.get(i));
            if(i < fusionMaterial.size()-1) {
                S.append(" + ");
            }
        }
        /*Return the string*/
        return S.toString();
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the FusionMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public FusionMonster clone() throws CloneNotSupportedException {
        FusionMonster clone = new FusionMonster(this.getCardName(), this.getCardDescription(), this.getAttackPoints(), this.getDefencePoints(), this.getLevel(), this.getMonsterAttribute(), this.getMonsterType(), this.getCardID(), new ArrayList<>());
        for(MonsterSubType S : this.getSubTypes()) {
            clone.getSubTypes().add(S);
        }
        for(String S : fusionMaterial) {
            clone.getFusionMaterial().add(S);
        }
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        return clone;
    }
}
