/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.searching;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Deck;
import programFunctions.ProgramFunctions;
import java.util.ArrayList;
public class Searcher {
    /**
     * Function definition for searchCard()
     * <p>
     *     Searches for a card in all containers
     * </p>
     * @param name is the name of the card
     * @return an ArrayList of cards and locations
     */
    public ArrayList<SearchResult> searchCard(String name) {
        /*Check the name is not null or whitespace*/
        if(name.equals("") || name == null) {
            /*Output an error*/
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "013: Invalid input from user");
            System.out.println("[ERROR] 013: Invalid input from user");
            /*Return null*/
            return null;
        }
        /*Make an ArrayList*/
        ArrayList<SearchResult> cardList = new ArrayList<>();
        /*For all containers*/
        ArrayList<Album> albums = ProgramFunctions.getUtilities().getFilter().filterAlbums(ProgramFunctions.getProgramData().getCurrentProfile().getUserContainers());
        ArrayList<Deck> decks = new ArrayList<>();
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeDecks()) {
            decks = ProgramFunctions.getUtilities().getFilter().filterDecks(ProgramFunctions.getProgramData().getCurrentProfile().getUserContainers());
        }
        for(Album a : albums) {
            for(Card b : a.getCards()) {
                if(b.getCardName().equals(name) || b.getCardName().contains(name)) {
                    cardList.add(new SearchResult(b.getCardName(), a.getContainerName(), b.getCardID()));
                }
            }
        }
        for(Deck a : decks) {
            for(Card b : a.getOnlyDeck()) {
                if(b.getCardName().equals(name) || b.getCardName().contains(name)) {
                    cardList.add(new SearchResult(b.getCardName(), a.getContainerName(), b.getCardID()));
                }
            }
            for(Card b : a.getExtraDeck()) {
                if(b.getCardName().equals(name) || b.getCardName().contains(name)) {
                    cardList.add(new SearchResult(b.getCardName(), a.getContainerName(), b.getCardID()));
                }
            }
        }
        /*Return the cardList*/
        return cardList;
    }
    /**
     * Function definition for searchSystem()
     * <p>
     *     Searches the system for a card
     * </p>
     * @param name is the card to seach for
     * @return the results
     */
    public ArrayList<SearchResult> searchSystem(String name) {
        /*Check the name is not null or whitespace*/
        if(name.equals("") || name == null) {
            /*Output an error*/
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "013: Invalid input from user");
            System.out.println("[ERROR] 013: Invalid input from user");
            /*Return null*/
            return null;
        }
        /*Make an ArrayList*/
        ArrayList<SearchResult> cardList = new ArrayList<>();
        /*For all containers*/
        for (Card aTmpII : ProgramFunctions.getProgramData().getCache().getSystemCards()) {
            if (name.equals(aTmpII.getCardName()) || aTmpII.getCardName().contains(name)) {
                SearchResult tmpIII = new SearchResult(aTmpII.getCardName(), "System" , aTmpII.getCardID());
                cardList.add(tmpIII);
            }
        }
        /*If card is present*/
        /*Return the cardList*/
        return cardList;
    }
}
