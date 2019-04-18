package programFunctions.utilities;

import dataStructure.containerHierarchy.Deck;
import programFunctions.ProgramFunctions;

import java.util.ArrayList;

public class ImportExport {
    public ImportExport() {

    }
    /**
     * Function definition for importDeck()
     * <p>
     *     Handles the initial stage of the import deck feature.
     *     This function passes the imported deck to the deckListChecker
     * </p>
     * @return whether successful
     */
    public void importDeck() {
        /*If there is an active profile*/
        if(ProgramFunctions.profileActive()) {
            /*Set the deck to equal the chosen file*/
            Deck deck = ProgramFunctions.getUtilities().getFileHandler().loadDeck(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().importDialog());
            /*Create a string of the name*/
            String newName = deck.getContainerName();
            /*Check if the name is available*/
            while(!ProgramFunctions.checkContainerName(newName)) {
                /*Ask for a new name*/
                newName = ProgramFunctions.getProgramData().getUserInterface().inputWindow("Change Name...", "Input new name:");
            }
            /*Set the container name*/
            deck.setContainerName(newName);
            /*Output stage*/
            System.out.println("[SYSTEM] Invoking Deck List Checker");
            deckListChecker(deck);
            /*Return the result of the deckListChecker*/
        }
        /*Otherwise*/
        else {
            /*Output error*/
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
        }
    }
    /**
     * Function definition for exportDeck()
     * <p>
     *     Checks there is a profile active and then initiates the exportation
     *     of a deck owned in the profile.
     * </p>
     * @return whether successful
     */
    public boolean exportDeck() {
        /*If there is an active profile*/
        if(ProgramFunctions.profileActive()) {
            /*Save the selected deck*/
            Deck deck = (Deck) ProgramFunctions.getProgramData().getCurrentProfile().determineContainer(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().boxSelector(ProgramFunctions.getUtilities().getOutputter().listDecks(ProgramFunctions.getProgramData().getCache().getDecks()), "Select Deck..."));
            if(deck != null) {
                ProgramFunctions.getUtilities().getFileHandler().saveDeck(deck, ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().exportDialog());
                /*Output success*/
                System.out.println("[SYSTEM] Deck exported successfully");
                /*Return true*/
                return true;
            }
            return false;
        }
        /*Otherwise*/
        else {
            /*Output error*/
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("ERROR", "002: No active profile selected");
            System.out.println("[ERROR] 002: No active profile selected");
            /*Return false*/
            return false;
        }
    }
    /**
     * Function definition for deckListChecker()
     * <p>
     *     Checks the deck list against the cards owned by the user,
     *     and determines if the user is missing any copies of cards
     *     needed to make the deck
     * </p>
     * @param d is the Deck to be checked
     * @return true or false
     */
    private void deckListChecker(Deck d) {
        /*Make an ArrayList*/
        ArrayList<String> available = new ArrayList<>();
        /*Copy the DeckList*/
        ArrayList<String> missing = d.listCardsString();
        ArrayList<String> backUp;
        /*Get the Users Cards*/
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeDecks() != true) {
            backUp = ProgramFunctions.getProgramData().getCache().getAlbumCardsString();
        }
        else {
            backUp = ProgramFunctions.getProgramData().getCurrentProfile().listAllCards();
        }
        /*For all cards in the deck*/
        ArrayList<String> tmpI = d.listCardsString();
        for (String aTmpI : tmpI) {
            /*For all cards the user owns*/
            for (String S : backUp) {
                /*If the user owns a the current card being examined*/
                if (aTmpI.equals(S)) {
                    /*Output a message*/
                    System.out.println("[SYSTEM] Found " + S);
                    /*Add it to the available list*/
                    available.add(S);
                    /*Remove it from the missing list*/
                    missing.remove(S);
                    /*Remove it from the users card pool*/
                    backUp.remove(S);
                    /*Break*/
                    break;
                }
            }
        }
        /*Change the GUI scene*/
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getDeckImport(available, missing));
        /*Return*/
    }
}
