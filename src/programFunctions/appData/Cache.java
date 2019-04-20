package programFunctions.appData;

import dataStructure.UserProfile;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import programFunctions.ProgramFunctions;

import java.util.ArrayList;

public class Cache {
    /**
     * albumCards holds all the cards available in a users albums
     */
    private ArrayList<Card> albumCards = new ArrayList<>();
    /**
     * deckCards holds all the cards available in a users decks
     */
    private ArrayList<Card> deckCards = new ArrayList<>();
    /**
     * systemCards holds all the cards available in the application
     */
    private ArrayList<Card> systemCards;
    private ArrayList<Album> albums;
    private ArrayList<Deck> decks;
    /**
     * Constructor with no arguments
     * <p>
     *     Sets the Cache up
     * </p>
     */
    public Cache() {
        albumCards = new ArrayList<>();
        deckCards = new ArrayList<>();
        albums = new ArrayList<>();
        decks = new ArrayList<>();
        systemCards = loadSystemCards();
    }
    /**
     * Constructor with arguments
     * <p>
     *     Sets up a Cache object using passed data
     * </p>
     * @param userData the data to be cached
     */
    public Cache(UserProfile userData) {
        loadUserCards(userData);
        albums = ProgramFunctions.getUtilities().getFilter().filterAlbums(userData.getUserContainers());
        decks = ProgramFunctions.getUtilities().getFilter().filterDecks(userData.getUserContainers());
        systemCards = loadSystemCards();
    }
    /**
     * Function definition for loadSystemCards()
     * <p>
     *     Loads all the cards from all the series
     * </p>
     * @return the list of all cards
     */
    private static ArrayList<Card> loadSystemCards() {
        ArrayList<Card> systemSet = new ArrayList<>();
        for(int i = 0; i < ProgramFunctions.getUtilities().getFileHandler().searchSeriesFolder().size(); i++) {
            systemSet.addAll(ProgramFunctions.getUtilities().getFileHandler().loadSeries(ProgramFunctions.getUtilities().getFileHandler().searchSeriesFolder().get(i)).getCards());
        }
        return systemSet;
    }
    /**
     * Function definition for loadUserCards()
     * <p>
     *      Loads the user's card collections into appropriate arraylists
     * </p>
     * @param user is the user to be cached
     */
    public void loadUserCards(UserProfile user) {
        System.out.println("[SYSTEM] Caching user cards...");
        deckCards = new ArrayList<>();
        albumCards = new ArrayList<>();
        for(String s : user.listContainers()) {
            if(user.determineContainer(s) instanceof Deck) {
                deckCards.addAll(user.determineContainer(s).getCards());
            }
            else {
                albumCards.addAll(user.determineContainer(s).getCards());
            }
        }
        albums = ProgramFunctions.getUtilities().getFilter().filterAlbums(user.getUserContainers());
        decks = ProgramFunctions.getUtilities().getFilter().filterDecks(user.getUserContainers());
        System.out.println("[SYSTEM] User cards cached successfully");
        return;
    }
    /**
     * Function definition for updateCache()
     * <p>
     *     Sets up the user cards correctly for a premade cache object
     * </p>
     */
    public void updateCache() {
        loadUserCards(ProgramFunctions.getProgramData().getCurrentProfile());
    }
    /**
     * Function definition for swapCard()
     * <p>
     *     Moves a card in the cache if appropriately swapped
     * </p>
     * @param source is the initial container
     * @param destination is the new container
     * @param card is the card to move
     */
    public void swapCard(String source, String destination, Card card) {
        removeCard(source, card);
        addCard(destination, card);
    }
    /**
     * Function definition for getAlbumCards()
     * <p>
     *     Returns the albumCards list
     * </p>
     * @return
     */
    public ArrayList<Card> getAlbumCards() {
        return albumCards;
    }
    /**
     * Function definition for getAlbumCardsString()
     * <p>
     *     Returns a list of all the album cards
     * </p>
     * @return list of all the album cards
     */
    public ArrayList<String> getAlbumCardsString() {
        ArrayList<String> tmp = new ArrayList<>();
        for(Card c : albumCards) {
            tmp.add(c.getCardName());
        }
        return tmp;
    }

    /**
     * Function definition for getDeckCards()
     * <p>
     *     Returns the deckCards list
     * </p>
     * @return
     */
    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }
    /**
     * Function definition for getSystemCards()
     * <p>
     *     Returns the systemCards list
     * </p>
     * @return
     */
    public ArrayList<Card> getSystemCards() {
        return systemCards;
    }
    /**
     * Function definition for setAlbumCards()
     * <p>
     *     Sets the albumCards list to a passed list
     * </p>
     * @param albumCards is the new list
     */
    public void setAlbumCards(ArrayList<Card> albumCards) {
        this.albumCards = albumCards;
    }
    /**
     * Function defintion for setDeckCards()
     * <p>
     *     Sets the deckCards list to a passed list
     * </p>
     * @param deckCards is the new list
     */
    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }
    /**
     * Function definition for setSystemCards()
     * <p>
     *     Sets the systemCards list to a passed list
     * </p>
     * @param systemCards is the new list
     */
    public void setSystemCards(ArrayList<Card> systemCards) {
        this.systemCards = systemCards;
    }
    /**
     * Function definition for removeCard()
     * <P>
     *     Removes the card from either the album list or the deck list
     * </P>
     * @param container is the container to have the card removed from
     * @param card is the card to be removed
     */
    public void removeCard(String container, Card card) {
        if(determineContainer(container) instanceof Deck) {
            deckCards.remove(card);
            for(Deck d : decks) {
                if(d.getContainerName().equals(container)) {
                    Card remover = null;
                    for(Card c : d.getCards()) {
                        if(c.getCardName().equals(card.getCardName())) {
                            remover = c;
                            break;
                        }
                    }
                    if(remover != null) {
                        d.removeCard(remover);
                    }
                }
            }
            Card remover = null;
            for(Card C : deckCards) {
                if(C.getCardName().equals(card.getCardName()) && C.getCardID().equals(card.getCardID())) {
                    remover = C;
                    break;
                }
            }
            if(remover != null) {
                deckCards.remove(remover);
            }
        }
        else {
            for(Album a : albums){
                if(a.getContainerName().equals(container)){
                    Card remover = null;
                    for(Card c : a.getCards()) {
                        if(c.getCardName().equals(card.getCardName())) {
                            remover = c;
                            break;
                        }
                    }
                    if(remover != null) {
                        a.removeCard(remover);
                    }
                }
            }
            Card remover = null;
            for(Card C : albumCards) {
                if(C.getCardName().equals(card.getCardName()) && C.getCardID().equals(card.getCardID())) {
                    remover = C;
                    break;
                }
            }
            if(remover != null) {
                albumCards.remove(remover);
            }
        }
    }
    /**
     * Function definition for addCard()
     * <p>
     *     Adds the card to either the album list or the deck listS
     * </p>
     * @param container is the container the card is being added to
     * @param card is the card to be added
     */
    public void addCard(String container, Card card) {
        if(determineContainer(container) instanceof Deck) {
            deckCards.add(card);
            for(Deck d : decks) {
                if(d.getContainerName().equals(container)) {
                    d.addCard(card);
                }
            }
        }
        else {
            albumCards.add(card);
            for(Album a : albums) {
                if(a.getContainerName().equals(container)) {
                    a.addCard(card);
                }
            }
        }
    }
    public Container determineContainer(String name) {
        for(Deck d : decks) {
            if(d.getContainerName().equals(name)) {
                return d;
            }
        }
        for(Album a : albums) {
            if(a.getContainerName().equals(name)) {
                return a;
            }
        }
        return null;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public ArrayList<Deck> getDecks() {
        return decks;
    }

    @Override
    public Cache clone() throws CloneNotSupportedException {
        Cache cont = new Cache();
        for(Album a : this.albums) {
            cont.albums.add(a.clone());
        }
        for(Deck d : this.decks) {
            cont.decks.add(d.clone());
        }
        for(Card c : this.albumCards) {
            cont.albumCards.add(c.clone());
        }
        for(Card c : this.deckCards) {
            cont.deckCards.add(c.clone());
        }
        return cont;
    }
}
