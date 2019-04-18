/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import programFunctions.ProgramFunctions;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class DeckBuilderController implements Initializable {
    /**
     * contentsList holds the current contents
     */
    @FXML private ListView<String> contentsList;
    /**
     * suggestionTexts holds the suggestion information
     */
    @FXML private TextArea suggestionText;
    /**
     * infoText holds the deck's information
     */
    @FXML private TextArea infoText;
    /**
     * suggestionList holds the list of card suggestions
     */
    private ArrayList<Card> suggestionList;
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
        thing =url;
        test = resourceBundle;
        contentsList.getItems().removeAll();
        ObservableList<String> checkers = contentsList.getItems();
        for(String C : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().listCardsString()) {
            checkers.add(C);
        }
        suggestionList = new ArrayList<>();
        if(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().getOnlyDeck().size() > 0) {
            suggestionList = ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getSuggestions();
        }
        String exe = "";
        for(Card c  : suggestionList) {
            exe = exe + c.getCardName() + "\n-> " +  "\"" + c.getCardDescription() + "\"\n";
        }
        suggestionText.setText(exe);
        suggestionText.setWrapText(true);
        String info;
        if(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getStatusFlag() == 0) {
            info = "DECK NOT AT MINIMUM LIMIT";
        }
        else if(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getStatusFlag() == 1) {
            info = "DECK CONTAINS LIMITED CARDS";
        }
        else {
            info = "OKAY";
        }
        info = info + "\nNumber of cards in deck: " + ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().getOnlyDeck().size();
        info = info + "\nNumber of cards in extra deck: " + ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().getExtraDeck().size();
        infoText.setText(info);
        ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().handleSuggestion(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().determineNextCard());
    }
    private void suggest() {
        suggestionList = new ArrayList<>();
        if(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().getOnlyDeck().size() > 0) {
            suggestionList = ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getSuggestions();
        }
        String exe = "";
        for(Card c  : suggestionList) {
            exe = exe + c.getCardName() + "\n-> " +  "\"" + c.getCardDescription() + "\"\n";
        }
        suggestionText.setText(exe);
        suggestionText.setWrapText(true);
    }
    /**
     * Function definition for cancel()
     * <p>
     *     Asks for confirmation and then reacts appropriately
     * </p>
     */
    @FXML
    private void cancel() {
        if (ProgramFunctions.getProgramData().getUserInterface().yesNoWindow("Are You Sure?", "Leave without updating changes?")) {
            ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCardSuggestor(null);
            ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
        } else {
            return;
        }
    }
    /**
     * Function definition for save()
     * <p>
     *     Saves the deck and overrides the deck already in existence
     * </p>
     */
    @FXML
    private void save() {
        ArrayList<Container> containers = new ArrayList<>();
        for(Container c : ProgramFunctions.getProgramData().getCurrentProfile().getUserContainers()) {
            if(c instanceof Album) {
                for(Album a : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().getAlbums()) {
                    if(a.getContainerName().equals(c.getContainerName())) {
                        containers.add(a);
                    }
                }
            }
            else {
                for(Deck a : ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().getDecks()) {
                    if(a.getContainerName().equals(c.getContainerName())) {
                        containers.add(a);
                    }
                }
            }
        }
        ProgramFunctions.getProgramData().getCurrentProfile().setContainers(containers);
        ProgramFunctions.getUtilities().getFileHandler().saveUserProfile(ProgramFunctions.getProgramData().getCurrentProfile());
        ProgramFunctions.getProgramData().getCache().updateCache();
    }
    /**
     * Function definition for add()
     * <p>
     *     Opens dialogue to find and add a card
     * </p>
     */
    @FXML
    private void add() {
        ArrayList<String> command = new ArrayList<>();
        command = ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().searchResult(DeckBuilder(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().searchCard(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Search for Card", "Search:"))));
        if(command.size() > 0) {
            if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeUnowned() != true) {
                for(Card c : ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(command.get(0)).getCards()) {
                    if(c.getCardName().equals(command.get(1)) && c.getCardID().equals(c.getCardID())) {
                        ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().addCard(c);
                        ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().swapCard(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().determineContainer(command.get(0)),ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().determineContainer(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().getContainerName()),c);
                    }
                }
            }
            else {
                ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().addCard(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getCpyCache().getSystemCards().get(Integer.parseInt(command.get(0))));
            }
            contentsList.setItems(FXCollections.observableArrayList(ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getCardSuggestor().getTmpDeck().listCardsString()));
            contentsList.refresh();
            suggest();
        }
        else {
        }
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
