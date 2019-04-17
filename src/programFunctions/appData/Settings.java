/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.appData;
import java.io.Serializable;
public class Settings implements Serializable {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * shoppingSite holds the number that corresponds to the appropriate site
     * 0 = Card Market
     * 1 = Ebay
     * TO BE CONTINUED
     */
    private int shoppingSite;
    /**
     * region holds the number that corresponds to the appropriate region
     * 0 = uk (en)
     * 1 = us
     * 2 = de
     * 3 = fr
     * TO BE CONTINUED
     */
    private int region;
    /**
     * Constructor with no arguments
     * <p>
     *     Sets up the settings to their default values
     * </p>
     */
    public Settings() {
        shoppingSite = 0;
        region = 0;
    }
    /**
     * Function definition for getShoppingSite()
     * <p>
     *     Return the shoppingSite value
     * </p>
     * @return the shoppingSite value
     */
    public int getShoppingSite() {
        return shoppingSite;
    }
    /**
     * Function definition for getRegion()
     * <p>
     *     Returns the value for the region
     * </p>
     * @return the region
     */
    public int getRegion() {
        return region;
    }
    /**
     * Function definition for shoppingSite()
     * <p>
     *     Sets the shoppingSite value to the passed value
     * </p>
     * @param shoppingSite is the new value to be used
     */
    public void setShoppingSite(int shoppingSite) {
        this.shoppingSite = shoppingSite;
    }
    /**
     * Function definition for setRegion()
     * <p>
     *     Sets the region to the passed value
     * </p>
     * @param region is the new value to be used
     */
    public void setRegion(int region) {
        this.region = region;
    }
}
