/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions;

import dataStructure.UserProfile;
import programFunctions.appData.AppData;
import programFunctions.appData.Cache;
import programFunctions.utilities.Utils;

import java.util.List;

public class ProgramFunctions {
    private static AppData programData;
    private static Utils utilities;



    /**
     * Function definition for updateUserList()
     * <p>
     *      Sets the user list to the files in the user folder.
     * </p>
     */
    public static void updateUserList() {
        /*Set the user list to the files in the user folder*/
        programData.setUsers(utilities.getFileHandler().searchUserFolder());
    }
    /**
     * Function definition for checkUser()
     * <p>
     *      Checks a given name against the user list to determine if a profile with that name
     *      already exists on the system.
     * </p>
     * @param name is the name to be tested
     * @return whether the name is okay or not
     */
    public static boolean checkUser(String name) {
        /*Update the user list*/
        updateUserList();
        String fileName = name;
        /*For all the names in the user list*/
        List<String> tmp = programData.getUsers();
        for (String aTmp : tmp) {
            System.out.println("[SYSTEM] Checking " + fileName + " against " + aTmp);
            /*If the name matches*/
            if (fileName.equals(aTmp)) {
                /*Return false*/
                return false;
            }
        }
        /*Return true*/
        return true;
    }




    /**
     * Function definition for createProfile()
     * <p>
     *      Checks whether a profile with this name already exists and then
     *      either creates or returns the error state.
     * </p>
     * @param name is the name for the profile
     * @return
     */
    public static boolean createProfile(String name) {
        if(name == null || name.equals("")) {
            return false;
        }
        /*If name is free*/
        if (checkUser(name)) {
            /*Make the object*/
            UserProfile user = new UserProfile(name);
            /*Save the object*/
            utilities.getFileHandler().saveUserProfile(user);
            /*Output success*/
            System.out.println("[SYSTEM] Profile created successfully");
            /*Set currentProfile to user*/
            programData.setCurrentProfile(user);
            programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
            programData.getUserInterface().updateTitle();
            programData.getCache().updateCache();
            /*Return*/
            return true;
        }
        /*Else*/
        else {
            /*Output an error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "001a: Profile with that name already exists");
            System.out.println("[ERROR] 001a: Profile with that name already exists");
            /*Return*/
            return false;
        }
    }
    /**
     * Function definition for deleteProfile()
     * <p>
     *      Deletes a profile with a given name.
     * </p>
     * @param name is the name of the profile to be deleted
     * @return whether deletion was successful or not
     */
    public static boolean deleteProfile(String name) {
        /*Update the user list*/
        updateUserList();
        /*Check the name is already a profile*/
        if (!checkUser(name)) {
            /*Delete the profile*/
            utilities.getFileHandler().deleteUserProfile(name);
            /*Output success*/
            System.out.println("[SYSTEM] Profile deleted successfully");
            /*If the selected profile is the active one*/
            if (profileActive()) {
                if (name.equals(programData.getCurrentProfile().getProfileName())) {
                    /*Remove the profile from the active slot*/
                    programData.setCurrentProfile(null);
                    programData.getUserInterface().updateScene(programData.getUserInterface().getDefaultScene());
                    programData.getUserInterface().updateTitle();
                }
            }
            /*return true*/
            return true;
        }
        /*Otherwise*/
        else {
            /*Output an error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "004: Named profile not present");
            System.out.println("[ERROR] 004: Named profile not present");
            /*Return false*/
            return false;
        }
    }






    /**
     * Function definition for profileActive()
     * <p>
     *      Check whether there is an active profile in the application.
     * </p>
     * @return whether there is a profile active
     */
    public static boolean profileActive() {
        /*Check program data for active profile*/
        if (programData.getCurrentProfile() != null) {
            /*Return true if there is*/
            return true;
        }
        /*Else*/
        else {
            /*Return false*/
            return false;
        }
    }

    public static AppData getProgramData() {
        return programData;
    }
    public static Utils getUtilities() {
        return utilities;
    }


    /**
     * Function definition for makeActive()
     * <p>
     *      Checks if the given profile exists, if it does, make it active
     *      else output error.
     * </p>
     * @param name is the name of the profile to make active
     * @return whether the function executed successfully
     */
    public static boolean makeActive(String name) {
        /*If user exists*/
        if (!checkUser(name)) {
            /*If there is already an  active profile*/
            if (programData.getCurrentProfile() != null) {
                /*Save the profile*/
                utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
            }
            UserProfile user = utilities.getFileHandler().loadUserProfile(name);
            if(user != null) {
                /*Set the active profile to the given one*/
                programData.setCurrentProfile(user);
                /*Output success*/
                System.out.println("[SYSTEM] Set " + programData.getCurrentProfile().getProfileName() + " as active profile");
                programData.setCache(new Cache(user));
                programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
                programData.getUserInterface().updateTitle();
                programData.getCache().updateCache();
                /*Return true*/
                return true;
            }
            else {
                return false;
            }
        }
        /*Otherwise*/
        else {
            /*Output failure*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "004: Profile of that name does not exist");
            System.out.println("[ERROR] 004: Profile of that name does not exist");
            /*Return true*/
            return false;
        }
    }

    public static void exit() {
        System.exit(0);
    }
}
