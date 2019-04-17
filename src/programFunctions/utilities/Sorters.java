/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.cardHierarchy.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Sorters {
    /**
     * Constructor with no arguments
     */
    public Sorters() {
    }
    /**
     * Function definition for sortByName()
     * <p>
     *     Sorts a list of cards by their name
     * </p>
     * @param cards is the list to be sorted
     * @return the sorted list
     */
    public ArrayList<Card> sortByName(ArrayList<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getCardName().compareTo(c2.getCardName());
            }
        });
        return cards;
    }
    /**
     * Function definition for invertedSortByName()
     * <p>
     *     Returns an inverted sorted ArrayList
     * </p>
     * @param cards
     */
    public ArrayList<Card> invertedSortByName(ArrayList<Card> cards) {
        ArrayList<Card> tmp = sortByName(cards);
        Collections.reverse(tmp);
        return tmp;
    }
    /**
     * Function definition for sortBySet()
     * <p>
     *     Sorts the cards in the list by the setID
     * </p>
     * @param cards is the list to be sorted
     * @return the list
     */
    public ArrayList<Card> sortBySet(ArrayList<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getCardID().compareTo(c2.getCardID());
            }
        });
        return cards;
    }
    /**
     * Function definition for invertedSortBySet()
     * <p>
     *     Sorts the list by setID then reverses the order
     * </p>
     * @param cards the list to be sorted
     * @return the sorted list
     */
    public ArrayList<Card> invertedSortBySet(ArrayList<Card> cards) {
        ArrayList<Card> tmp = sortBySet(cards);
        Collections.reverse(tmp);
        return tmp;
    }
    /**
     * Function definition for sortByValue()
     * <p>
     *     Sorts a list of cards by value
     * </p>
     * @param cards is a list to be sorted
     * @return the sorted list
     */
    public ArrayList<Card> sortByValue(ArrayList<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return Double.compare(o1.getValue(), o2.getValue());
            }
        });
        return cards;
    }
    /**
     * Function definition for invertedSortByValue()
     * <p>
     *     Inverts the sortByValue list
     * </p>
     * @param cards is the list to be sorted
     * @return the sorted list
     */
    public ArrayList<Card> invertedSortByValue(ArrayList<Card> cards) {
        ArrayList<Card> tmp = sortByValue(cards);
        Collections.reverse(tmp);
        return tmp;
    }
}
