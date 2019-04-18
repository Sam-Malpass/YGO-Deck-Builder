/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.appData;
import dataStructure.UserProfile;
import graphicalUserInterface.GUI;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AppData {
    /**
     * users holds the list of profiles
     */
    private List<String> users;
    /**
     * userInterface holds the object for the UI
     */
    private GUI userInterface;
    /**
     * currentProfile holds the active user profile
     */
    private UserProfile currentProfile;
    /**
     * icon holds the icon of the program
     */
    private Image icon;
    /**
     * cache holds misc data that is useful for the program
     */
    private Cache cache;
    /**
     * globalSettings holds the global settings
     */
    private Settings globalSettings;
    /**
     * versionNumber holds the version number of the application
     */
    private String versionNumber = "default";
    /**
     * Constructor that sets up all objects
     */
    public AppData() {

        /*Create a GUI*/
        //userInterface = new GUI();
        /*Set the profile to null*/
        currentProfile = null;
        /*Create a cache*/
        //cache = new Cache();
        globalSettings = new Settings();
        try {
            /*Open a file*/
            File test = new File("resources/icon.jpg");
            /*Make a file input stream*/
            FileInputStream input = new FileInputStream(test);
            /*Set the icon*/
            icon = new Image(input);
        }
        /*Catch*/
        catch(FileNotFoundException ignored) {
        }
        ArrayList<String> list = new ArrayList<>();
        File file = new File("resources/appDetails");
        try (Scanner input = new Scanner(file))
        {
            while (input.hasNextLine()) {
                System.out.print(input.hasNextLine());
                list.add(input.nextLine());
            }
            for(String s : list) {
                if(s.contains("VERSION:")) {
                    versionNumber = s.substring(9, s.length());
                }
            }
        }
        catch (Exception e) {
        }
    }
    /**
     * Function definition for getUsers()
     * <p>
     * Returns the list of users
     * </p>
     * @return the list of users
     */
    public List<String> getUsers() {
        /*Return the users*/
        return users;
    }
    /**
     * Function definition for getUserInterface()
     * <p>
     * Return the userInterface.
     * </p>
     * @return userInterface
     */
    public GUI getUserInterface() {
        /*Return userInterface*/
        return userInterface;
    }
    /**
     * Function definition for getCurrentProfile()
     * <p>
     *     Return the currentProfile.
     * </p>
     * @return currentProfile
     */
    public UserProfile getCurrentProfile() {
        /*Return currentProfile*/
        return currentProfile;
    }
    /**
     * Function definition for getCache()
     * <p>
     *     Return the cache
     * </p>
     * @return the cache
     */
    public Cache getCache() {
        return cache;
    }
    /**
     * Function definition for getGlobalSettings()
     * <p>
     *     Returns the globalSettings
     * </p>
     * @return the globalSettings
     */
    public Settings getGlobalSettings() {
        return globalSettings;
    }
    /**
     * Function definition for getVersionNumber()
     * <p>
     *     Returns the version number of the application
     * </p>
     * @return the version number
     */
    public String getVersionNumber() {
        return versionNumber;
    }
    /**
     * Function definition for getIcon()
     * <p>
     *     Returns the icon
     * </p>
     * @return icon
     */
    public Image getIcon() {
        return icon;
    }
    /**
     * Function definition for setUsers()
     * <p>
     * Sets the user list to the passed list.
     * </p>
     * @param list is the user list to be used
     */
    public void setUsers(List<String> list) {
        /*Set users to list*/
        users = list;
        /*Return*/
    }
    /**
     * Function definition for setUserInterface()
     * <p>
     *     Set userInterface to passed BaseGUI object.
     * </p>
     * @param newGUI is the new userInterface
     */
    public void setUserInterface(GUI newGUI) {
        /*Set userInterface to newGUI*/
        userInterface = newGUI;
        /*Return*/
    }
    /**
     * Function definition for setCurrentProfile()
     * <p>
     *     Sets currentProfile to the passed UserProfile object.
     * </p>
     * @param profile is the profile to be made active
     */
    public void setCurrentProfile(UserProfile profile) {
        /*Set currentProfile to profile*/
        currentProfile = profile;
        /*Return*/
    }
    /**
     * Function definition for setCache()
     * <p>
     *     Set the cache
     * </p>
     * @param data is the new cache
     */
    public void setCache(Cache data) {
        cache = data;
    }
    /**
     * Function definition for setVersionNumber()
     * <p>
     *     Sets the version number to the passed value
     * </p>
     * @param versionNumber
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

}
