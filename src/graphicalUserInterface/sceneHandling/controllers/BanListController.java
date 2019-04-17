/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ResourceBundle;
public class BanListController implements Initializable {
    /**
     * banList holds a list of banned and limited cards
     */
    @FXML private ListView<String> banList;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> bans = banList.getItems();
        for(String C : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getBanList()) {
            bans.add(C);
        }
        banList.setCellFactory(lv -> {
            /*Make a ListCell*/
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        return;
                    }
                    System.out.println(item.toString());
                    if (item.contains("SEMI-LIMITED")) {
                        setStyle("-fx-background-color: darkseagreen;");
                    } else if (item.contains("LIMITED")) {
                        setStyle("-fx-background-color: lightyellow;");
                    } else {
                        setStyle("-fx-background-color: salmon;");
                    }

                }
            };
            /*Bind the itemProperty to the textProperty*/
            cell.textProperty().bind(cell.itemProperty());
            /*If the cell was empty*/
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    /*Remove the context menu*/
                    cell.setContextMenu(null);
                    cell.setStyle("-fx-background-color: white;");
                    /*Otherwise*/
                } else {
                }
            });
            /*Return the cell*/
            return cell;
        });
    }
    /**
     * Function definition for back()
     * <p>
     *     Returns to the beginningScene
     * </p>
     */
    @FXML
    private void back() {
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
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getSettingsScene());)
    }
}
