/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.containerHierarchy.Deck;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import programFunctions.ProgramFunctions;
import programFunctions.builder.DeckBuilder;
import programFunctions.searching.SearchResult;

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
        ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCardSuggestor(null);
        String name = "test";
        ArrayList<String> tst = ProgramFunctions.getUtilities().getOutputter().outputSearchResults(processImportResults(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getAvailable()));
        ObservableList<String> owned = using.getItems();
        for(String C : tst) {
            owned.add(C);
        }
        ObservableList<String> missed = missing.getItems();
        for(String C : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getMissing()) {
            missed.add(C);
        }
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ProgramFunctions.getProgramData().getUserInterface().yesNoWindow("Are You Sure?", "Leave without updating changes?")) {
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCardSuggestor(null);
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
                } else {
                    return;
                }
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ProgramFunctions.getProgramData().getUserInterface().yesNoWindow("Are You Sure?", "This will move cards from the album(s)")) {
                    ProgramFunctions.createDeck(name);
                    for(String S : tst) {
                        ProgramFunctions.moveCard(S.substring(S.indexOf("in ")).replace("in ", ""), name,S.substring(0, S.indexOf(" (")).replace(" (", ""),S.substring(S.indexOf("("), S.indexOf(")")).replace("(", "").replace(")", ""));
                    }
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCardSuggestor(new DeckBuilder((Deck)ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(name), 2, 1, 1));
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getDeckBuilder());
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
    /**
     * Function definition for processImportResults()
     * <p>
     *     Processes the results of the importDeck search feature, essentially
     *     preventing double searching cards.
     * </p>
     * @param available is the list needed to be checked
     * @return the final result
     */
    private ArrayList<SearchResult> processImportResults(ArrayList<String> available) {
        /*Make a temp ArrayList*/
        ArrayList<SearchResult> test = new ArrayList<>();
        /*Make a temp ArrayList*/
        ArrayList<String> dummy = new ArrayList<>();
        /*Make a flag*/
        boolean flag = false;
        /*For all elements in the available list*/
        for (String anAvailable : available) {
            /*For all elements in the temp ArrayList*/
            for (String s : dummy) {
                /*If the element matches*/
                if (s.equals(anAvailable)) {
                    /*Set the flag to true*/
                    flag = true;
                }
            }
            /*If the flag is false*/
            if (!flag) {
                /*Create a temp ArrayList*/
                ArrayList<SearchResult> locationBois = ProgramFunctions.getQuery().searchCard(anAvailable);
                /*Add to the temp ArrayList*/
                dummy.add(anAvailable);
                /*For all elements in the temp ArrayList*/
                /*Add to the temp ArrayList*/
                test.addAll(locationBois);
            }
            /*Reset the flag*/
            flag = false;
        }
        return test;
    }
}
