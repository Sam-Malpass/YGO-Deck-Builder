/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataStructure.containerHierarchy;
import dataStructure.cardHierarchy.Card;
import java.io.Serializable;
import java.util.ArrayList;
public abstract class Container implements Serializable, Cloneable {
    /**
     * serialVersionUID allows the class to implement serialised
     */
    public static final long serialVersionUID = 1L;
    /**
     * containerName holds the name of the Container
     */
    private String containerName;
    /**
     * cards holds the all the cards in the Container
     */
    private ArrayList<Card> cards;
    /**
     * maxCapacity holds the maximum amount of cards that the Container can hold
     */
    private int maxCapacity;
    /**
     * Constructor with no arguments
     * <p>
     * Sets the variables to default values
     */
    Container() {
        /*Set containerName to null*/
        containerName = null;
        /*Set cards to null*/
        cards = new ArrayList<>();
        /*Set maxCapacity to 0*/
        maxCapacity = 0;
    }
    /**
     * Constructor with arguments
     * <p>
     * Sets the variables to passed values and then creates a new ArrayList of cards
     * <p>
     * @param N is for the name of the Container
     * @param M is for the maxCapacity of the Container
     */
    Container(String N, int M) {
        /*Sets the containerName to N*/
        containerName = N;
        /*Creates a new ArrayList*/
        cards = new ArrayList<>();
        /*Sets maxCapacity to M*/
        maxCapacity = M;
    }
    /**
     * Function definition for getContainerName()
     * <p>
     * Return the containerName for a given Container
     * <p>
     * @return the containerName
     */
    public String getContainerName() {
        /*Return containerName*/
        return this.containerName;
    }
    /**
     * Function definition for getMaxCapacity()
     * <p>
     * Return the maxCapacity for a given Container
     * <p>
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        /*Return maxCapacity*/
        return this.maxCapacity;
    }
    /**
     * Function definition for getCards()
     * <p>
     *     Returns the Cards
     * </p>
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    /**
     * Function definition for setContainerName()
     * <p>
     * Set the containerName to N
     * @param N is value to set containerName to
     */
    public void setContainerName(String N) {
        /*Set containerName to N*/
        this.containerName = N;
    }
    /**
     * Function definition for setMaxCapacity()
     * <p>
     * Set maxCapacity to M
     * <p>
     * @param M is the value to set the maxCapacity to
     */
    public void setMaxCapacity(int M) {
        /*Set maxCapacity to M*/
        this.maxCapacity = M;
    }
    /**
     * Function definition for addCard()
     * <p>
     * Adds the Card passed to the ArrayList as long as the maxCapacity has
     * not been reached
     * <p>
     * @param C is the Card to be added
     */
    public boolean addCard(Card C) {
        /*If the Container is at maxCapacity*/
        if (cards.size() >= maxCapacity) {
            /*Return*/
            return false;
            /*Else*/
        } else {
            /*Add the Card*/
            cards.add(C);
            /*Return*/
            return true;
        }
    }
    /**
     * Function definition for removeCard()
     * <p>
     * Checks all the Cards in the Container and removes the Card if it
     * is found
     * <p>
     * @param C is the Card to be removed from the Container
     */
    public boolean removeCard(Card C) {
        /*For all Cards in the Container*/
        for (int i = 0; i < cards.size(); i++) {
            /*If the Card is in the Container*/
            if (cards.get(i).equals(C)) {
                /*Remove the Card*/
                cards.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Function definition for listCardsString()
     * <p>
     * Creates and amends a string with the data using the names of all the cards
     * within the Container
     * <p>
     * @return the created string
     */
    public ArrayList listCardsString() {
        /*Create an empty string*/
        ArrayList<String> S = new ArrayList<>();
        /*For all the Cards in the Container*/
        for (Card card : cards) {
            /*Add the cardName to the ArrayList*/
            S.add(card.getCardName());
        }
        /*Return the string*/
        return S;
    }
    /**
     * Function definition for findCard()
     * <p>
     *     Checks the name of the desired card to see if card exists in container.
     *     If it does return the card.
     * </p>
     * @param name
     * @return
     */
    public Card findCard(String name) {
        /*For all cards in Container*/
        for (Card card : cards) {
            /*Check if name is a match*/
            if (name.equals(card.getCardName())) {
                /*If it is return the card*/
                return card;
            }
        }
        /*If all checks fail return null*/
        return null;
    }
    /**
     * Function definition for clone()
     * <p>
     *     Clones the Container object
     * </p>
     * @return clone
     * @throws CloneNotSupportedException
     */
    @Override
    public Container clone() throws CloneNotSupportedException {
        Container clone = (Container) super.clone();
        for(Card C : this.cards) {
            clone.cards.add(C.clone());
        }
        return clone;
    }
}
