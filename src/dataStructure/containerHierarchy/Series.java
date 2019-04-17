/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.containerHierarchy;
import dataStructure.cardHierarchy.Card;
public class Series extends Container {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * Constructor with no arguments
     * <p>
     *     Calls the Container constructor
     * </p>
     */
    public Series() {
        super();
    }
    /**
     * Constructor with arguments
     * <p>
     *     Calls the Container constructor with arguments
     * </p>
     * @param name the name of the series
     */
    public Series(String name) {
        super(name, 500);
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the Series object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public Series clone() throws CloneNotSupportedException {
        Series clone = new Series(this.getContainerName());
        for(Card C : this.getCards()) {
            clone.getCards().add(C.clone());
        }
        return clone;
    }
}
