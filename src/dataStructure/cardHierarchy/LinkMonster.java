/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.cardHierarchy;
import dataStructure.cardHierarchy.enumerators.LinkDirection;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterSubType;
import dataStructure.cardHierarchy.enumerators.MonsterType;
import java.util.ArrayList;
public class LinkMonster extends Card{
    /**
     * serialVersionUID allows the object(s) to be saved
     */
    public static final long serialVersionUID = 1L;
    /**
     * sumReq holds the summoning requirements
     */
    private String sumReq;
    /**
     * links holds the links available
     */
    private ArrayList<LinkDirection> links;
    /**
     * attackPoints holds the attack point value of a monster
     */
    private int attackPoints;
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
    private ArrayList<MonsterSubType> subTypes;
    /**
     * Constructor with no arguments
     * <p>
     * Calls the Card constructor
     */
    public LinkMonster() {
        /*Calls Card constructor*/
        super();
        /*Sets attackPoints to 0*/
        this.attackPoints = 0;
        /*Sets attribute to null*/
        this.attribute = null;
        /*Sets type to null*/
        this.type = null;
        /*Set sumReq to null*/
        sumReq = null;
        /*Set links to null*/
        links = null;
        /*Set subType to null*/
        subTypes = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Passes all passed values to the Card constructor
     * <p>
     * @param N is the name of the Card
     * @param D is the description of the Card
     * @param AP is the attack point value of the Card
     * @param A is the attribute of the Card
     * @param T is the type of the Card
     * @param S is the setID
     * @param SR is the sumReq
     * @param LD is the links
     */
    public LinkMonster(String N, String D, int AP, MonsterAttribute A, MonsterType T, String S, String SR, ArrayList<LinkDirection> LD) {
        /*Calls Card constructor*/
        super(N, D, S);
        /*Set sumReq to SR*/
        sumReq = SR;
        /*Set links to LD*/
        links = LD;
        /*Sets attackPoints to AP*/
        this.attackPoints = AP;
        /*Sets attribute to A*/
        this.attribute = A;
        /*Sets type to T*/
        this.type = T;
        /*Sets up a new list of sub-types*/
        subTypes = new ArrayList<>();
    }
    /**
     * Function definition for getAttackPoints()
     * <p>
     *     Returns the attackPoints of the card
     * </p>
     * @return the attackPoints
     */
    public int getAttackPoints() {
        return attackPoints;
    }
    /**
     * Function definition for getType()
     * <p>
     *     Return the type of the card
     * </p>
     * @return the type
     */
    public MonsterType getType() {
        return type;
    }
    /**
     * Function definition for getSumReq()
     * <p>
     *     Returns the sumReq for the Card
     * </p>
     * @return sumReq
     */
    public String getSumReq() {
        /*Return sumReq*/
        return sumReq;
    }
    /**
     * Function definition for getSubTypes()
     * <p>
     *     Returns subTypes
     * </p>
     * @return
     */
    public ArrayList<MonsterSubType> getSubTypes() {
        return subTypes;
    }
    /**
     * Function definition for getLinks()
     * <p>
     *     Returns the ArrayList of links
     * </p>
     * @return
     */
    public ArrayList<LinkDirection> getLinks() {
        /*Return links*/
        return links;
    }
    /**
     * Function definition for getAttribute()
     * <p>
     *     Returns the attribute of the card
     * </p>
     * @return the attribute
     */
    public MonsterAttribute getAttribute() {
        return attribute;
    }
    /**
     * Function definition for setAttackPoints()
     * <p>
     *     Set attackPoints to the passed value
     * </p>
     * @param attackPoints is the value to use
     */
    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    /**
     * Function definition for setAttribute()
     * <p>
     *     Sets the attribute to the passed value
     * </p>
     * @param attribute is the value to use
     */
    public void setAttribute(MonsterAttribute attribute) {
        this.attribute = attribute;
    }
    /**
     * Function definition for setType()
     * <p>
     *     Set the type to the passed value
     * </p>
     * @param type is the value to use
     */
    public void setType(MonsterType type) {
        this.type = type;
    }
    /**
     * Function definition for setSumReq()
     * <p>
     *     Sets the sumReq to the passed string
     * </p>
     * @param S is the value to make sumReq
     */
    public void setSumReq(String S) {
        /*Set sumReq to S*/
        sumReq = S;
    }
    /**
     * Function definition for setLinks()
     * <p>
     *     Sets the links to a passed ArrayList
     * </p>
     * @param LD is the ArrayList to set links to
     */
    public void setLinks(ArrayList<LinkDirection> LD) {
        /*Set the links to a passed ArrayList*/
        links = LD;
    }
    /**
     * Function definition for listLinks()
     * <p>
     *     Returns a string listing the links
     * </p>
     * @return a string listing the links
     */
    public String listLinks() {
        /*Create a StringBuilder to build with*/
        StringBuilder S = new StringBuilder();
        /*For all links*/
        for(int i = 0; i < links.size(); i++) {
            /*Append the link to the builder*/
            S.append(links.get(i).toString());
            /*Add a comma if not at the end*/
            if(i < links.size()-1) {
                S.append(", ");
            }
        }
        /*Return the built String*/
        return S.toString();
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the LinkMonster object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public LinkMonster clone() throws CloneNotSupportedException {
        LinkMonster clone = new LinkMonster(this.getCardName(), this.getCardDescription(), this.attackPoints, this.attribute, this.type, this.getCardID(), this.sumReq, this.links);
        clone.setEffValue(this.getEffValue());
        clone.setValue(this.getValue());
        for(MonsterSubType S : this.subTypes) {
            clone.getSubTypes().add(S);
        }
        return clone;
    }
}
