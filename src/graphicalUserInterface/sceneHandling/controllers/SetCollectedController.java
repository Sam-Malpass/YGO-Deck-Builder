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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class SetCollectedController implements Initializable {
    /**
     * collected holds a list of collected cards
     */
    @FXML private ListView<String> collected;
    /**
     * uncollected holds a list of uncollected cards
     */
    @FXML private ListView<String> uncollected;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> checkers = collected.getItems();
        for(String C : ProgramFunctions.getUtilities().getOutputter().outputCardList(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCollected())) {
            checkers.add(C);
        }
        ObservableList<String> checkers2 = uncollected.getItems();
        for(String C : ProgramFunctions.getUtilities().getOutputter().outputCardList(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getUncollected())) {
            checkers2.add(C);
        }
        collected.setCellFactory(lv -> {
            /*Make a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Make a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            MenuItem view = new MenuItem("View...");
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ProgramFunctions.getProgramData().getUserInterface().viewCard(ProgramFunctions.findCard(cell.getItem()));
                }
            });
            MenuItem findAll = new MenuItem("Find All...");
            findAll.setOnAction(event -> ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().searchResult((ProgramFunctions.getQuery().searchCard(cell.getItem()))));
            MenuItem cancel = new MenuItem("Cancel");
            contextMenu.getItems().addAll(view, findAll, cancel);
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
                }
            });
            /*Return the cell*/
            return cell;
        });
        uncollected.setCellFactory(lv -> {
            /*Make a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Make a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            MenuItem view = new MenuItem("View...");
            view.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ProgramFunctions.getProgramData().getUserInterface().viewCard(ProgramFunctions.findCard(cell.getItem()));
                }
            });
            MenuItem findOnline = new MenuItem("Find Online...");
            findOnline.setOnAction(event -> ProgramFunctions.getUtilities().getPricing().findOnline(cell.getItem(), ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCollectedSetID()));
            MenuItem cancel = new MenuItem("Cancel");
            contextMenu.getItems().addAll(view, findOnline, cancel);
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
                }
            });
            /*Return the cell*/
            return cell;
        });
    }
    /**
     * Function definition for back()
     * <p>
     *     Goes back to the checklistScene
     * </p>
     */
    @FXML private void back() {
        ArrayList<String> tst = new ArrayList<>();
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getChecklistScene(tst));
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
