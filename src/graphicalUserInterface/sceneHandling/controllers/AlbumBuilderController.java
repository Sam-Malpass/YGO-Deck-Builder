/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ResourceBundle;
public class AlbumBuilderController implements Initializable {
    /**
     * cardList holds the list of cards
     */
    @FXML private ListView<String> cardList;
    /**
     * infoArea holds the information for the album
     */
    @FXML private TextArea infoArea;
    /**
     * album holds the Album
     */
    @FXML private Album album;
    /**
     * thing holds the URL
     */
    private URL thing;
    /**
     * test holds the ResourceBundle
     */
    private ResourceBundle test;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thing = url;
        test = resourceBundle;
        album = ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getAlbum();
        getOverviewData();
        ObservableList<String> cards = cardList.getItems();
        for(String C : ProgramFunctions.getUtilities().getOutputter().outputCardList(album.getCards())) {
            cards.add(C);
        }
        cardList.setCellFactory(lv -> {
            /*Create a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Create a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            MenuItem view = new MenuItem("View...");
            view.setOnAction(event -> ProgramFunctions.getProgramData().getUserInterface().viewCard(ProgramFunctions.findCard(cell.getItem())));
            MenuItem findAll = new MenuItem("Find All...");
            findAll.setOnAction(event -> ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().searchResult((ProgramFunctions.getQuery().searchCard(cell.getItem()))));
            MenuItem cancel = new MenuItem("Cancel");
            cancel.setOnAction(event -> {

            });
            contextMenu.getItems().addAll(view, findAll, cancel);
            /*Set the cell text property*/
            cell.textProperty().bind(cell.itemProperty());
            /*Determine if the cell is empty*/
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                /*If it is*/
                if (isNowEmpty) {
                    /*Set the context menu to null*/
                    cell.setContextMenu(null);
                    /*Otherwise*/
                } else {
                    /*Apply the context menu*/
                    cell.setContextMenu(contextMenu);
                }
            });
            /*Return the cell*/
            return cell;
        });
    }
    /**
     * Function definition for getOverviewData()
     * <p>
     *     Calculates the overview data of the album and puts it in the appropriate text area
     * </p>
     */
    private void getOverviewData() {
        infoArea.setWrapText(true);
        infoArea.clear();
        int numSpells = ProgramFunctions.getUtilities().getFilter().filterSpells(album.getCards()).size();
        int numTraps = ProgramFunctions.getUtilities().getFilter().filterTraps(album.getCards()).size();
        int numMonsters = ProgramFunctions.getUtilities().getFilter().filterMonsters(album.getCards()).size();
        infoArea.appendText("Name: " + album.getContainerName() + "\n");
        infoArea.appendText("Number of Spells: " + numSpells + "\n");
        infoArea.appendText("Number of Traps: " + numTraps + "\n");
        infoArea.appendText("Number of Monsters: " + numMonsters + "\n");
        infoArea.appendText("TOTAL CARDS: " + album.getCards().size() + "\n");
    }
    /**
     * Function definition for addCard()
     * <p>
     *     Handles the adding of a card to this album
     * </p>
     */
    @FXML
    private void addCard() {
        Card X = ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().systemResults(ProgramFunctions.getQuery().searchSystem(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Search for...", "Search:")));
        if(X == null) {
        }
        else {
            album.addCard(X);
            cardList.setItems(FXCollections.observableArrayList(ProgramFunctions.getUtilities().getOutputter().outputCardList(album.getCards())));
            cardList.refresh();
        }
    }
    /**
     * Function definition for cancel()
     * <p>
     *     Asks if user is sure and reacts appropriately
     * </p>
     */
    @FXML
    private void cancel() {
        if (ProgramFunctions.getProgramData().getUserInterface().yesNoWindow("Are You Sure?", "Leave without updating changes?")) {
            ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setAlbum(null);
            ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
        } else {

        }
    }
    /**
     * Function definition for save()
     * <p>
     *     Overrides the object in the users collection with the new object
     * </p>
     */
    @FXML
    private void save() {
        ProgramFunctions.getProgramData().getCurrentProfile().removeContainer(ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(album.getContainerName()));
        ProgramFunctions.getProgramData().getCurrentProfile().addContainer(album);
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
        ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("About", "Yu-Gi-Oh! Deck Builder by Samuel John Malpass\nVersion : " + ProgramFunctions.getProgramData().getVersionNumber());
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
