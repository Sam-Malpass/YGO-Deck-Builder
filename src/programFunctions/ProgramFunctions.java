/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions;
import dataStructure.UserProfile;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Deck;
import dataStructure.containerHierarchy.Series;
import javafx.application.Application;
import javafx.stage.Stage;
import programFunctions.appData.AppData;
import programFunctions.appData.Cache;
import programFunctions.dataImporter.DataImporter;
import programFunctions.searching.Searcher;
import programFunctions.utilities.Utils;
import java.util.ArrayList;
import java.util.List;
public class ProgramFunctions extends Application {
    /**
     * programData holds the AppData
     */
    private static AppData programData;
    /**
     * utilities holds the Utils
     */
    private static Utils utilities;
    /**
     * query holds the Searcher
     */
    private static Searcher query;
    /**
     * dataImporter holds the DataImporter
     */
    private static DataImporter dataImporter;
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
     * Function definition for checkVersion()
     * <p>
     *     Compares the online version number with the application version number
     *     and alerts if there is a difference
     * </p>
     * @return whether update exists
     */
    public static boolean checkVersion() {
        String onlineVer = utilities.getFileCollector().getVersionOnline();
        String currentVer = programData.getVersionNumber();
        System.out.println("[SYSTEM] online: " + onlineVer + " offline: " + currentVer);
        if(onlineVer.equals(currentVer)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Function definition for checkContainerNameOutput()
     * <p>
     *     Checks whether a name is available for the containers
     * </p>
     * @param name is the name to be tested
     * @return whether it's available
     */
    public static boolean checkContainerName(String name) {
        /*If the input is invalid*/
        if(name == null || name.equals("")) {
            /*Output an error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "013: Invalid input from user");
            System.out.println("[ERROR] 013: Invalid input from user");
            /*Return false*/
            return false;
        }
        /*For all containers*/
        ArrayList<String> tmpI = programData.getCurrentProfile().listContainers();
        for (String aTmpI : tmpI) {
            /*If the name matches an existing container*/
            if (name.equals(aTmpI)) {
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
     * Function definition for createDeck()
     * <p>
     *      Creates a Deck and adds it to the currently
     *      active UserProfile.
     * </p>
     * @param name is the name of the Container
     * @return whether the creation was successful
     */
    public static boolean createDeck(String name) {
        if(name == null || name.equals("")) {
            return false;
        }
        /*If there is an active profile*/
        if (profileActive()) {
            /*For all existing containers in profile*/
            ArrayList<String> tmp = programData.getCurrentProfile().listContainers();
            for (String aTmp : tmp) {
                /*Check name against these containers*/
                System.out.println("[SYSTEM] testing " + name + " against " + aTmp);
                /*If name is taken*/
                if (name.equals(aTmp)) {
                    /*Output error message*/
                    programData.getUserInterface().getBasicWindows().alert("ERROR", "003a: Deck with that name already exists");
                    System.out.println("[ERROR] 003a: Container with that name already exists");
                    /*Return false*/
                    return false;
                }
            }
            /*Otherwise create a deck*/
            Deck deck = new Deck(name);
            /*Add the deck to the profile*/
            programData.getCurrentProfile().addContainer(deck);
            /*Output success*/
            System.out.println("[SYSTEM] Deck created successfully");
            /*Save the profile*/
            utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
            /*Update GUI*/
            programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
            /*Return true*/
            return true;
        }
        /*Otherwise*/
        else {
            /*Output error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for createAlbum()
     * <p>
     *      Creates an Album and adds it to the currently
     *      active UserProfile.
     * </p>
     * @param name is the name of the Container
     * @return whether the creation was successful
     */
    public static boolean createAlbum(String name) {
        if(name == null || name.equals("")) {
            return false;
        }
        if (profileActive()) {
            /*For all existing containers in profile*/
            ArrayList<String> tmp = programData.getCurrentProfile().listContainers();
            for (String aTmp : tmp) {
                /*Check name against these containers*/
                System.out.println("[SYSTEM] testing " + name + " against " + aTmp);
                /*If name is taken*/
                if (name.equals(aTmp)) {
                    /*Output error message*/
                    programData.getUserInterface().getBasicWindows().alert("ERROR", "003a: Container with that name already exists");
                    System.out.println("[ERROR] 003a: Container with that name already exists");
                    /*Return false*/
                    return false;
                }
            }
            /*Otherwise create a deck*/
            Album album = new Album(name);
            /*Add the deck to the profile*/
            programData.getCurrentProfile().addContainer(album);
            /*Output success*/
            System.out.println("[SYSTEM] Album created successfully");
            /*Save the profile*/
            utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
            programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
            /*Return true*/
            return true;
        }
        /*Otherwise*/
        else {
            /*Output error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for deleteContainer()
     * <p>
     *      Deletes a container with a given name.
     * </p>
     * @param name is the name of the container to be deleted
     * @return whether container was deleted successfully
     */
    public static boolean deleteContainer(String name) {
        /*If there is an active profile*/
        if (profileActive()) {
            /*Check the containers for the one we want to delete*/
            ArrayList<String> tmp = programData.getCurrentProfile().listContainers();
            for (String aTmp : tmp) {
                /*Check name against these containers*/
                System.out.println("[SYSTEM] testing " + name + " against " + aTmp);
                /*If name is taken*/
                if (name.equals(aTmp)) {
                    /*Remove the container*/
                    programData.getCurrentProfile().removeContainer(programData.getCurrentProfile().determineContainer(name));
                    /*Output success*/
                    System.out.println("[SYSTEM] Container deleted successfully");
                    /*Update the changes*/
                    utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
                    programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
                    /*Return true*/
                    return true;
                }
            }
            /*Output error message*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "010: Container does not exist");
            System.out.println("[ERROR] 010: Container does not exist");
            /*Return false*/
            return false;

        } else {
            /*Output error message*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for moveCard()
     * <p>
     *     Checks that container 1 exists, and checks that container 2 exists, then checks the card exists.
     *     If all are ok then move the card and re-save the profile.
     * </p>
     * @param con1Name name of initial container
     * @param con2Name name of target container
     * @param cardName name of card
     * @return whether successful
     */
    public static boolean moveCard(String con1Name, String con2Name, String cardName, String cardID) {
        /*If there is an active profile*/
        if(profileActive()) {
            ProgramFunctions.getProgramData().getUserInterface().setVerboseEnabled(false);
            if(!checkContainerName(con1Name) && !checkContainerName(con2Name)) {
                ProgramFunctions.getProgramData().getUserInterface().setVerboseEnabled(true);
                for(Card C : programData.getCurrentProfile().determineContainer(con1Name).getCards()) {
                    if(C.getCardID().equals(cardID) && C.getCardName().equals(cardName)) {
                        if(programData.getCurrentProfile().determineContainer(con2Name).addCard(C)) {
                            programData.getCurrentProfile().determineContainer(con1Name).removeCard(C);
                            utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
                            System.out.println("[SYSTEM] Card " + C.getCardName() + " with SetID " + C.getCardID() + " moved from " + con1Name + " to " + con2Name);
                            programData.getCache().swapCard(programData.getCurrentProfile().determineContainer(con1Name),programData.getCurrentProfile().determineContainer(con2Name), C);
                            return true;
                        }
                        else {
                            programData.getUserInterface().getBasicWindows().alert("ERROR", "014: Deck already contains max copies of that card");
                            System.out.println("[ERROR] 014: Deck already contains max copies of that card");
                            return false;
                        }
                    }
                }
                programData.getUserInterface().getBasicWindows().alert("ERROR", "011: Card not found in container");
                System.out.println("[ERROR] 011: Card not found in container");
                return false;
            }
            else {
                programData.getUserInterface().getBasicWindows().alert("ERROR", "010: Container does not exist");
                System.out.println("[ERROR] 010: Container does not exist");
                return false;
            }
        }
        /*Otherwise*/
        else {
            /*Output error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for removeCard()
     * <p>
     *     Checks that profile is active, that the container exists, and the card exists
     *     and then removes that card from the container.
     * </p>
     * @param cName is the card name
     * @param uName is the container name
     * @return whether the function was successful
     */
    public static boolean removeCard(String cName, String uName, String cID) {
        /*If profile is active*/
        if(profileActive()) {
            ProgramFunctions.getProgramData().getUserInterface().setVerboseEnabled(false);
            if(!checkContainerName(uName)) {
                ProgramFunctions.getProgramData().getUserInterface().setVerboseEnabled(true);
                for(Card C : programData.getCurrentProfile().determineContainer(uName).getCards()) {
                    if(C.getCardName().equals(cName) && C.getCardID().equals(cID)) {
                        programData.getCurrentProfile().determineContainer(uName).removeCard(C);
                        utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
                        System.out.println("[SYSTEM] Card " + C.getCardName() + " with setID " + C.getCardID() + " removed from " + uName);
                        programData.getCache().removeCard(programData.getCurrentProfile().determineContainer(uName), C);
                        return true;
                    }
                }
                programData.getUserInterface().getBasicWindows().alert("ERROR", "011: Card not found in container");
                System.out.println("[ERROR] 011: Card not found in container");
                return false;
            }
            else {
                /*Output error*/
                programData.getUserInterface().getBasicWindows().alert("ERROR", "010: Container does not exist");
                System.out.println("[ERROR] 010: Container does not exist");
                /*Return false*/
                return false;
            }
        }
        /*Otherwise*/
        else {
            /*Output error*/
            programData.getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for createSeries()
     * <p>
     *      Creates a Series
     * </p>
     * @param name is the name of the Container
     * @return whether the creation was successful
     */
    public static boolean createSeries(String name) {
        /*For all existing containers in profile*/
        List<String> tmp = utilities.getFileHandler().searchSeriesFolder();
        for (String aTmp : tmp) {
            /*Check name against these containers*/
            System.out.println("[SYSTEM] testing " + name + " against " + aTmp);
            if (name.equals(aTmp)) {
                /*Output error*/
                programData.getUserInterface().getBasicWindows().alert("ERROR", "005: Series already exists");
                System.out.println("[ERROR] 005: Series already exists");
                /*Return false*/
                return false;
            }
        }
        /*Otherwise create a deck*/
        Series series = new Series(name);
        /*Output success*/
        System.out.println("[SYSTEM] Series created successfully");
        /*Save the profile*/
        utilities.getFileHandler().saveSeries(series);
        /*Return true*/
        return true;
    }
    /**
     * Function definition for deleteSeries()
     * <p>
     *      Deletes a Series with a given name.
     * </p>
     * @param name the name of the series to be deleted
     * @return whether deletion was successful or not
     */
    public static boolean deleteSeries(String name) {
        /*Update the user list*/
        List<String> list = utilities.getFileHandler().searchSeriesFolder();
        /*For all elements in the list*/
        for (String aList : list) {
            /*If name equals the element*/
            if ((name).equals(aList)) {
                /*Delete the series*/
                utilities.getFileHandler().deleteSeries(name);
                /*Output success*/
                System.out.println("[SYSTEM] Series deleted successfully");
                /*Return true*/
                return true;
            }
        }
        /*Output error*/
        programData.getUserInterface().getBasicWindows().alert("ERROR", "006: Series not found");
        System.out.println("[ERROR] 006: Series not found");
        /*Return false*/
        return false;
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
    /**
     * Function definition for getProgramData()
     * <p>
     *     Return the programData
     * </p>
     * @return programData
     */
    public static AppData getProgramData() {
        return programData;
    }
    /**
     * Function definition for getUtilities()
     * <p>
     *     Return utilities
     * </p>
     * @return utilities
     */
    public static Utils getUtilities() {
        return utilities;
    }
    /**
     * Function definition for getQuery()
     * <P>
     *     Return query
     * </P>
     * @return query
     */
    public static Searcher getQuery() {
        return query;
    }
    /**
     * Function definition for getDataImporter()
     * <p>
     *     Return the dataImporter
     * </p>
     * @return dataImporter
     */
    public static DataImporter getDataImporter() {
        return dataImporter;
    }
    /**
     * Function definition for findCard()
     * <p>
     *     Finds a Card with the given name
     * </p>
     * @param name is the name of the card
     * @return the Card itself
     */
    public static Card findCard(String name) {
        for(Card C : programData.getCache().getSystemCards()) {
            if(C.getCardName().equals(name)) {
                return C;
            }
        }
        return null;
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
                if(user.getProfileSettings().isHasPassword()) {
                    if(!utilities.getAuthenticator().authenticate(programData.getUserInterface().getBasicWindows().passwordInput("Enter Password..."), user.getProfileSettings().getPassword())){
                        System.out.println("[ERROR] Password does not match");
                        return false;
                    }
                }
                /*Set the active profile to the given one*/
                programData.setCurrentProfile(user);
                /*Output success*/
                System.out.println("[SYSTEM] Set " + programData.getCurrentProfile().getProfileName() + " as active profile");
                /*
                DEBUG CODE
                 */
                //utilities.getGenerator().genSDREAlbum();

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
    /**
     * Function definition for renameContaienr()
     * <p>
     *     Handles the renaming of a container, checking that the new name is not
     *     already in use by another container
     * </p>
     * @param containerName is the current name of the contaienr
     * @param newName is the new name for the container
     */
    public static void renameContainer(String containerName, String newName) {
        if(checkContainerName(newName)) {
            /*Update the container name*/
            programData.getCurrentProfile().determineContainer(containerName).setContainerName(newName);
            /*Save the profile*/
            utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
            /*Output success*/
            System.out.println("[SYSTEM] Name changed successfully");
            /*Update the Scene*/
            programData.getUserInterface().updateScene(programData.getUserInterface().getBeginningScene());
        }
        else {
            programData.getUserInterface().getBasicWindows().alert("ERROR", "003a: Container already exists");
            System.out.println("[ERROR] 003a: Container already exists");
        }
    }
    /**
     * Function definition for renameUser()
     * <P>
     *     Handles the renaming of the current user, checking whether the name is
     *     already in use
     * </P>
     * @param newName is the new name for the profile
     */
    public static void renameUser(String newName) {
        if(checkUser(newName)) {
            /*Is the name of the current profile*/
            String tmp = programData.getCurrentProfile().getProfileName();
            /*Attempts to change the name of the profile*/
            programData.getCurrentProfile().setProfileName(newName);
            /*Saves the new profile*/
            utilities.getFileHandler().saveUserProfile(programData.getCurrentProfile());
            /*Deletes the old user*/
            deleteProfile(tmp);
            /*Update the title of the main window*/
            programData.getUserInterface().updateTitle();
        }
        else {
            System.out.println("[ERROR] 001a: Profile already exists");
            programData.getUserInterface().getBasicWindows().alert("ERROR", "Profile with that name already exists");
        }
    }
    /**
     * Function definition for exit()
     * <p>
     *      Closes the application completely.
     * </p>
     */
    public static void exit() {
        System.exit(0);
    }
    /**
     * Function definition for start()
     * <p>
     * Creates all the nodes for the mainStage and then shows the mainStage
     * <p>
     * @param S is a Stage
     */
    public void start(Stage S) throws Exception {
        /*Creates a new base data scheme for the program*/
        utilities = new Utils();
        programData = new AppData();
        query = new Searcher();
        dataImporter = new DataImporter();

        /*
        SET UP DATA ON FIRST RUN
         */
        //utilities.getGenerator().genBanList();

        /*Setup the UI*/
        programData.getUserInterface().run(S);
    }
    /**
     * Function definition for main()
     * <p>
     * Launches the application
     * <p>
     * @param args are the launch arguments
     */
    public static void main(String[] args) {
        /*Launches the application*/
        Application.launch(args);
    }
}
