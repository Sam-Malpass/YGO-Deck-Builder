/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ResourceBundle;
public class GlobalSettingsController implements Initializable {
    /**
     * siteBox holds the site selector
     */
    @FXML private ComboBox siteBox;
    /**
     * regBox holds the region selector
     */
    @FXML private ComboBox regBox;
    /**
     * cancel holds the cancel button
     */
    @FXML private Button cancel;
    /**
     * apply holds the apply button
     */
    @FXML private Button apply;
    /**
     * ok holds the ok button
     */
    @FXML private Button ok;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> sites = siteBox.getItems();
        sites.add("Card Market");
        sites.add("Ebay");
        ObservableList<String> reg = regBox.getItems();
        reg.add("UK");
        reg.add("US");
        reg.add("DE");
        reg.add("FR");

        siteBox.getSelectionModel().select(ProgramFunctions.getGlobalSettings().getShoppingSite());
        regBox.getSelectionModel().select(ProgramFunctions.getGlobalSettings().getRegion());

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ProgramFunctions.profileActive()) {
                    ProgramFunctions.updateGUI(ProgramFunctions.getGUI().getBeginningScene());
                }
                else {
                    ProgramFunctions.updateGUI(ProgramFunctions.getGUI().getDefaultScene());
                }
            }
        });
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveSettings();
            }
        });
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveSettings();
                if(ProgramFunctions.profileActive()) {
                    ProgramFunctions.updateGUI(ProgramFunctions.getGUI().getBeginningScene());
                }
                else {
                    ProgramFunctions.updateGUI(ProgramFunctions.getGUI().getDefaultScene());
                }
            }
        });
    }
    /**
     * Function definition for saveSettings()
     * <p>
     *     Applies the selected settings
     * </p>
     */
    private void saveSettings() {
        ProgramFunctions.getGlobalSettings().setRegion(lookup(regBox.getValue().toString()));
        ProgramFunctions.getGlobalSettings().setShoppingSite(lookup(siteBox.getValue().toString()));
    }
    /**
     * Function definition for lookup()
     * <p>
     *     Converts selected strings to the appropriate integer
     * </p>
     * @param value is the string to lookup
     * @return the integer
     */
    private int lookup(String value) {
        if(value.equals("Card Market")) {
            return 0;
        }
        else if(value.equals("Ebay")) {
            return 1;
        }
        else if(value.equals("UK")) {
            return 0;
        }
        else if(value.equals("US")) {
            return 1;
        }
        else if(value.equals("DE")) {
            return 2;
        }
        else if(value.equals("FR")) {
            return 3;
        }
        else {
            return 0;
        }
    }
    /**
     * COLLECTION FOR MENUBAR
     */
    @FXML
    private void newProfile(ActionEvent event) {
        /*Make a profile using user input*/
        ProgramFunctions.createProfile(ProgramFunctions.showInput("Create Profile...", "Input Name:"));
        /*Update title*/
        ProgramFunctions.updateTitle();
    }
    @FXML
    private void loadProfile(ActionEvent event) {
        /*Load a profile*/
        ProgramFunctions.makeActive(ProgramFunctions.showSelector(ProgramFunctions.searchUserFolder(), "Select Profile..."));
        /*Update title*/
        ProgramFunctions.updateTitle();
    }
    @FXML
    private void exit(ActionEvent event) {
        ProgramFunctions.exit();
    }
    @FXML
    private void about(ActionEvent event) {
        ProgramFunctions.showAlert("About", "Yu-Gi-Oh! Deck Builder by Samuel John Malpass\nVersion : 0.3.0.d");
    }
    @FXML
    private void check(ActionEvent event) {
        if (ProgramFunctions.checkVersion()) {
            ProgramFunctions.showAlert("Check for updates...", "No Update Available.");
        } else {
            if (ProgramFunctions.showYesNo("Update available", "Would you like to update now?")) {
                /*Download the update*/
            } else {
            }
        }
    }
    @FXML
    private void settings(ActionEvent event) {
        ProgramFunctions.updateGUI(ProgramFunctions.getSettingsScene());
    }
}
