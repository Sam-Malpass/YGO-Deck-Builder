/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.ProfileSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ResourceBundle;
public class ProfileSettingsController implements Initializable {
    /**
     * passwordCheck shows whether a password is in use
     */
    @FXML private CheckBox passwordCheck;
    /**
     * unownedCheck shows whether the user is using cards they don't own
     */
    @FXML private CheckBox unownedCheck;
    /**
     * deckCheck shows whether the user wants to include their decks in searches and building
     */
    @FXML private CheckBox deckCheck;
    /**
     * changePass holds the button to change password
     */
    @FXML private Button changePass;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProfileSettings settings = ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings();
        if(settings.isHasPassword()) {
            passwordCheck.setSelected(true);
            passwordCheck.setText("Enabled");
            changePass.setVisible(true);
        }
        else{
            passwordCheck.setSelected(false);
            passwordCheck.setText("Disabled");
            changePass.setVisible(false);
        }
        if(settings.isIncludeUnowned()) {
            unownedCheck.setSelected(true);
            unownedCheck.setText("Enabled");
        }
        else {
            unownedCheck.setSelected(false);
            unownedCheck.setText("Disabled");
        }
        if(settings.isIncludeDecks()) {
            deckCheck.setSelected(true);
            deckCheck.setText("Enabled");
        }
        else {
            deckCheck.setSelected(false);
            deckCheck.setText("Disabled");
        }
    }
    /**
     * Function definition for cancel()
     * <p>
     *     Returns to the beginningScene
     * </p>
     */
    @FXML
    private void cancel(){
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
    }
    /**
     * Function definition for delete()
     * <p>
     *     Allows the user to delete the profile
     * </p>
     */
    @FXML
    private void delete(){
        if (ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().yesNo("Delete Profile...", "Are you sure?")) {
            ProgramFunctions.deleteProfile(ProgramFunctions.getProgramData().getCurrentProfile().getProfileName());
            ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getDefaultScene());
        }
    }
    /**
     * Function definition for changePassword()
     * <p>
     *     Changes the user's password
     * </p>
     */
    @FXML
    private void changePassword() {
        ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setPassword(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().passwordInput("Input New Password..."));
        ProgramFunctions.getUtilities().getFileHandler().saveUserProfile(ProgramFunctions.getProgramData().getCurrentProfile());
    }
    /**
     * Function definition for rename()
     * <p>
     *     Renames the profile
     * </p>
     */
    @FXML
    private void rename() {
        String x = ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Rename Profile...", "Input new name:");
        if(ProgramFunctions.checkUser(x)) {
            ProgramFunctions.getProgramData().getCurrentProfile().setProfileName(x);
            ProgramFunctions.getUtilities().getFileHandler().saveUserProfile(ProgramFunctions.getProgramData().getCurrentProfile());
            ProgramFunctions.getProgramData().getUserInterface().updateTitle();
        }
        else {
            System.out.println("[ERROR] 001a: Profile already exists");
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "Profile with that name already exists");
        }
    }
    /**
     * Function definition for apply()
     * <p>
     *     Applies the changes to the settings and reverts to the beginningScene
     * </p>
     */
    @FXML
    private void apply() {
        if (passwordCheck.isSelected()) {
            if (!ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isHasPassword()) {
                ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setHasPassword(true);
                ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setPassword(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().passwordInput("Input Password: "));
            }
        } else {
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setHasPassword(false);
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setPassword(null);
        }

        if (deckCheck.isSelected()) {
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setIncludeDecks(true);
        } else {
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setIncludeDecks(false);
        }

        if (unownedCheck.isSelected()) {
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setIncludeUnowned(true);
        } else {
            ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().setIncludeUnowned(false);
        }
        ProgramFunctions.getUtilities().getFileHandler().saveUserProfile(ProgramFunctions.getProgramData().getCurrentProfile());
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
    }
    /**
     * COLLECTION FOR MENUBAR
     */
    @FXML
    private void newProfile(ActionEvent event) {
        /*Make a profile using user input*/
        ProgramFunctions.createProfile(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Create Profile...", "Input Name:"));
        /*Update title*/
        ProgramFunctions.getProgramData().getUserInterface().updateTitle();
    }
    @FXML
    private void loadProfile(ActionEvent event) {
        /*Load a profile*/
        ProgramFunctions.makeActive(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().boxSelector(ProgramFunctions.getUtilities().getFileHandler().searchUserFolder(), "Select Profile..."));
        /*Update title*/
        ProgramFunctions.getProgramData().getUserInterface().updateTitle();
    }
    @FXML
    private void exit(ActionEvent event) {
        ProgramFunctions.exit();
    }
    @FXML
    private void about(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("About", "Yu-Gi-Oh! Deck Builder by Samuel John Malpass\nVersion : 0.3.0.d");
    }
    @FXML
    private void check(ActionEvent event) {
        if (ProgramFunctions.checkVersion()) {
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("Check for updates...", "No Update Available.");
        } else {
            if (ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().yesNo("Update available", "Would you like to update now?")) {
                /*Download the update*/
            } else {
            }
        }
    }
    @FXML
    private void settings(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getSettingsScene());
    }
}
