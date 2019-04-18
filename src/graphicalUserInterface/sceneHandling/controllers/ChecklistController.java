/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.cardHierarchy.Card;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ChecklistController implements Initializable {
    /**
     * checkList holds a list of the sets and the percentage collected
     */
    @FXML private ListView<String> checkList;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> checkers = checkList.getItems();
        for(String C : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getChecklist()) {
            checkers.add(C);
        }
        checkList.setCellFactory(lv -> {
            /*Make a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Make a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            MenuItem view = new MenuItem("View...");
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String name = cell.getItem().substring(0, cell.getItem().indexOf(" "));
                    ArrayList<Card> tmpI = ProgramFunctions.getUtilities().getChecker().checklistCollected(name);
                    ArrayList<Card> tmpII = ProgramFunctions.getUtilities().getChecker().checklistUncollected(tmpI, name);
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCollected(tmpI);
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setUncollected(tmpII);
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCollectedSetID(name);
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getViewSetCollected());
                }
            });
            MenuItem cancel = new MenuItem("Cancel");
            contextMenu.getItems().addAll(view, cancel);
            /*Bind the itemProperty to the textProperty*/
            cell.textProperty().bind(cell.itemProperty());
            /*If the cell was empty*/
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    /*Remove the context menu*/
                    cell.setContextMenu(null);
                    /*Otherwise*/
                } else {
                    /*Add the context menu*/
                    cell.setContextMenu(contextMenu);
                    float percentage = Float.parseFloat(cell.getItem().substring(cell.getItem().indexOf("\t ")).replace("%", ""));
                    if (percentage >= 75) {
                        cell.setStyle("-fx-background-color: darkseagreen;");
                    } else if (percentage < 75 && percentage >= 50) {
                        cell.setStyle("-fx-background-color: lightyellow;");
                    } else if (percentage < 50 && percentage >= 0) {
                        cell.setStyle("-fx-background-color: salmon;");
                    }
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
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getSettingsScene());
    }
}
