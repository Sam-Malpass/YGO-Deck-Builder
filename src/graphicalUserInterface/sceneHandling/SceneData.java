/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling;
import dataStructure.ProfileSettings;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Deck;
import programFunctions.builder.DeckBuilder;

import java.util.ArrayList;
public class SceneData {
    /**
     * available is used to show a list of available cards
     */
    private ArrayList<String> available;
    /**
     * missing is used to show a list of missing cards
     */
    private ArrayList<String> missing;
    /**
     * checklist is used to show a list of collected cards
     */
    private ArrayList<String> checklist;
    /**
     * banList is used to show a list of banned cards
     */
    private ArrayList<String> banList;
    /**
     * deck is used to store a copy of a deck for modification
     */
    private Deck deck;
    /**
     * collected is used to store a list of collected cards
     */
    private ArrayList<Card> collected;
    /**
     * uncollected is used to store a list of uncollected cards
     */
    private ArrayList<Card> uncollected;
    /**
     * collectedSetID is used to store the setID for a given collection
     */
    private String collectedSetID;
    /**
     * tmpProfSettings holds a tmpCopy of the profileSettings
     */
    private ProfileSettings tmpProfSettings;
    /**
     * cardSuggestor holds the CardSuggestor
     */
    private DeckBuilder cardSuggestor;
    /**
     * album holds an Album object
     */
    private Album album;
    /**
     * Constructor with no arguments
     * <p>
     *     Sets up the object correctly
     * </p>
     */
    SceneData(){
        available = new ArrayList<>();
        missing = new ArrayList<>();
        checklist = new ArrayList<>();
        banList = new ArrayList<>();
        deck = new Deck();
        album = new Album();
        collected = new ArrayList<>();
        uncollected = new ArrayList<>();
        collectedSetID = "";
        tmpProfSettings = null;
        cardSuggestor = null;
    }
    /**
     * Function definition for getAvailable()
     * <P>
     *     Return available
     * </P>
     * @return available
     */
    public ArrayList<String> getAvailable() {
        return available;
    }
    /**
     * Function definition for getMissing()
     * <p>
     *     Return missing
     * </p>
     * @return missing
     */
    public ArrayList<String> getMissing() {
        return missing;
    }
    /**
     * Function definition for getChecklist()
     * <p>
     *     Returns checklist
     * </p>
     * @return checklist
     */
    public ArrayList<String> getChecklist() {
        return checklist;
    }
    /**
     * Function definition for getBanList()
     * <p>
     *     Return banList
     * </p>
     * @return banList
     */
    public ArrayList<String> getBanList() {
        return banList;
    }
    /**
     * Function definition for getDeck()
     * <p>
     *     Return deck
     * </p>
     * @return deck
     */
    public Deck getDeck() {
        return deck;
    }
    /**
     * Function definition for getCollected()
     * <p>
     *     Return collected
     * </p>
     * @return collected
     */
    public ArrayList<Card> getCollected() {
        return collected;
    }
    /**
     * Function definition for getUncollected()
     * <p>
     *     Return uncollected
     * </p>
     * @return uncollected
     */
    public ArrayList<Card> getUncollected() {
        return uncollected;
    }
    /**
     * Function definition for getCollectedSetID()
     * <p>
     *     Return collectedSetID
     * </p>
     * @return collectedSetID
     */
    public String getCollectedSetID() {
        return collectedSetID;
    }
    /**
     * Function definition for getTmpProfSettings()
     * <P>
     *     Return the tmpProfSettings
     * </P>
     * @return
     */
    public ProfileSettings getTmpProfSettings() {
        return tmpProfSettings;
    }
    /**
     * Function definition for getCardSuggestor()
     * <p>
     *     Return the cardSuggestor
     * </p>
     * @return the cardSuggestor
     */
    public DeckBuilder getCardSuggestor() {
        return cardSuggestor;
    }
    /**
     * Function definition for getAlbum()
     * <p>
     *     Return the album
     * </p>
     * @return album
     */
    public Album getAlbum() {
        return album;
    }
    /**
     * Function definition for setAvailable()
     * <p>
     *     Sets available to the passed list
     * </p>
     * @param available is the new list to be used
     */
    public void setAvailable(ArrayList<String> available) {
        this.available = available;
    }
    /**
     * Function definition for setMissing()
     * <p>
     *     Sets missing to the passed list
     * </p>
     * @param missing is the new list to use
     */
    public void setMissing(ArrayList<String> missing) {
        this.missing = missing;
    }
    /**
     * Function definition for setChecklist()
     * <p>
     *     Sets checklist to the passed list
     * </p>
     * @param checklist is the list to be used
     */
    public void setChecklist(ArrayList<String> checklist) {
        this.checklist = checklist;
    }
    /**
     * Function definition for setBanList()
     * <p>
     *     Sets banList to the passed list
     * </p>
     * @param banList is the new list to be used
     */
    public void setBanList(ArrayList<String> banList) {
        this.banList = banList;
    }
    /**
     * Function definition for setDeck()
     * <p>
     *     Sets the deck to the passed object
     * </p>
     * @param deck is the new Deck to be used
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    /**
     * Function definition for setCollected()
     * <p>
     *     Sets collected to the passed list
     * </p>
     * @param collected is the new list to be used
     */
    public void setCollected(ArrayList<Card> collected) {
        this.collected = collected;
    }
    /**
     * Function definition for setUncollected()
     * <p>
     *     Sets uncollected to the passed list
     * </p>
     * @param uncollected is the new list to be used
     */
    public void setUncollected(ArrayList<Card> uncollected) {
        this.uncollected = uncollected;
    }
    /**
     * Function definition for setCollectedSetID()
     * <P>
     *     Sets collectedSetID to the passed value
     * </P>
     * @param collectedSetID is the new value to be used
     */
    public void setCollectedSetID(String collectedSetID) {
        this.collectedSetID = collectedSetID;
    }
    /**
     * Function definition for setTmpProfileSettings()
     * <P>
     *     Sets the tmpProfSettings to the passed object
     * </P>
     * @param tmpProfSettings is the new set of settings to hold
     */
    public void setTmpProfSettings(ProfileSettings tmpProfSettings) {
        this.tmpProfSettings = tmpProfSettings;
    }
    /**
     * Function definition for setCardSuggestor()
     * <p>
     *     Sets the cardSuggestor to the passed object
     * </p>
     * @param cardSuggestor is the new object to use
     */
    public void setCardSuggestor(DeckBuilder cardSuggestor) {
        this.cardSuggestor = cardSuggestor;
    }
    /**
     * Function definition for setAlbum()
     * <p>
     *     Set album to the passed Album
     * </p>
     * @param album is the Album to use
     */
    public void setAlbum(Album album) {
        this.album = album;
    }
}
