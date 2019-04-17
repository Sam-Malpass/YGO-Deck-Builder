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
import javafx.scene.control.ListView;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ImportSceneController implements Initializable {
    /**
     * missing holds the list of missing cards
     */
    @FXML private ListView missing;
    /**
     * using holds the list of cards and their locations that the application will use
     */
    @FXML private ListView using;
    /**
     * cancel holds the cancel button
     */
    @FXML private Button cancel;
    /**
     * confirm holds the confirm button
     */
    @FXML private Button confirm;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProgramFunctions.getGUI().getData().setCardSuggestor(null);
        String name = "test";
        ArrayList<String> tst = ProgramFunctions.resultsToString(ProgramFunctions.processImportResults(ProgramFunctions.getGUI().getData().getAvailable()));
        ObservableList<String> owned = using.getItems();
        for(String C : tst) {
            owned.add(C);
        }
        ObservableList<String> missed = missing.getItems();
        for(String C : ProgramFunctions.getGUI().getData().getMissing()) {
            missed.add(C);
        }
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ProgramFunctions.getGUI().yesNoWindow("Are You Sure?", "Leave without updating changes?")) {
                    ProgramFunctions.getGUI().getData().setCardSuggestor(null);
                    ProgramFunctions.updateGUI(ProgramFunctions.getBeginningScene());
                } else {
                    return;
                }
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ProgramFunctions.getGUI().yesNoWindow("Are You Sure?", "This will move cards from the album(s)")) {
                    ProgramFunctions.createDeck(name);
                    for(String S : tst) {
                        ProgramFunctions.moveCard(S.substring(S.indexOf("in ")).replace("in ", ""), name,S.substring(0, S.indexOf(" (")).replace(" (", ""),S.substring(S.indexOf("("), S.indexOf(")")).replace("(", "").replace(")", ""));
                    }
                    ProgramFunctions.getGUI().getData().setCardSuggestor(new CardSuggestor((Deck)ProgramFunctions.getCurrentProfile().determineContainer(name), 2, 1, 1));
                    ProgramFunctions.getGUI().updateScene(ProgramFunctions.getDeckBuilderScene());
                }
                else {
                    return;
                }
            }
        });
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