/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.cardHierarchy.Card;
import dataStructure.containerHierarchy.Series;
import programFunctions.ProgramFunctions;
import programFunctions.searching.SearchResult;
import java.util.ArrayList;
import java.util.List;

public class Checklist {
    /**
     * Constructor with no arguments
     * <p>
     *     Creates the object
     * </p>
     */
    Checklist() {
    }
    /**
     * Function definition of checklistCollected()
     * <p>
     *     Opens a series and checks which cards have been collected
     * </p>
     * @param seriesName is the series to open
     * @return the collected cards
     */
    public ArrayList<Card> checklistCollected(String seriesName) {
        ArrayList<Card> tmp = new ArrayList<>();
        Series series = ProgramFunctions.getUtilities().getFileHandler().loadSeries(seriesName);
        for(Card c : series.getCards()) {
            ArrayList<SearchResult> tmpII = ProgramFunctions.getQuery().searchCard(c.getCardName());
            /*For all the search results*/
            for (SearchResult test : tmpII) {
                /*Create a test string*/
                /*If the series name matches the cards set ID*/
                if (series.getContainerName().equals(test.getSetID())) {
                    tmp.add(c);
                    /*Break*/
                    break;
                }
            }
        }
        return tmp;
    }
    /**
     * Function definition for checklistUncollected()
     * <p>
     *     Checks a list of collected cards against a list and returns all the uncollected cards
     * </p>
     * @param collected is the list of collected cards
     * @param seriesName is the series to check against
     * @return the uncollected cards
     */
    public ArrayList<Card> checklistUncollected(ArrayList<Card> collected, String seriesName) {
        Series series = ProgramFunctions.getUtilities().getFileHandler().loadSeries(seriesName);
        ArrayList<Card> tmp = series.getCards();
        ArrayList<Card> toBeRemoved = new ArrayList<>();
        for(Card c : tmp) {
            for(Card x : collected) {
                if(c.getCardName().equals(x.getCardName())) {
                    toBeRemoved.add(c);
                }
            }
        }
        tmp.removeAll(toBeRemoved);
        return tmp;
    }
    /**
     * Function definition for checklistChecker()
     * <P>
     *     Checks all the cards in every series against all the cards
     *     a user has an determines the percentage of each series the
     *     user has collected. WARNING! This function is highly inefficient
     *     and whilst it works it will need to be refactored. This has been
     *     partially fixed as of v0.2.9.d
     * </P>
     * @return an ArrayList of series and their respective percentages
     */
    public ArrayList<String> checklistChecker() {
        /*Make an ArrayList*/
        ArrayList<String> checklist = new ArrayList<>();
        /*
        THIS VARIABLE SERVES TO REDUCE FILE ACCESS TIME AND REDUCE FUNCTION TIME
         */
        List<String> seriesList = ProgramFunctions.getUtilities().getFileHandler().searchSeriesFolder();
        /*Declare a counter*/
        float counter;
        /*For all series*/
        for (String aSeriesList : seriesList) {
            /*Set the counter to zero*/
            counter = 0;
            /*
            THIS VARIABLE SERVES TO REDUCE FILE ACCESS TIME AND REDUCE FUNCTION TIME
             */
            ArrayList<String> series = ProgramFunctions.getUtilities().getFileHandler().loadSeries(aSeriesList).listCardsString();
            /*For all cards in the series*/
            for (String sery : series) {
                /*Make an ArrayList of the search cards in user profile*/
                ArrayList<SearchResult> tmp = ProgramFunctions.getQuery().searchCard(sery);
                /*For all the search results*/
                for (SearchResult test : tmp) {
                    /*Create a test string*/
                    /*If the series name matches the cards set ID*/
                    if (aSeriesList.equals(test.getSetID())) {
                        /*Increment the counter*/
                        counter++;
                        /*Break*/
                        break;
                    }
                }
            }
            /*Make a float*/
            float size = series.size();
            /*Make a float*/
            float percent = ((counter / size) * 100);
            /*Output the percentage to console*/
            System.out.println("[SYSTEM] Percentage calculated at " + percent + "%");
            /*Add the data to the checklist*/
            checklist.add(aSeriesList + " \t=====\t " + percent + "%");
        }
        /*Return the checklist*/
        return checklist;
    }
}
