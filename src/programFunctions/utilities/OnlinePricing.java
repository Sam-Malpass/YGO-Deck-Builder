/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import programFunctions.ProgramFunctions;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
public class OnlinePricing {
    /**
     * Constructor with no arguments
     * <p>
     *     Sets up the object
     * </p>
     */
    OnlinePricing() {

    }
    /**
     * Function definition for findOnline()
     * <p>
     *     Determines what the settings are using and passes to the appropriate
     *     online price checkers
     * </p>
     * @param cardName is the name of the card
     * @param setID is the setID of the card
     */
    public void findOnline(String cardName, String setID) {
        if(true) {
            String name;
            String set;
            switch(ProgramFunctions.getProgramData().getGlobalSettings().getShoppingSite()) {
                case 0:
                    name = cardMarketName(cardName);
                    set = getSeriesName(setID);
                    findOnCardMarket(name, set);
                    break;
                case 1:
                    name = ebayName(cardName);
                    set = getSeriesName(setID);
                    findOnEbay(name, set);
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Function definition for findOnCardMarket()
     * <p>
     *     This function takes a string of a card name and the name of a set and searches it on
     *     the internet using the default browser if available
     * </p>
     * @param cardName is the name of the card
     * @param setID is the set
     */
    private void findOnCardMarket(String cardName, String setID) {
        String domain;
        switch(ProgramFunctions.getProgramData().getGlobalSettings().getRegion()) {
            case 0:
            case 1:
                domain = "en";
                break;
            case 2:
                domain = "de";
                break;
            case 3:
                domain = "fr";
                break;
            default:
                domain = "en";
                break;
        }
        try{
            if (Desktop.isDesktopSupported()) {
                try {
                    String str = "https://www.cardmarket.com/" + domain + "/YuGiOh/Products/Singles/" + setID + "/" + cleaner(cardName) + "/";
                    URI uri = new URI(str);
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ex) {

        }
        return;
    }
    /**
     * Function definition for findOnEbay()
     * <p>
     *     This function takes a string of a card name and the name of a set and searches it on
     *     the internet using the default browser if available
     * </p>
     * @param cardName is the name of the card to find
     * @param setID is the set the card belongs to
     */
    private void findOnEbay(String cardName, String setID) {
        String newSetID = setID.replaceAll("-", "+");
        String domain;
        switch (ProgramFunctions.getProgramData().getGlobalSettings().getRegion()) {
            case 0:
                domain = "co.uk";
                break;
            case 1:
                domain = "com";
                break;
            case 2:
                domain = "de";
                break;
            case 3:
                domain = "fr";
                break;
            default:
                domain = "co.uk";
                break;
        }
        try{
            if (Desktop.isDesktopSupported()) {
                try {
                    String str = "http://www.ebay." + domain + "/sch/i.html?_from=R40&_trksid=m570.l1313&_nkw=" + cleaner(cardName) + "+" + newSetID + "&rt=nc&LH_All=1";
                    URI uri = new URI(str);
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ex) {

        }
        return;
    }
    /**
     * Function definition for cardMarketName()
     * <p>
     *     This function processes the card name into a format that can be used in the findOnline function
     * </p>
     * @param cardName is the name of the card to be processed
     * @return the processed name
     */
    private String cardMarketName(String cardName) {
        String unprocessed[] = cardName.split(" ");
        java.util.List<String> list;
        list = Arrays.asList(unprocessed);
        String processedName = "";
        for(String s : list) {
            processedName = processedName + s + "-";
        }
        processedName = processedName.substring(0, processedName.length()-1);
        return processedName;
    }
    /**
     * Function definition for ebayName()
     * <p>
     *     This function processes the card name into a format that can be used in the findOnline function
     * </p>
     * @param cardName is the name to be processed
     * @return the processed name
     */
    private String ebayName(String cardName) {
        String unprocessed[] = cardName.split(" ");
        List<String> list;
        list = Arrays.asList(unprocessed);
        String processedName = "";
        for(String s : list) {
            processedName = processedName + s + "+";
        }
        processedName = processedName.substring(0, processedName.length()-1);
        return processedName;
    }
    /**
     * Function definition for cleaner()
     * <P>
     *     Cleans the passed card name
     * </P>
     * @param cardName is the name to be cleaned
     * @return the cleaned string
     */
    private String cleaner(String cardName) {
        cardName = cardName.replace(",", "");
        return cardName;
    }
    /**
     * Function definition for getSeriesName()
     * <p>
     *     Gets the programFileHandler to look up the full name for a given set
     *     using the setID
     * </p>
     * @param setID is the setID to search
     * @return the result
     */
    private String getSeriesName(String setID) {
        if(setID.contains("SD")) {
            return "Structure-Deck-" + ProgramFunctions.getUtilities().getFileHandler().findSetName(setID);
        }
        else {
            return ProgramFunctions.getUtilities().getFileHandler().findSetName(setID);
        }
    }
}
