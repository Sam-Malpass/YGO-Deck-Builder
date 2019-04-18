/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.banList.BanList;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import programFunctions.ProgramFunctions;
import programFunctions.searching.SearchResult;

import java.util.ArrayList;
public class Outputters {
    /**
     * Constructor with no arguments
     */
    public Outputters() {
    }
    /**
     * Function definition for outputStringList()
     * <P>
     *     Outputs to the console all the strings in a list
     * </P>
     * @param list the list to be output
     */
    public void outputStringList(ArrayList<String> list) {
        for(String s : list) {
            System.out.println(s);
        }
    }
    /**
     * Function definition for outputCardList()
     * <p>
     *     Outputs to the console all the card names in a given list
     * </p>
     * @param list the cards to be output
     */
    public ArrayList<String> outputCardList(ArrayList<Card> list) {
        ArrayList<String> tmp = new ArrayList<>();
        for(Card c : list) {
            tmp.add(c.getCardName());
        }
        return tmp;
    }
    /**
     * Function definition for outputSearchResults()
     * <P>
     *     Converts an ArrayList of SearchResults to an ArrayList of Strings
     * </P>
     * @param results is an ArrayList of SearchResults
     * @return an ArrayList of Strings
     */
    public ArrayList<String> outputSearchResults(ArrayList<SearchResult> results) {
        /*Make an empty ArrayList*/
        ArrayList<String> resultsOutput = new ArrayList<>();
        /*For all results*/
        for(SearchResult S : results) {
            /*Add the output to the ArrayList*/
            resultsOutput.add(S.outputResult());
        }
        /*Return the ArrayList*/
        return resultsOutput;
    }
    /**
     * Function definition for outputBanList()
     * <p>
     *     Makes a banList from the loaded BanList, then returns the outputBanList of the
     *     banList
     * </p>
     * @return the outputBanList of the banList
     */
    public static ArrayList<String> outputBanList() {
        /*Make a BanList object*/
        BanList banList = ProgramFunctions.getUtilities().getFileHandler().loadBanList();
        /*Return the banList as a string ArrayList*/
        return banList.outputBanList();
    }
    /**
     * Function definition for listDecks()
     * <p>
     *     Returns an arraylist of names of all the decks a user owns
     * </p>
     * @param decks is the list of decks
     * @return list of names
     */
    public ArrayList<String> listDecks(ArrayList<Deck> decks) {
        ArrayList<String> tmp = new ArrayList<>();
        for(Deck C : decks) {
            tmp.add(C.getContainerName());
        }
        return tmp;
    }
}
