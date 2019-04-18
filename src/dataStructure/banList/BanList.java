/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.banList;
import java.io.Serializable;
import java.util.ArrayList;
public class BanList implements Serializable {
    /**
     * list holds all the ForbiddenLimited information
     */
    private ArrayList<LimitedCard> list;
    /**
     * name holds the name of the list
     */
    private String name;
    /**
     * Constructor with no arguments
     * <P>
     *     Set the variables to default values
     * </P>
     */
    public BanList() {
        list = null;
        name = null;
    }
    /**
     * Constructor with arguments
     * <P>
     *     Creates an object and sets up the variables properly
     * </P>
     * @param name
     */
    public BanList(String name) {
        list = new ArrayList<>();
        this.name = name;
    }
    /**
     * Function definition for getName()
     * <p>
     *     Return the name
     * </p>
     * @return the name
     */
    public String getName() {
        /*Return the name*/
        return name;
    }
    /**
     * Function definition for getList()
     * <p>
     *     Return the list
     * </p>
     * @return the list
     */
    public ArrayList<LimitedCard> getList() {
        /*Return the list*/
        return list;
    }
    /**
     * Function definition for setName()
     * <p>
     *     Sets the name to the passed value
     * </p>
     * @param name is the new name
     */
    public void setName(String name) {
        /*Set the name to the passed value*/
        this.name = name;
    }
    /**
     * Function definition for setList()
     * <p>
     *     Sets the list to a passed ArrayList
     * </p>
     * @param list is the new list to use
     */
    public void setList(ArrayList<LimitedCard> list) {
        /*Set the list to the passed ArrayList*/
        this.list = list;
    }
    /**
     * Function definition for outputBanList()
     * <p>
     *     Returns an ArrayList detailing all the banned and limited cards
     * </p>
     * @return an ArrayList of strings
     */
    public ArrayList<String> outputBanList() {
        /*Make a new ArrayList*/
        ArrayList<String> output = new ArrayList<>();
        /*For all elements in the list*/
        for(LimitedCard f : list) {
            /*Add a string to the ArrayList*/
            output.add(f.getCardName() + " is " + f.getStatus());
        }
        /*Return the ArrayList*/
        return output;
    }
}
