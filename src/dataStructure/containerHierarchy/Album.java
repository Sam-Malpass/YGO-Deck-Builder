/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.containerHierarchy;
import dataStructure.cardHierarchy.Card;
public class Album extends Container {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * Constructor with no arguments
     * <p>
     * Calls Container constructor
     */
    public Album() {
        /*Calls Container constructor*/
        super();
    }
    /**
     * Constructor with Arguments
     * <p>
     * Calls Container constructor using the string passed and a hard-coded
     * max limit
     * <p>
     * @param N is the name of the Container
     */
    public Album(String N) {
        /*Calls Container constructor*/
        super(N, 1000000);
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the Album object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public Album clone() throws CloneNotSupportedException {
        Album clone = new Album(this.getContainerName());
        for(Card C : this.getCards()) {
            clone.getCards().add(C.clone());
        }
        return clone;
    }
}
