/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Deck;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import programFunctions.ProgramFunctions;
import programFunctions.builder.DeckBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
public class BeginningController implements Initializable {
    /**
     * containerList holds a list of the user's containers
     */
    @FXML private ListView<String> containerList;
    /**
     * cardList holds a list of the user's cards
     */
    @FXML private ListView<String> cardList;
    /**
     * searchCard holds the search bar
     */
    @FXML private TextField searchCard;
    /**
     * profDetailsBox holds the details of the profile
     */
    @FXML private TextArea profDetailsBox;
    /**
     * sortBox holds the card sorting box
     */
    @FXML private ChoiceBox sortBox;
    /**
     * deck is a selected deck
     */
    public static Deck deck;
    /**
     * album is a selected album
     */
    public static Album album;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortBox.getItems().addAll("-", "Name A-Z", "Name Z-A", "Set ID", "Value High-Low", "Value Low-High");
        sortBox.setValue("-");
        sortBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(sortBox.getValue().equals("Name A-Z")) {
                    cardList.setItems(cardList.getItems().sorted());
                }
                else if(sortBox.getValue().equals("Name Z-A")) {
                    cardList.setItems(cardList.getItems().sorted(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o2.compareTo(o1);
                        }
                    }));
                }
                else if(sortBox.getValue().equals("Set ID")) {
                    ArrayList<Card> tmp = ProgramFunctions.getProgramData().getCache().getAlbumCards();
                    tmp.addAll(ProgramFunctions.getProgramData().getCache().getDeckCards());
                    ProgramFunctions.getUtilities().getSorter().sortBySet(tmp);
                    cardList.setItems(FXCollections.observableArrayList(ProgramFunctions.getUtilities().getOutputter().outputCardList(tmp)));
                }
                else if(sortBox.getValue().equals("Value High-Low")) {
                    ArrayList<Card> tmp = ProgramFunctions.getProgramData().getCache().getAlbumCards();
                    tmp.addAll(ProgramFunctions.getProgramData().getCache().getDeckCards());
                    ProgramFunctions.getUtilities().getSorter().sortByValue(tmp);
                    cardList.setItems(FXCollections.observableList(ProgramFunctions.getUtilities().getOutputter().outputCardList(tmp)));
                }
                else if(sortBox.getValue().equals("Value Low-High")) {
                    ArrayList<Card> tmp = ProgramFunctions.getProgramData().getCache().getAlbumCards();
                    tmp.addAll(ProgramFunctions.getProgramData().getCache().getDeckCards());
                    ProgramFunctions.getUtilities().getSorter().invertedSortByValue(tmp);
                    cardList.setItems(FXCollections.observableList(ProgramFunctions.getUtilities().getOutputter().outputCardList(tmp)));
                }
                else if(sortBox.getValue().equals("-")) {
                    cardList.setItems(FXCollections.observableList(ProgramFunctions.getProgramData().getCurrentProfile().listAllCards()));
                }
            }
        });
        ObservableList<String> containers = containerList.getItems();
        for(String C : ProgramFunctions.getProgramData().getCurrentProfile().listContainers()) {
            containers.add(C);
        }
        containerList.setCellFactory(lv -> {
            /*Create a ListCell*/
            ListCell<String> cell = new ListCell<>();
            /*Create a ContextMenu*/
            ContextMenu contextMenu = new ContextMenu();
            /*Create a MenuItem*/
            MenuItem open = new MenuItem();
            /*Set the text property*/
            open.textProperty().bind(Bindings.format("Open...", cell.itemProperty()));
            /*Set an action*/
            open.setOnAction(event -> {
                /*Get the cell contents*/
                String item = cell.getItem();
                if (ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item) instanceof Deck) {
                    deck = (Deck) ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item);
                    ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().setCardSuggestor(new DeckBuilder(deck, 2, 1, 1));
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getDeckBuilder());
                } else {
                    album = (Album) ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item);
                    ProgramFunctions.getUtilities().getOutputter().outputStringList(ProgramFunctions.getUtilities().getOutputter().outputCardList(album.getCards()));
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getAlbumBuilder());
                }
            });
            /*Create a MenuItem*/
            MenuItem rename = new MenuItem();
            /*Set the text property*/
            rename.textProperty().bind(Bindings.format("Rename...", cell.itemProperty()));
            /*Set an action*/
            rename.setOnAction(event -> {
                /*Get the cell contents*/
                String item = cell.getItem();
                /*Get a container name*/
                String temp = ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Rename Container...", "Input New Name: ");
                /*If the name is available*/
                ProgramFunctions.renameContainer(item, temp);
                /*Return*/
            });
            /*Create a MenuItem*/
            MenuItem analyze = new MenuItem();
            /*Set the text property*/
            analyze.textProperty().bind(Bindings.format("Analyze...", cell.itemProperty()));
            /*Set an action*/
            analyze.setOnAction(event -> {
                /*Get the cell contents*/
                String item = cell.getItem();
                if (ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item) instanceof Deck) {
                    deck = (Deck) ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item);
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getDeckAnalyzerScene());
                } else {
                    album = (Album) ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(item);
                    ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getAlbumAnalyzer());
                }
            });
            /*Create a MenuItem*/
            MenuItem delete = new MenuItem();
            /*Set the text property*/
            delete.textProperty().bind(Bindings.format("Delete...", cell.itemProperty()));
            /*Set an action*/
            delete.setOnAction(event -> {
                /*Get the cell contents*/
                String item = cell.getItem();
                /*Open a basic window yesNo*/
                if (ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().yesNo("Delete Container...", "Are you sure?")) {
                    /*Delete the container*/
                    ProgramFunctions.deleteContainer(item);
                }
            });
            /*Make a MenuItem*/
            MenuItem cancel = new MenuItem();
            /*Set the text property*/
            cancel.textProperty().bind(Bindings.format("Cancel", cell.itemProperty()));
            /*Set an action*/
            cancel.setOnAction(event -> {
                /*Get the cell contents*/
                String item = cell.getItem();
            });
            /*Add the MenuItems to the context menu*/
            contextMenu.getItems().addAll(open, rename, analyze, delete, cancel);
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

        ObservableList<String> cards = cardList.getItems();
        for(String C : ProgramFunctions.getProgramData().getCurrentProfile().listAllCards()) {
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

        searchCard.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            /*If enter pressed*/
            switch (keyEvent.getCode()) {
                case ENTER:
                    /*If the TextFile contents are not null*/
                    if (searchCard.getText() != null) {
                        /*Open the results of a search*/
                        ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().searchResult((ProgramFunctions.getQuery().searchCard(searchCard.getText())));
                        /*Clear the TextField*/
                        searchCard.clear();
                        /*Return*/
                        return;
                    }
                    /*Output an error*/
                    ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "013: Invalid input from user");
                    System.out.println("[ERROR] 013: Invalid input from user");
                    /*Break*/
                    break;
                /*Default*/
                default:
                    /*Break*/
                    break;
            }
        });
        setupBox();
    }
    /**
     * Function definition for setupBox()
     * <p>
     *     Sets up the profDetailsBox
     * </p>
     */
    private void setupBox() {
        profDetailsBox.setWrapText(true);
        profDetailsBox.appendText("Name: " + ProgramFunctions.getProgramData().getCurrentProfile().getProfileName() + "\n");
        profDetailsBox.appendText("Number of Decks: " + ProgramFunctions.getUtilities().getFilter().filterDecks(ProgramFunctions.getProgramData().getCurrentProfile().getUserContainers()).size() +"\n");
        profDetailsBox.appendText("Number of Albums: " + ProgramFunctions.getUtilities().getFilter().filterAlbums(ProgramFunctions.getProgramData().getCurrentProfile().getUserContainers()).size() + "\n");
        profDetailsBox.appendText("Number of Cards: " + ProgramFunctions.getProgramData().getCurrentProfile().listAllCards().size() + "\n");
    }
    /**
     * Function definition for createDeck()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void createDeck(ActionEvent event) {
        ProgramFunctions.createDeck(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Create Deck", "Input name:"));
    }
    /**
     * Function definition for importButton()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void importButton(ActionEvent event) {
        ProgramFunctions.getUtilities().getImportExport().importDeck();
    }
    /**
     * Function definition for exportButton()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void exportButton(ActionEvent event) {
        ProgramFunctions.getUtilities().getImportExport().exportDeck();
    }
    /**
     * Function definition for createAlbum()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void createAlbum(ActionEvent event) {
        ProgramFunctions.createAlbum(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Create Album", "Input name:"));
    }
    /**
     * Function definition for checklistButton()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void checklistButton(ActionEvent event) {
        ArrayList<String> tst = new ArrayList<>();
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getChecklistScene(tst));
    }
    /**
     * Function definition for profileSettings()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void profileSettings(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getProfSetScene());
    }
    /**
     * Function definition for banlistButton()
     * <p>
     *     Used by the appropriate button
     * </p>
     * @param event
     */
    @FXML
    private void banlistButton(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getForbiddenScene());
    }
    /**
     * Function definition for search()
     * <p>
     *     Gets the searchCard content and searches for that card
     * </p>
     * @param event
     */
    @FXML
    private void search(ActionEvent event) {
        /*If the TextField content is not null*/
        if (searchCard.getText() != null) {
            /*Open the results of a search*/
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().searchResult((ProgramFunctions.getQuery().searchCard(searchCard.getText())));
            /*Clear the TextField*/
            searchCard.clear();
            /*Return*/
            return;
        }
        /*Output an error*/
        ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "013: Invalid input from user");
        System.out.println("[ERROR] 013: Invalid input from user");
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
