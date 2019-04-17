/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure;
import java.io.Serializable;
public class ProfileSettings implements Serializable {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * hasPassword determines whether the profile uses a password
     */
    private boolean hasPassword;
    /**
     * password holds the password for the profile
     */
    private String password;
    /**
     * includeDecks determines whether to include cards from decks when importing decks
     */
    private boolean includeDecks;
    /**
     * toggleSuggestions determines whether to show suggestions in deck building
     */
    private boolean toggleSuggestions;
    /**
     * includeUnowned determines whether to include cards that are unowned by the profile
     */
    private boolean includeUnowned;
    /**
     * Constructor with no arguments
     * <p>
     *     Sets up a ProfileSettings object
     * </p>
     */
    public ProfileSettings() {
        hasPassword = false;
        password = null;
        includeDecks = false;
        toggleSuggestions = true;
        includeUnowned = false;
    }
    /**
     * Function definition for isHasPassword()
     * <p>
     *     Returns the value of hasPassword
     * </p>
     * @return hasPassword
     */
    public boolean isHasPassword() {
        /*Return hasPassword*/
        return hasPassword;
    }
    /**
     * Function definition for getPassword()
     * <p>
     *     Returns the password
     * </p>
     * @return the password
     */
    public String getPassword() {
        /*Return password*/
        return password;
    }
    /**
     * Function definition for isIncludeDecks()
     * <p>
     *     Return the value of includeDecks
     * </p>
     * @return includeDecks
     */
    public boolean isIncludeDecks() {
        /*Return includeDecks*/
        return includeDecks;
    }
    /**
     * Function definition for isIncludeUnowned()
     * <p>
     *     Return the value of includeUnowned
     * </p>
     * @return includeUnowned
     */
    public boolean isIncludeUnowned() {
        /*Return includeUnowned*/
        return includeUnowned;
    }
    /**
     * Function definition for isToggleSuggestions()
     * <p>
     *     Return the value of toggleSuggestions
     * </p>
     * @return toggleSuggestions
     */
    public boolean isToggleSuggestions() {
        /*Return toggleSuggestions*/
        return toggleSuggestions;
    }
    /**
     * Function definition for setHasPassword()
     * <p>
     *     Set the hasPassword to the passed value
     * </p>
     * @param hasPassword is the new value
     */
    public void setHasPassword(boolean hasPassword) {
        /*Set the hasPassword to the passed value*/
        this.hasPassword = hasPassword;
    }
    /**
     * Function definition for setPassword()
     * <p>
     *     Set the password to the newPassword
     * </p>
     * @param newPassword is the new password
     */
    public void setPassword(String newPassword) {
        /*Set the password to the passed value*/
        password = newPassword;
    }
    /**
     * Function definition for setIncludeDecks()
     * <p>
     *     Set the includeDecks to the passed value
     * </p>
     * @param includeDecks is the new value
     */
    public void setIncludeDecks(boolean includeDecks) {
        /*Set the includeDecks to the passed value*/
        this.includeDecks = includeDecks;
    }
    /**
     * Function definition for setIncludeUnowned()
     * <p>
     *     Set the includeUnowned to the passed value
     * </p>
     * @param includeUnowned is the new value
     */
    public void setIncludeUnowned(boolean includeUnowned) {
        /*Set the includeUnowned to the passed value*/
        this.includeUnowned = includeUnowned;
    }
    /**
     * Function definition for setToggleSuggestions()
     * <p>
     *     Set the toggleSuggestions to the passed value
     * </p>
     * @param toggleSuggestions is the new value
     */
    public void setToggleSuggestions(boolean toggleSuggestions) {
        /*Set toggleSuggestions to the passed value*/
        this.toggleSuggestions = toggleSuggestions;
    }
}
