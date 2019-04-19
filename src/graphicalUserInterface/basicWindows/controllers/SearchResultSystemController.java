/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.basicWindows.controllers;
import dataStructure.cardHierarchy.Card;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;
import programFunctions.searching.SearchResult;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchResultSystemController implements Initializable {
    /**
     * list holds a list of results
     */
    @FXML private ListView<String> list;
    /**
     * Close holds the close button
     */
    @FXML private Button close;
    public static Card result = null;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<SearchResult> results = ProgramFunctions.getProgramData().getUserInterface().getResults();
        result = null;
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();
            }
        });

        ArrayList<String> resultsOutput = ProgramFunctions.getUtilities().getOutputter().outputSearchResults(results);
        ObservableList<String> containers = list.getItems();
        for(String C : resultsOutput) {
            containers.add(C);
        }
        /*Set the cell factory*/
        list.setCellFactory(lv -> {
            /*Make a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Make a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            /*Make a MenuItem*/
            MenuItem view = new MenuItem("View...");
            /*Set an action*/
            view.setOnAction(event -> ProgramFunctions.getProgramData().getUserInterface().viewCard(ProgramFunctions.findCard(results.get(cell.getIndex()).getCardName())));
            /*Make a MenuItem*/
            MenuItem move = new MenuItem("Select...");
            /*Set an action*/
            move.setOnAction(event -> {
                String item = cell.getItem();
                result = ProgramFunctions.getUtilities().getFileHandler().loadSeries(item.substring(item.indexOf("(")+1, item.indexOf(")"))).findCard(item.substring(0, item.indexOf(" (")));
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();
            });
            /*Make a MenuItem*/
            MenuItem cancel = new MenuItem("Cancel");
            /*Set an action*/
            cancel.setOnAction(event -> {
                /*Do nothing*/
            });
            /*Add all MenuItems to context menu*/
            contextMenu.getItems().addAll(view, move, cancel);
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
}
