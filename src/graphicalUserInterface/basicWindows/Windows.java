/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.basicWindows;
import dataStructure.cardHierarchy.Card;
import graphicalUserInterface.basicWindows.controllers.SearchResultDeckController;
import graphicalUserInterface.basicWindows.controllers.SearchResultSystemController;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;
import programFunctions.searching.SearchResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Windows {
    /**
     * sResult holds a string for user input
     */
    private String sResult;
    /**
     * card holds a Card that can be returned
     */
    private Card card;
    /**
     * result holds a list of strings that can be returned
     */
    private ArrayList<String> result;
    /**
     * items holds a list of strings that can be returned
     */
    private List<String> items;
    /**
     * label holds a string
     */
    private String label;
    /**
     * flag holds a boolean value
     */
    private static boolean flag;
    /**
     * verboseFlag holds whether to output a window
     */
    private boolean verboseFlag = true;
    /**
     * Function definition for getLabel()
     * <p>
     *     Return the label
     * </p>
     * @return label
     */
    public String getLabel() {
        return label;
    }
    /**
     * Function definition for getItems()
     * <p>
     *     Return items
     * </p>
     * @return items
     */
    public List<String> getItems() {
        return items;
    }
    /**
     * Function definition for setsResult()
     * <p>
     *     Set the sResult to the passed value
     * </p>
     * @param sResult is the value to use
     */
    public void setsResult(String sResult) {
        this.sResult = sResult;
    }
    /**
     * Function definition for setFlag()
     * <p>
     *     Set the flag to the passed value
     * </p>
     * @param flag is the value to use
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    /**
     * Function definition for setVerboseFlag()
     * <p>
     *     Sets the verboseFlag to the passed value
     * </p>
     * @param verboseFlag is the value to use
     */
    public void setVerboseFlag(boolean verboseFlag) {
        this.verboseFlag = verboseFlag;
    }
    /**
     * Function definition for alert()
     * <p>
     * Creates an alert window to using the passed titleStr and contentStr
     * <p>
     *
     * @param titleStr   is for the title of the window
     * @param contentStr is for the content of the window
     */
    public void alert(String titleStr, String contentStr) {
        if(verboseFlag==false) {
            return;
        }
        /*Create an Alert object*/
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        /*Set the title of the Alert to titleStr*/
        alert.setTitle(titleStr);
        /*Set the header text of the Alert to null*/
        alert.setHeaderText(null);
        /*Set the content of the Alert to contentStr*/
        alert.setContentText(contentStr);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
        /*Show the Alert*/
        stage.showAndWait();
    }
    /**
     * Function definition for input()
     * <p>
     * Creates a window that is used to return a user input string
     * <p>
     *
     * @param titleStr  is for the title of the window
     * @param headerStr is for the header of the window
     */
    public String input(String titleStr, String headerStr) {
        /*Create input dialog box*/
        TextInputDialog dialog = new TextInputDialog("");
        /*Set the title string*/
        dialog.setTitle(titleStr);
        /*Set the header string*/
        dialog.setHeaderText(headerStr);
        /*Setup the user input*/
        String userInput = "";
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
        /*Create a result*/
        Optional<String> result = dialog.showAndWait();
        /*If the result is there*/
        if (result.isPresent()) {
            /*Set the user input to the result*/
            userInput = result.get();
        }
        /*Return the userInput*/
        return userInput;
    }
    /**
     * Function definition for passwordInput()
     * <p>
     * Creates a window to allow the user to enter passwords
     * </p>
     *
     * @param titleStr is the title of the window
     * @return the password that was input
     */
    public String passwordInput(String titleStr) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Password Pop-Up.fxml"));
            Scene scene = new Scene(root, 400, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titleStr);
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
        return sResult;
    }
    /**
     * Function definition for boxSelector()
     * <p>
     * Converts an ArrayList of strings into a combo box and returns
     * the selected item
     * </p>
     *
     * @param list is the list to be used in the combo box
     * @return the option
     */
    public String boxSelector(List<String> list, String titleStr) {
        items = list;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Box Selector Pop-Up.fxml"));
            Scene scene = new Scene(root, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titleStr);
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
        return sResult;
    }
    /**
     * Function definition for yesNo()
     * <p>
     * Creates and opens a window with a yes/no option set
     * and set the flag accordingly
     * </p>
     *
     * @param title the title of the window
     * @param label the label for the window
     * @return
     */
    public boolean yesNo(String title, String label) {
        this.label = label;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Yes-No Pop-Up.fxml"));
            Scene scene = new Scene(root, 300, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(title);
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
        return flag;
    }
    /**
     * Function definition for searchResult()
     * <p>
     * Displays the results of a search query
     * </p>
     */
    public void searchResult(ArrayList<SearchResult> results) {
        ProgramFunctions.getProgramData().getUserInterface().setResults(results);
        /*If results are null*/
        if (results == null) {
            /*Return*/
            return;
        }
        /*If results is empty*/
        if (results.isEmpty()) {
            /*Throw an alert*/
            alert("Search Results", "No Results Found");
            /*Return*/
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Search Result Pop-Up.fxml"));
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Search Results");
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
    }
    /**
     * Function definition for searchResultDeckBuilder()
     * <p>
     *     Displays the searchResults for the deckBuilder
     * </p>
     * @param results are the results to show
     * @return the selected result
     */
    public ArrayList<String> searchResultDeckBuilder(ArrayList<SearchResult> results) {
        ProgramFunctions.getProgramData().getUserInterface().setResults(results);
        result = new ArrayList<>();
        /*If results are null*/
        if (results == null) {
            /*Return*/
            return result;
        }
        /*If results is empty*/
        if (results.isEmpty()) {
            /*Throw an alert*/
            alert("Search Results", "No Results Found");
            /*Return*/
            return result;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Search Result Deck Pop-Up.fxml"));
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Search Results");
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
        result = SearchResultDeckController.result;
        return result;
    }
    /**
     * Function definition for systemResults()
     * <p>
     *     Displays the passed results
     * </p>
     * @param results to be shown
     * @return the selected Card
     */
    public Card systemResults(ArrayList<SearchResult> results) {
        ProgramFunctions.getProgramData().getUserInterface().setResults(results);
        card = null;
        /*If results are null*/
        if (results == null) {
            /*Return*/
            return card;
        }
        /*If results is empty*/
        if (results.isEmpty()) {
            /*Throw an alert*/
            alert("Search Results", "No Results Found");
            /*Return*/
            return card;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/Search Result Deck Pop-Up.fxml"));
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Search Results");
            stage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
            stage.showAndWait();
        }
        catch (Exception e) {
        }
        card = SearchResultSystemController.result;
        return card;
    }
    /**
     * Function definition for exportDialog()
     * <p>
     * Create a stage and return a file from the FileChooser dialog
     * </P>
     *
     * @return a file
     */
    public File exportDialog() {
        /*Create a stage*/
        Stage export = new Stage();
        /*Create a FileChooser*/
        FileChooser fileChooser = new FileChooser();
        /*Set the title*/
        fileChooser.setTitle("Export Deck...");
        /*Create a file to return*/
        File file = fileChooser.showSaveDialog(export);
        /*Return the file*/
        return file;
    }
    /**
     * Function definition for importDialog()
     * <p>
     * Create a stage and return a file from the FileChooser dialog
     * </p>
     *
     * @return a file
     */
    public File importDialog() {
        /*Create a Stage*/
        Stage importStage = new Stage();
        /*Create a FileChooser*/
        FileChooser fileChooser = new FileChooser();
        /*Set the title*/
        fileChooser.setTitle("Import Deck...");
        /*Create a file to return*/
        File file = fileChooser.showOpenDialog(importStage);
        /*Return the file*/
        return file;
    }
}
