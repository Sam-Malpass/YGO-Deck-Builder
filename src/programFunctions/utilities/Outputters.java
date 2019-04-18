/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
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
            System.out.println(c.getCardName());
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
    public ArrayList<String> listDecks(ArrayList<Deck> decks) {
        ArrayList<String> tmp = new ArrayList<>();
        for(Deck C : decks) {
            tmp.add(C.getContainerName());
        }
        return tmp;
    }
}
