/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.containerHierarchy;
import dataStructure.cardHierarchy.*;
import java.util.ArrayList;
public class Deck extends Container {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * extraDeck hold the cards in the extra deck for this Container
     */
    private ArrayList<Card> extraDeck;
    /**
     * Constructor with no arguments
     * <p>
     * Calls Container constructor and sets the extraDeck to be null
     */
    public Deck() {
        /*Calls Container constructor*/
        super();
        /*Set extraDeck to null*/
        extraDeck = new ArrayList<>();
    }
    /**
     * Constructor with arguments
     * <p>
     * Calls Container constructor and creates a new ArrayList for the extraDeck
     * <p>
     * @param N is the Container name
     */
    public Deck(String N) {
        /*Calls Container constructor*/
        super(N, 60);
        /*Creates new ArrayList*/
        extraDeck = new ArrayList<>();
    }
    /**
     * Function definition for getOnlyDeck()
     * <p>
     *     Returns only the cards within the deck
     * </p>
     * @return deck cards only
     */
    public ArrayList<Card> getOnlyDeck() {
        return super.getCards();
    }
    /**
     * Function definition for getExtraDeck()
     * <p>
     *     Returns only the extraDeck
     * </p>
     * @return the extraDeck
     */
    public ArrayList<Card> getExtraDeck() {
        return extraDeck;
    }
    /**
     * Function definition for addExtraDeckCard()
     * <p>
     * Check if the size is exceeded, and then add a Card
     * <p>
     * @param C is the Card to be added
     */
    public boolean addExtraDeckCard(Card C) {
        /*If the extraDeck size is at max*/
        if (extraDeck.size() >= 15) {
            /*Return*/
            return false;
            /*Else*/
        } else {
            /*Add Card to extraDeck*/
            extraDeck.add(C);
            return true;
        }
    }
    /**
     * Function definition for removeExtraDeckCard()
     * <p>
     * Check all Cards in extraDeck and if it is there, remove it
     * <p>
     * @param C is the Card to be removed
     */
    public void removeExtraDeckCard(Card C) {
        /*For all Cards in the extraDeck*/
        for (int i = 0; i < extraDeck.size(); i++) {
            /*If the Card is there*/
            if (extraDeck.get(i).equals(C)) {
                /*Remove it*/
                extraDeck.remove(C);
            }
        }
    }
    /**
     * Function definition for listExtraDeck()
     * <p>
     * Creates a string and amends it with card names from the extraDeck
     * <p>
     * @return the string
     */
    public ArrayList<String> listExtraDeck() {
        /*Create an empty ArrayList*/
        ArrayList<String> S = new ArrayList<>();
        /*For all the extraDeck Cards*/
        for (Card anExtraDeck : extraDeck) {
            /*Amend the string with the Card name*/
            S.add(anExtraDeck.getCardName());
        }
        /*Return the string*/
        return S;
    }
    /**
     * Function definition for addCard()
     * <p>
     *     Determines whether card can be put in this deck and whether it is an extra deck
     *     or main deck card, then it uses the appropriate add function for the given card
     * </p>
     * @param C is the Card to be added
     */
    public boolean addCard(Card C) {
        /*If card should be in the Extra Deck*/
        if(determineCardStack(C)) {
            /*Create a counter*/
            int counter = 0;
            /*Create a tmp ArrayList*/
            ArrayList tmp = listExtraDeck();
            /*For all elements in the tmp ArrayList*/
            for (Object aTmp : tmp) {
                /*If the Card name matches a card*/
                if (C.getCardName().equals(aTmp)) {
                    /*Increment the counter*/
                    counter++;
                    /*If the counter is greater than 3*/
                    if (counter >= 3) {
                        /*Return*/
                        return false;
                    }
                }
            }
            /*Add the card to the extra deck*/
            return addExtraDeckCard(C);
        }
        /*Otherwise card is a main deck card*/
        else {
            /*Create a counter*/
            int counter = 0;
            /*Create a tmp ArrayList*/
            ArrayList<String> tmp = listCardsString();
            /*For all elements in the tmp ArrayList*/
            for (String aTmp : tmp) {
                /*If the Card name matches a card*/
                if (C.getCardName().equals(aTmp)) {
                    /*Increment the counter*/
                    counter++;
                    /*If the counter is greater than 3*/
                    if (counter >= 3) {
                        /*Return*/
                        return false;
                    }
                }
            }
            /*Add the card using parent function*/
            return super.addCard(C);
        }
    }
    /**
     * Function definition for removeCard()
     * <p>
     *     Determines what type of card the Card is and then removes it
     *     using the appropriate remove function
     * </p>
     * @param C is the Card to be removed
     */
    public boolean removeCard(Card C) {
        /*If card is an extra deck card*/
        if(determineCardStack(C)) {
            /*Use removeExtraDeckCard function*/
            removeExtraDeckCard(C);
            return true;
        }
        /*Otherwise*/
        else {
            /*Use parent removeCard function*/
            return super.removeCard(C);
        }
    }
    /**
     * Function definition for getCard()
     * <p>
     *     Gets the card for the input name
     * </p>
     * @param name is the card to find
     * @return the card
     */
    public Card findCard(String name) {
        /*For all cards in Container*/
        for (Card card : extraDeck) {
            /*Check if name is a match*/
            if (name.equals(card.getCardName())) {
                /*If it is return the card*/
                return card;
            }
        }
        return super.findCard(name);
    }
    /**
     * Function definition for listCardsString()
     * <p>
     *     Creates an ArrayList for deck cards and a separate one for extra deck cards
     *     then concatenates the two before returning the merged ArrayList
     * </p>
     * @return the merged ArrayList
     */
    public ArrayList<String> listCardsString() {
        /*Get the deck cards*/
        ArrayList tmpI = super.listCardsString();
        /*Get the extra deck cards*/
        ArrayList tmpII = listExtraDeck();
        /*Create a new ArrayList*/
        ArrayList tmpIII = new ArrayList();
        /*Add all elements from the deck to the new ArrayList*/
        tmpIII.addAll(tmpI);
        /*Add all elements from the extra deck to the new ArrayList*/
        tmpIII.addAll(tmpII);
        /*Return the merge*/
        return tmpIII;
    }
    /**
     * Function definition for determineCardStack()
     * <p>
     *     Determines whether a card should be within the main deck or the
     *     extra deck
     * </p>
     * @param C is the card to test
     * @return true or false accordingly
     */
    public boolean determineCardStack(Card C) {
        /*If the card should be in the extra deck*/
        if(C instanceof SynchroMonster || C instanceof FusionMonster || C instanceof XYZMonster || C instanceof LinkMonster){
            /*Return true*/
            return true;
        }
        /*Return false*/
        return false;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the Deck object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public Deck clone() throws CloneNotSupportedException {
        Deck clone = new Deck(this.getContainerName());
        for(Card C : this.getOnlyDeck()) {
            clone.getOnlyDeck().add(C.clone());
        }
        for(Card C : this.getExtraDeck()) {
            clone.getExtraDeck().add(C.clone());
        }
        return clone;
    }
}
