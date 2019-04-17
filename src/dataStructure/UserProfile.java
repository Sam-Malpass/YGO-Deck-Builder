/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import java.io.Serializable;
import java.util.ArrayList;
public class UserProfile implements Serializable {
    /**
     * serialVersionUID allows the class to be serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * userContainers holds the Containers for a user
     */
    private ArrayList<Container> userContainers;
    /**
     * profileName holds the name of the user
     */
    private String profileName;
    /**
     * profileSettings holds the settings
     */
    private ProfileSettings profileSettings;
    /**
     * Constructor with no arguments
     * <p>
     * Sets the variables to null values
     */
    public UserProfile() {
        /*Set profileName to null*/
        profileName = null;
        /*Set userContainers to null*/
        userContainers = null;
        /*Set the profileSettings to null*/
        profileSettings = null;
    }
    /**
     * Constructor with arguments
     * <p>
     * Sets profileName to passed string and creates a new ArrayList for userContainers
     * <p>
     * @param N is the name of the profile
     */
    public UserProfile(String N) {
        /*Sets the profileName to N*/
        profileName = N;
        /*Creates new ArrayList*/
        userContainers = new ArrayList<>();
        /*Setup the settings*/
        profileSettings = new ProfileSettings();
    }
    /**
     * Function definition for getProfileName()
     * <p>
     * Return the profileName for a given user
     * <p>
     * @return the profileName
     */
    public String getProfileName() {
        /*Return profileName*/
        return this.profileName;
    }
    /**
     * Function definition for getProfileSettings()
     * <p>
     *     Returns the profileSettings
     * </p>
     * @return the profileSettings
     */
    public ProfileSettings getProfileSettings() {
        /*Return the profileSettings*/
        return profileSettings;
    }
    /**
     * Function definition for getUserContainers()
     * <p>
     *     Return the userContainers
     * </p>
     * @return userContainers
     */
    public ArrayList<Container> getUserContainers() {
        return userContainers;
    }
    /**
     * Function definition for setProfileName()
     * <p>
     * Sets the profileName to N
     * <p>
     * @param N is the value to set profileName to
     */
    public void setProfileName(String N) {
        /*Set profileName to N*/
        profileName = N;
    }
    /**
     * Function definition for setContainers()
     * <p>
     *     Sets the userContainers to a passed list
     * </p>
     * @param c
     */
    public void setContainers(ArrayList<Container> c) {
        userContainers = c;
    }
    /**
     * Function definition for addContainer()
     * <p>
     * Checks whether a Container of the same name already exists and
     * then either returns or adds the container
     * <p>
     * @param C is a Container to be added
     */
    public void addContainer(Container C) {
        /*For all Containers the user has*/
        for (Container userContainer : userContainers) {
            /*If the C shares the same name as the Container*/
            if (userContainer.getContainerName().equals(C.getContainerName())) {
                /*Return*/
                return;
            }
        }
        /*Add the Container C*/
        userContainers.add(C);
    }
    /**
     * Function definition for removeContainer()
     * <p>
     * Checks all Containers a user has, and if the Container that is being searched
     * for is found, remove it
     * <p>
     * @param C the Container to remove
     */
    public void removeContainer(Container C) {
        /*For all Containers the user has*/
        for (int i = 0; i < userContainers.size(); i++) {
            /*Check if there is a Container with the same name*/
            if (userContainers.get(i).getContainerName().equals(C.getContainerName())) {
                /*Remove the Container C*/
                userContainers.remove(C);
            }
        }
        /*Return*/
    }
    /**
     * Function definition for numberContainers()
     * <p>
     * Returns the number of Containers a user has
     * <p>
     * @return the number of Containers
     */
    public int numContainers() {
        /*Return the number of Containers*/
        return userContainers.size();
    }
    /**
     * Function definition for listContainers()
     * <p>
     * Create an ArrayList of type string, and then add the Containers names
     * of all Containers owned by the user to the ArrayList, before returning
     * said ArrayList
     * <p>
     * @return the ArrayList of Container names
     */
    public ArrayList<String> listContainers() {
        /*Create a new ArrayList*/
        ArrayList<String> list = new ArrayList<>();
        /*For all Containers the user owns*/
        for (Container userContainer : userContainers) {
            /*Add the Container name to the list*/
            list.add(userContainer.getContainerName());
        }
        /*Return the list*/
        return list;
    }
    /**
     * Function definition for determineContainer()
     * <p>
     * Checks the string against the names of all user-owned Containers
     * and returns the Container it matches with
     * <p>
     * @param S is the name of a Container that is being searched for
     * @return the Container that matches
     */
    public Container determineContainer(String S) {
        /*For all the Containers owned by a user*/
        for (Container userContainer : userContainers) {
            /*If the Container's name matches the string*/
            if (userContainer.getContainerName().equals(S)) {
                if(userContainer instanceof Deck) {
                    Deck newCont = (Deck) userContainer;
                    return newCont;
                }
                else {
                    Album newCont = (Album) userContainer;
                    return  newCont;
                }
            }
        }
        /*Return null*/
        return null;
    }
    /**
     * Function definition for listAllCards()
     * <p>
     *     Returns an array list of all the cards within all containers
     * </p>
     * @return list of all cards
     */
    public ArrayList<String> listAllCards() {
        /*Create ArrayList*/
        ArrayList<String> list = new ArrayList<>();
        /*For all containers*/
        for (Container userContainer : userContainers) {
            /*For all cards in container*/
            for (int j = 0; j < userContainer.listCardsString().size(); j++) {
                /*Add card name to list*/
                list.add(userContainer.listCardsString().get(j).toString());
            }
        }
        /*Return the list*/
        return list;
    }
}
