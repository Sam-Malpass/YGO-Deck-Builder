/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.builder;
import dataStructure.banList.BanList;
import dataStructure.banList.LimitedCard;
import dataStructure.cardHierarchy.*;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterType;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Deck;
import programFunctions.ProgramFunctions;
import programFunctions.appData.Cache;
import programFunctions.searching.SearchResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class DeckBuilder {
    /**
     * tmpDeck holds a temporary Deck list
     */
    private Deck tmpDeck;
    /**
     * numberOfSpells holds the number of spells in the Deck
     */
    private float numberOfSpells;
    /**
     * numberOfTraps holds the number of traps in the Deck
     */
    private float numberOfTraps;
    /**
     * numberOfMonsters holds the number of monsters in the Deck
     */
    private float numberOfMonsters;
    /**
     * ratioMonsters holds the ratio of monster cards required
     */
    private float ratioMonsters;
    /**
     * ratioSpells hold the ratio of spell cards required
     */
    private float ratioSpells;
    /**
     * ratioTraps holds the ratio of trap cards required
     */
    private float ratioTraps;
    /**
     * statusFlag holds the current state of the deck
     */
    private int statusFlag;
    /**
     * cpyCache is a copy of the user's Cache
     */
    private Cache cpyCache;
    /**
     * suggestions holds a list of potential cards to suggest
     */
    private ArrayList<Card> suggestions;
    /**
     * Constructor with no arguments
     * <p>
     *     Sets the variables to their appropriate values
     * </p>
     */
    public DeckBuilder() {
        suggestions = new ArrayList<>();
        /*Sets up a new Deck*/
        tmpDeck = new Deck();
        /*Sets the numberOfMonsters*/
        numberOfMonsters = 0;
        /*Sets the numberOfSpells*/
        numberOfSpells = 0;
        /*Sets the numberOfTraps*/
        numberOfTraps = 0;
        /*Sets the ratioMonsters to a default*/
        ratioMonsters = 2;
        /*Sets the ratioSpells to a default*/
        ratioSpells = 1;
        /*Sets the ratioTraps to a default*/
        ratioTraps = 1;
        cpyCache = null;
    }
    /**
     * Constructor with arguments
     * <p>
     *     Sets up the object using passed values
     * </p>
     * @param mons is for the ratio of monsters
     * @param spells is for the ratio of spells
     * @param traps is for the ratio of traps
     */
    public DeckBuilder(Deck deck, int mons, int spells, int traps) {
        suggestions = new ArrayList<>();
        /*Sets up a new Deck*/
        try {
            tmpDeck = deck.clone();
            setupData();
            /*Sets the ratioMonsters to a value*/
            ratioMonsters = mons;
            /*Sets the ratioSpells to a value*/
            ratioSpells = spells;
            /*Sets the ratioTraps to a value*/
            ratioTraps = traps;
            /*Set up the cache*/
            cpyCache = cachePrep();
            /*Fill the suggestion list*/
            while(suggestions.size() < 3 && deck.getOnlyDeck().size() > 0) {
                suggestions.add(handleSuggestion(determineNextCard()));
            }
        }
        catch (Exception e) {
            System.out.println("[ERROR] Deck could not be cloned successfully");
        }
    }
    /**
     * Function definition for cachePrep()
     * <p>
     *     Creates a copy of the user's cache
     * </p>
     * @return the cache copy
     */
    public Cache cachePrep() {
        try {
            return ProgramFunctions.getProgramData().getCache().clone();
        }
        catch (Exception e) {
            System.out.println("[ERROR] Cache could not be cloned successfully");
        }
        return null;
    }
    /**
     * Function definition for setupData()
     * <p>
     *     Calculates all the current numbers of cards in a given deck
     * </p>
     */
    public void setupData() {
        for(Card c : tmpDeck.getOnlyDeck()) {
            if(c instanceof SpellCard) {
                numberOfSpells++;
            }
            if(c instanceof TrapCard) {
                numberOfTraps++;
            }
            if(c instanceof SynchroMonster || c instanceof XYZMonster || c instanceof LinkMonster || c instanceof FusionMonster) {
                continue;
            }
            if(c instanceof MonsterCard) {
                numberOfMonsters++;
            }
        }
    }
    /**
     * Function definition for addCard()
     * <p>
     *     Checks the card type and increments the counters accordingly
     * </p>
     * @param C is the Card to be added
     */
    public void addCard(Card C) {
        /*Check if card is a trap card*/
        if(C instanceof TrapCard) {
            /*If the card is added successfully*/
            if(tmpDeck.addCard(C)) {
                /*Increment the counter*/
                numberOfTraps++;
            }
        }
        /*Check if the card is a spell card*/
        else if(C instanceof SpellCard) {
            /*If the card is added successfully*/
            if(tmpDeck.addCard(C)) {
                /*Increment the counter*/
                numberOfSpells++;
            }
        }
        /*Check if the card is an extra deck monster*/
        else if(C instanceof SynchroMonster || C instanceof FusionMonster || C instanceof LinkMonster || C instanceof XYZMonster) {
            tmpDeck.addExtraDeckCard(C);
        }
        /*Else*/
        else {
            /*If the card is added successfully*/
            if(tmpDeck.addCard(C)) {
                /*Increment the counter*/
                numberOfMonsters++;
            }
        }
        suggestions = new ArrayList<>();
        while(suggestions.size() < 3) {
                suggestions.add(handleSuggestion(determineNextCard()));
        }
        /*Return*/
        return;
    }
    /**
     * Function definition for removeCard()
     * <p>
     *     Removes a Card from the Deck and decrements the counters accordingly
     * </p>
     * @param C is the Card to be removed
     */
    public void removeCard(Card C) {
        /*Check if card is a trap card*/
        if(C instanceof TrapCard) {
            /*If the card is added successfully*/
            if(tmpDeck.removeCard(C)) {
                /*Increment the counter*/
                numberOfTraps--;
            }
        }
        /*Check if the card is a spell card*/
        else if(C instanceof SpellCard) {
            /*If the card is added successfully*/
            if(tmpDeck.removeCard(C)) {
                /*Increment the counter*/
                numberOfSpells--;
            }
        }
        /*Check if the card is an extra deck monster*/
        else if(C instanceof SynchroMonster || C instanceof FusionMonster || C instanceof LinkMonster || C instanceof XYZMonster) {
            /*Add the card*/
            tmpDeck.removeCard(C);
        }
        /*Else*/
        else {
            /*If the card is added successfully*/
            if(tmpDeck.removeCard(C)) {
                /*Increment the counter*/
                numberOfMonsters--;
            }
        }
        suggestions = new ArrayList<>();
        while(suggestions.size() < 3) {
            suggestions.add(handleSuggestion(determineNextCard()));
        }
        /*Return*/
        return;
    }
    /**
     * Function definition for suggestMonster()
     * <p>
     *     Determines the monster to suggest next
     * </p>
     */
    public MonsterCard suggestMonster() {
        /*
        SET UP VARIABLES
         */
        ArrayList<MonsterCard> monsterlist;
        ArrayList<MonsterCard> deckMonsters = ProgramFunctions.getUtilities().getFilter().filterMonsters(tmpDeck.getOnlyDeck());
        ArrayList<FusionMonster> deckFusions = ProgramFunctions.getUtilities().getFilter().filterFusions(tmpDeck.getExtraDeck());
        ArrayList<RitualMonster> deckRituals = ProgramFunctions.getUtilities().getFilter().filterRituals(tmpDeck.getOnlyDeck());
        String archetype = findArchetype();
        MonsterCard suggestion = new MonsterCard();
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeUnowned()) {
            monsterlist = ProgramFunctions.getUtilities().getFilter().filterMonsters(ProgramFunctions.getProgramData().getCache().getSystemCards());
        }
        else {
            monsterlist = ProgramFunctions.getUtilities().getFilter().filterMonsters(cpyCache.getAlbumCards());
        }
        /*
        SET UP VARIABLES
         */

        /*
        CHECK IF THERE ARE FUSION CARDS NEEDED
         */
        /*
        if(deckFusions.size() > 0) {
            for(FusionMonster f : deckFusions) {
                ArrayList<String> dummy = f.getFusionMaterial();
                for(String x : f.getFusionMaterial()) {
                    for(MonsterCard m : deckMonsters) {
                        if(m.getCardName().equals(x)) {
                            dummy.remove(x);
                        }
                    }
                }
                if(dummy.size() > 0) {
                    for(MonsterCard c : monsterlist) {
                        if(c.getCardName().equals(dummy.get(0))) {
                            if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                                suggestion = c;
                            }
                        }
                    }
                    if(suggestion.getCardName() != null) {
                        return suggestion;
                    }
                }
            }
        }
        */
        /*
        CHECK IF THERE ARE FUSION CARDS NEEDED
         */

        /*
        CHECK FOR SYNCHRO REQUIREMENTS
         */



        /*
        CHECK FOR SYNCHRO REQUIREMENTS
         */

        /*
        CHECK FOR XYZ REQUIREMENTS
         */
        /*
        ArrayList<XYZMonster> xyzMonsters = ProgramFunctions.getUtilities().getFilter().filterXYZs(tmpDeck.getExtraDeck());
        boolean xyzflag = false;
        if(xyzMonsters.size() > 0) {
            for(XYZMonster c : xyzMonsters) {
                int numberOfMons = c.getNumberXYZMaterials();
                int levelOfMons = c.getLevelXYZMaterials();
                int counter = 0;
                xyzflag = true;
                for(MonsterCard m : deckMonsters) {
                    if(m.getLevel() == levelOfMons) {
                        counter++;
                    }
                    if(counter == numberOfMons) {
                        xyzflag = false;
                        break;
                    }
                }
                if(xyzflag) {
                    ArrayList<MonsterCard> filteredCards = ProgramFunctions.getUtilities().getFilter().filterMonsterLevel(monsterlist, levelOfMons);
                    for(MonsterCard s : filteredCards) {
                        if(s.getValue() >= suggestion.getValue()) {
                            if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                                suggestion = c;
                            }
                        }
                    }
                    if(suggestion.getCardName() != null) {
                        return suggestion;
                    }
                }
            }
        }
        */
        /*
        CHECK FOR XYZ REQUIREMENTS
         */

        /*
        CHECK FOR LINK REQUIREMENTS
         */



        /*
        CHECK FOR LINK REQUIREMENTS
         */

        /*
        CHECK FOR IN-DECK REQUIREMENTS
         */



        /*
        CHECK FOR IN-DECK REQUIREMENTS
         */

        /*
        CHECK FOR ARCHETYPE
         */
        if(archetype != "" || archetype != null) {
            ArrayList<MonsterCard> monsters = ProgramFunctions.getUtilities().getFilter().filterMonsterArchetype(monsterlist, archetype);
            for(MonsterCard c : monsters) {
                if(c.getValue() >= suggestion.getValue()) {
                    if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                        System.out.println("Monster Stuck 1");
                        suggestion = c;
                    }
                }
            }
            if(suggestion.getCardName() != null) {
                return suggestion;
            }
        }
        /*
        CHECK FOR ARCHETYPE
         */

         /*
        IF ALL ELSE FAILS USE A TYPE CARD
         */
        MonsterAttribute commonAttr = findMostCommonAttribute();
        MonsterType commonType = findMostCommonType();
        ArrayList<MonsterCard> typeList = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterlist, commonType);
        ArrayList<MonsterCard> attrList = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(typeList, commonAttr);
        System.out.println("[SYSTEM] Attribute is : " + commonAttr + " and Type is : " + commonType);
        boolean suggestionFlag = false;
        for(MonsterCard c : attrList) {
            if(c.getValue() >= suggestion.getValue()) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    suggestion = c;
                    System.out.println("Monster Stuck 2");
                    suggestionFlag = true;
                }
            }
        }
        for(MonsterCard c : typeList) {
            if(c.getValue() >= suggestion.getValue()) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    suggestion = c;
                    System.out.println("Monster Stuck 3");
                    suggestionFlag = true;
                }
            }
        }
        if(suggestionFlag) {
            return suggestion;
        }
        /*
        IF ALL ELSE FAILS USE A TYPE CARD
         */

        /*
        IF EVERYTHING ELSE GOES WRONG
         */
        Collections.sort(monsterlist, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Double.compare(c1.getValue(), c2.getValue());
            }
        });
        for(int i = 0; i < monsterlist.size(); i++) {
                for (Card C : suggestions) {
                        System.out.println("Testing " + C.getCardName() + " against " + monsterlist.get(i).getCardName());
                        if (!(C.getCardName().equals(monsterlist.get(i).getCardName()))) {
                            return monsterlist.get(i);
                        }
                    }

            }
        return monsterlist.get(0);
        /*
        IF EVERYTHING ELSE GOES WRONG
         */
    }
    /**
     * Function definition for suggestSpell()
     * <p>
     *     Determines the spell to suggest
     * </p>
     */
    public SpellCard suggestSpell() {
        /*
        SET UP VARIABLES
         */
        ArrayList<SpellCard> spellList;
        ArrayList<SpellCard> deckSpells = ProgramFunctions.getUtilities().getFilter().filterSpells(tmpDeck.getOnlyDeck());
        ArrayList<FusionMonster> deckFusions = ProgramFunctions.getUtilities().getFilter().filterFusions(tmpDeck.getExtraDeck());
        ArrayList<RitualMonster> deckRituals = ProgramFunctions.getUtilities().getFilter().filterRituals(tmpDeck.getOnlyDeck());
        SpellCard suggestion = new SpellCard();
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeUnowned()) {
            spellList = ProgramFunctions.getUtilities().getFilter().filterSpells(ProgramFunctions.getProgramData().getCache().getSystemCards());
        }
        else {
            spellList = ProgramFunctions.getUtilities().getFilter().filterSpells(cpyCache.getAlbumCards());
        }
        /*
        SET UP VARIABLES
         */

        /*
        CHECK IF THERE ARE FUSION CARDS NEEDED
         */
        /*
        boolean fusionCheck = false;
        if(deckFusions.size() > 0) {
            for(SpellCard S : deckSpells) {
                if(S.getCardDescription().contains("fusion") || S.getCardName().equals("Polymerization")) {
                    fusionCheck = true;
                    break;
                }
            }
            if(fusionCheck != true) {
                for (SpellCard c : spellList) {
                    if (c.getCardDescription().contains("fusion")) {
                        if (c.getValue() >= suggestion.getValue()) {
                            if (canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                                suggestion = c;
                            }
                        }
                    }
                }
                if(suggestion.getCardName() != null) {
                    return suggestion;
                }
            }
        }
        */
        /*
        CHECK IF THERE ARE FUSION CARDS NEEDED
         */

        /*
        CHECK IF THERE ARE RITUAL CARDS NEEDED
         */
        /*
        boolean ritualCheck = false;
        for(RitualMonster c : deckRituals) {
            for(SpellCard s : deckSpells) {
                if(c.getSpellName().equals(s.getCardName())) {
                    ritualCheck = true;
                }
                else {
                    continue;
                }
            }
            if(ritualCheck == false) {
                for(SpellCard s : spellList) {
                    if(s.getCardName().equals(c.getSpellName()) && cardNameChecker(c.getCardName()) == false) {
                        suggestion = s;
                    }
                }
                if(suggestion.getCardName() != null) {
                    return suggestion;
                }
            }
        }
        */
        /*
        CHECK IF THERE ARE RITUAL CARDS NEEDED
         */

        /*
        CHECK FOR ARCHETYPE
         */
        String archetype = findArchetype();
        String archetpyeNoSpace = archetype.replace(" ", "");
        if(archetype != "" || archetype != null) {
            ArrayList<SpellCard> spells = ProgramFunctions.getUtilities().getFilter().filterSpellArchetype(spellList, archetpyeNoSpace);
            for(SpellCard c : spells) {
                if(c.getEffValue() >= suggestion.getValue()) {
                    if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                        System.out.println("Spell Stuck 1");
                        suggestion = c;
                    }
                }
            }
            if(suggestion.getCardName() != null) {
                return suggestion;
            }
        }
        /*
        CHECK FOR ARCHETYPE
         */

        MonsterAttribute commonAttr = findMostCommonAttribute();
        MonsterType commonType = findMostCommonType();
        System.out.println("[SYSTEM] Attribute is : " + commonAttr + " and Type is : " + commonType);
        for(SpellCard c : spellList) {
            if(c.getCardDescription().contains(commonAttr.toString())) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    System.out.println("Spell Stuck 2");
                    suggestion = c;
                }
            }
        }
        for(SpellCard c : spellList) {
            if(c.getCardDescription().toUpperCase().contains(commonType.toString().replace("_", " "))) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    System.out.println("Spell Stuck 3");
                    suggestion = c;
                }
            }
        }
        if(suggestion.getCardName() != null) {
            return suggestion;
        }
        /*
        IF ALL ELSE FAILS USE A DEFAULT CARD
         */
        boolean suggestionFlag = false;
        for(SpellCard c : spellList) {
            for(String s : ProgramFunctions.getUtilities().getFileHandler().loadDefaultSpellList()) {
                if(c.getCardName().equals(s)) {
                    if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                        suggestion = c;
                        System.out.println("Spell Stuck 4");
                        suggestionFlag = true;
                    }
                }
            }
        }
        if(suggestionFlag) {
            return suggestion;
        }
        /*
        IF ALL ELSE FAILS USE A DEFAULT CARD
         */

        /*
        ASSUMING EVERYTHING FAILS RETURN HIGHEST VALUE CARD OWNED CARD
         */
        Collections.sort(spellList, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Double.compare(c1.getValue(), c2.getValue());
            }
        });
        if(spellList.size() > 0) {
            System.out.println("Spell is " + spellList.get(0));
            return spellList.get(0);
        }
        return spellList.get(0);
    }
    /**
     * Function definition for suggestTrap()
     * <p>
     *     Determines the trap to suggest
     * </p>
     */
    public TrapCard suggestTrap() {
        /*
        SET UP VARIABLES
         */
        ArrayList<TrapCard> trapList;
        ArrayList<TrapCard> deckTraps = ProgramFunctions.getUtilities().getFilter().filterTraps(tmpDeck.getOnlyDeck());
        TrapCard suggestion = new TrapCard();
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeUnowned()) {
            trapList = ProgramFunctions.getUtilities().getFilter().filterTraps(cpyCache.getSystemCards());
        }
        else {
            trapList = ProgramFunctions.getUtilities().getFilter().filterTraps(cpyCache.getAlbumCards());
        }
        /*
        SET UP VARIABLES
         */

        /*
        CHECK FOR ARCHETYPE
         */
        String archetype = findArchetype();
        if(archetype != "" || archetype != null) {
            ArrayList<TrapCard> traps = ProgramFunctions.getUtilities().getFilter().filterTrapArchetype(trapList, archetype);
            if(traps.size() > 0) {
                for (TrapCard c : traps) {
                    if (c.getEffValue() >= suggestion.getValue()) {
                        if (canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                            suggestion = c;
                        }
                    }
                }
                if(suggestion.getCardName() != null) {
                    return suggestion;
                }
            }
        }
        /*
        CHECK FOR ARCHETYPE
         */

        MonsterAttribute commonAttr = findMostCommonAttribute();
        MonsterType commonType = findMostCommonType();
        for(TrapCard c : trapList) {
            if(c.getCardDescription().contains(commonAttr.toString())) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    System.out.println("Trap Stuck 1");
                    suggestion = c;
                }
            }
        }
        for(TrapCard c : trapList) {
            if(c.getCardDescription().toUpperCase().contains(commonType.toString().replace("_", " "))) {
                if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                    System.out.println("Trap Stuck 2");
                    suggestion = c;
                }
            }
        }
        if(suggestion.getCardName() != null) {
            return suggestion;
        }

        /*
        IF ALL ELSE FAILS USE A DEFAULT CARD
         */
        boolean suggestionFlag = false;
        for(TrapCard c : trapList) {
            for(String s : ProgramFunctions.getUtilities().getFileHandler().loadDefaultTrapList()) {
                if(c.getCardName().equals(s)) {
                    if(canAdd(c) && cardNameChecker(c.getCardName()) == false) {
                        suggestion = c;
                        System.out.println("Trap Stuck 3");
                        suggestionFlag = true;
                        break;
                    }
                }
            }
        }
        if(suggestionFlag) {
            return suggestion;
        }

        /*
        ASSUMING EVERYTHING FAILS RETURN HIGHEST VALUE CARD OWNED CARD
         */
        Collections.sort(trapList, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Double.compare(c1.getValue(), c2.getValue());
            }
        });
        System.out.println("Trap is " + trapList.get(0).getCardName());
        return trapList.get(0);
    }
    /**
     * Function definition for handleSuggestion()
     * <p>
     *     Determines what to do based on a suggestion
     * </p>
     * @param nextCard is the type of card to be suggested
     */
    public Card handleSuggestion(String nextCard) {
        Card c;
        /*Determine Card type*/
        switch(nextCard) {
            case "MONSTER":
                c = suggestMonster();
                return c;
            case "SPELL":
                c = suggestSpell();
                return c;
            case "TRAP":
                c = suggestTrap();
                return c;
            default:
                break;
        }
        return null;
    }
    /**
     * Function definition for determineNextCard()
     * <P>
     *     Based off the current and ideal ratios, determines what card is required next
     * </P>
     * @return the type of card required
     */
    public String determineNextCard() {
        /*Create an integer to hold the total cards in the deck*/
        float total = numberOfMonsters + numberOfTraps + numberOfSpells;
        /*Determine the smallest ratio*/
        float smolBoi = findSmallest(numberOfMonsters, numberOfSpells, numberOfTraps);
        /*Divide by the smallest ratio*/
        float currentRatMons = numberOfMonsters / smolBoi;
        /*Divide by the smallest ratio*/
        float currentRatSpells = numberOfSpells / smolBoi;
        /*Divide by the smallest ratio*/
        float currentRatTraps = numberOfTraps / smolBoi;
        System.out.println("[SYSTEM] Deck Builder determines final ration of " + currentRatMons + ":" + currentRatSpells + ":" + currentRatTraps);
        System.out.println("[SYSTEM] Optimal ratio is " + ratioMonsters + ":" + ratioSpells + ":" + ratioTraps);
        float totalRatios = ratioMonsters + ratioTraps + ratioSpells;
        float optimalMonsters = (total / totalRatios) * ratioMonsters;
        float optimalSpells = (total / totalRatios) * ratioSpells;
        float optimalTraps = (total / totalRatios) * ratioTraps;
        float monDiff = (numberOfMonsters - optimalMonsters) * -1;
        float spellDiff = (numberOfSpells - optimalSpells) * -1;
        float trapDiff = (numberOfTraps - optimalTraps) * -1;
        float largest = findLargest(monDiff, spellDiff, trapDiff);
        if(largest == monDiff) {
            return "MONSTER";
        }
        else if(largest == spellDiff) {
            return "SPELL";
        }
        else if(largest == trapDiff) {
            return "TRAP";
        }
        else {
            return "ERROR";
        }
    }
    /**
     * Function definition for findSmallest()
     * <p>
     *     Determines and returns the smallest float out of three
     * </p>
     * @param a is an float to test
     * @param b is an float to test
     * @param c is an float to test
     * @return the smallest of the three floats
     */
    public float findSmallest(float a, float b, float c) {
        /*If a is the smallest*/
        if((a < b && a < c) || (a < b && c <= 0) || (a < c && b <= 0)) {
            /*Return a*/
            return a;
        }
        /*If b is the smallest*/
        else if((b < a && b < c) || (b < a && c <= 0) || (b < c && a <=0 )){
            /*Return b*/
            return b;
        }
        /*If c is the smallest*/
        else if((c < a && c < b) || (c < a && b >= 0) || (c < b && a <= 0)) {
            return c;
        }
        /*Assuming they are all equal, return one*/
        return a;
    }
    /**
     * Function definition for findLargest()
     * <p>
     *     Determines and returns the largest number out of three inputs
     * </p>
     * @param a is a number to be tested
     * @param b is a number to be tested
     * @param c is a number to be tested
     * @return the largest number
     */
    public float findLargest(float a, float b, float c) {
        /*If a is the largest*/
        if(a > b && a > c) {
            /*Return a*/
            return a;
        }
        /*If b is the largest*/
        else if(b > a && b > c) {
            /*Return b*/
            return b;
        }
        /*If c is the largest*/
        else if(c > a && c > b) {
            return c;
        }
        else if(c > a && b > a && c == b) {
            return b;
        }
        /*Assuming they are all equal, return one*/
        return a;
    }
    /**
     * Function definition for setTmpDeck()
     * <P>
     *     Sets the deck to a passed deck
     * </P>
     * @param newDeck is the deck to be used
     */
    public void setTmpDeck(Deck newDeck) {
        tmpDeck = newDeck;
    }
    /**
     * Function definition for getTmpDeck()
     * <p>
     *     Returns the deck being worked on
     * </p>
     * @return the tmpDeck
     */
    public Deck getTmpDeck() {
        return tmpDeck;
    }
    /**
     * Function definition for getDetails()
     * <P>
     *     Returns the information on the deck
     * </P>
     * @return deck information
     */
    public String getDetails() {
        return "DETAILS:\nDECK NAME: " + tmpDeck.getContainerName() + "\nTOTAL CARDS IN DECK: " + (tmpDeck.getOnlyDeck().size()) + "\nTOTAL EXTRA DECK CARDS: " + tmpDeck.getExtraDeck().size();
    }
    /**
     * Function definition for findArchetype()
     * <p>
     *     Scans cards in the deck to find the archetype with the highest number
     *     of cards within the deck
     * </p>
     * @return the Archetype name
     */
    public String findArchetype() {
        /*Load the archetype list*/
        ArrayList<String> archetypeList = ProgramFunctions.getUtilities().getFileHandler().loadArchetypeList();
        /*Get only the deck cards*/
        ArrayList<Card> deck = tmpDeck.getOnlyDeck();
        /*Create a temporary string*/
        String tmp = "";
        /*Setup a percentage variable*/
        float currPercentage = 0;
        /*For all archetypes*/
        for(String s : archetypeList) {
            /*Reset the counter*/
            float counter = 0;
            /*For all cards in the deck*/
            for (Card aDeck : deck) {
                if(aDeck instanceof EffectMonster || aDeck instanceof FusionMonster || aDeck instanceof SynchroMonster || aDeck instanceof  XYZMonster || aDeck instanceof LinkMonster || aDeck instanceof PendulumMonster || aDeck instanceof  SpellCard || aDeck instanceof TrapCard) {
                    /*If the card is of the archetype*/
                    if (aDeck.getCardName().contains(s) || aDeck.getCardName().contains(s)) {
                        counter++;
                    }
                }
                else {
                    /*If the card is of the archetype*/
                    if (aDeck.getCardName().contains(s)) {
                        counter++;
                    }
                }
            }
            /*Calculate the percentage*/
            float percentage = (counter / deck.size()) * 100;
            /*If we have a new highest percentage*/
            if(percentage > currPercentage) {
                /*Set the tmp variable to the archetype*/
                tmp = s;
                currPercentage = percentage;
            }
        }
        /*Return the archetype*/
        return tmp;
    }
    /**
     * Function definition for findMostCommonType()
     * <p>
     *     Scans the deck and finds the most common type
     * </p>
     * @return the most common type
     */
    public MonsterType findMostCommonType() {
        /*Filter only deck monsters*/
        ArrayList<MonsterCard> deckMons = ProgramFunctions.getUtilities().getFilter().filterMonsters(tmpDeck.getOnlyDeck());
        /*Set the current type to a dummy value*/
        MonsterType currentValue = MonsterType.DRAGON;
        /*Setup the current size*/
        int curSize = 0;
        /*For every type*/
        for (MonsterType type : MonsterType.values()) {
            /*Filter by that type*/
            ArrayList<MonsterCard> value = ProgramFunctions.getUtilities().getFilter().filterMonsterType(deckMons, type);
            /*If it is a bigger list set the curSize to that*/
            if(value.size() > curSize) {
                curSize = value.size();
                currentValue = type;
            }
        }
        /*Return the most common type*/
        return currentValue;
    }
    /**
     * Function definition for findMostCommonAttribute()
     * <p>
     *     Finds the most common attribute in the deck
     * </p>
     * @return the most common MonsterAttribute
     */
    public MonsterAttribute findMostCommonAttribute() {
        /*Get all the monsters in the deck*/
        ArrayList<MonsterCard> deckMons = ProgramFunctions.getUtilities().getFilter().filterMonsters(tmpDeck.getOnlyDeck());
        /*Dummy value*/
        MonsterAttribute currentValue = MonsterAttribute.LIGHT;
        /*Tmp size*/
        int curSize = 0;
        /*For all possible attributes*/
        for (MonsterAttribute attr : MonsterAttribute.values()) {
            /*Count occurrences*/
            ArrayList<MonsterCard> value = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(deckMons, attr);
            /*If there are more occurrences than current record*/
            if(value.size() > curSize) {
                /*Set the variables accordingly*/
                curSize = value.size();
                currentValue = attr;
            }
        }
        /*Return the most common attribute*/
        return currentValue;
    }
    /**
     * Function definition for canAdd()
     * <p>
     *     Checks whether the card it is considering suggesting can actually be added, and also
     *     whether it is forbidden or semi-limited
     * </p>
     * @param c is the card to test
     * @return whether card can be added
     */
    public boolean canAdd(Card c) {
        /*Start with 3 allowed*/
        int numAllowed = 3;
        /*Load the banlist*/
        BanList banned = ProgramFunctions.getUtilities().getFileHandler().loadBanList();
        /*Load the ForbiddenLimited objects*/
        ArrayList<LimitedCard> forbiddenLimiteds = banned.getList();
        /*For all forbiddenLimited objects*/
        for(LimitedCard f : forbiddenLimiteds) {
            /*If the cardName matches*/
            if(f.getCardName().equals(c.getCardName())) {
                /*If the card is forbidden, return false*/
                if(f.getNumberOfCopies() == 0) {
                    return false;
                }
                /*Otherwise change the numAllowed*/
                else {
                    numAllowed = f.getNumberOfCopies();
                    break;
                }
            }
        }
        /*Get the cards in the deck*/
        ArrayList<Card> inDeck = tmpDeck.getOnlyDeck();
        /*Set the count*/
        int count = 0;
        /*For all cards in the deck*/
        for(Card a : inDeck) {
            /*if the names match*/
            if(a.getCardName().equals(c.getCardName())) {
                /*Increment the counter*/
                count++;
            }
        }
        /*If the counter is greater than or equal to the numAllowed*/
        if(count >= numAllowed) {
            /*Return false*/
            return false;
        }
        /*Otheriwse return true*/
        return true;
    }
    /**
     * Function definition for checkForbidden()
     * <p>
     *     Checks the deck and extra deck for forbidden cards
     * </p>
     * @return whether the deck contains illegal cards
     */
    public boolean checkForbidden() {
        /*For all ForbiddenLimited objects*/
        for(LimitedCard S : ProgramFunctions.getUtilities().getFileHandler().loadBanList().getList()) {
            /*For all cards in the deck*/
            for(Card c : tmpDeck.getOnlyDeck()) {
                /*If the card is forbidden*/
                if(c.getCardName().equals(S.getCardName()) && S.getNumberOfCopies() == 0) {
                    /*Return true*/
                    return true;
                }
            }
            /*For all cards in the extra deck*/
            for(Card c : tmpDeck.getExtraDeck()) {
                /*If the card is forbidden*/
                if(c.getCardName().equals(S.getCardName()) && S.getNumberOfCopies() == 0) {
                    /*Return true*/
                    return true;
                }
            }
        }
        /*Otherwise return false*/
        return false;
    }
    /**
     * Function definition for updateStatusFlag()
     * <p>
     *     Performs checks to update the statusFlag
     * </p>
     */
    public void updateStatusFlag() {
        /*If the deck is less then min size*/
        if(tmpDeck.getOnlyDeck().size() < 40) {
            /*Set the flag to 0*/
            statusFlag = 0;
        }
        /*If there are forbidden cards*/
        else if(checkForbidden()) {
            /*Set the flag to 1*/
            statusFlag = 1;
        }
        /*Otherwise*/
        else {
            /*Set the flag to 2*/
            statusFlag = 2;
        }
    }
    /**
     * Function definition for searchCard()
     * <p>
     *     Searches the cpyCache for copies of cards
     * </p>
     * @param name is the name of the card to look for
     * @return the results
     */
    public ArrayList<SearchResult> searchCard(String name) {
        /*Check the name is not null or whitespace*/
        if(name.equals("") || name == null) {
            /*Return null*/
            return null;
        }
        /*Make an ArrayList*/
        ArrayList<SearchResult> cardList = new ArrayList<>();
        /*For all containers*/
        if(ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings().isIncludeUnowned()) {
            for (Card a : cpyCache.getSystemCards()) {
                if (name.equals(a.getCardName()) || a.getCardName().contains(name)) {
                    cardList.add(new SearchResult(a.getCardName(), Integer.toString(cpyCache.getSystemCards().indexOf(a)), a.getCardID()));
                }
            }
        }
        else {
            for(Album A : cpyCache.getAlbums()) {
                for(Card a : A.getCards()) {
                    if (name.equals(a.getCardName()) || a.getCardName().contains(name)) {
                        cardList.add(new SearchResult(a.getCardName(), A.getContainerName(), a.getCardID()));
                    }
                }
            }
        }
        /*Return the cardList*/
        return cardList;
    }
    /**
     * Function definition for cardNameChecker()
     * <p>
     *     Returns whether the card is in the suggestion list
     * </p>
     * @param name is the card to check
     * @return whether in the list
     */
    private boolean cardNameChecker(String name) {
        /*For all the cards in the suggestion list*/
        for(Card c : suggestions) {
            /*If the name matches a card*/
            if(name.equals(c.getCardName())) {
                /*Return true*/
                return true;
            }
        }
        /*Otheriwse return false*/
        return false;
    }
    /**
     * Function definition for getStatusFlag()
     * <p>
     *     Updates and returns the statusFlag
     * </p>
     * @return the statusFlag
     */
    public int getStatusFlag() {
        updateStatusFlag();
        return statusFlag;
    }
    /**
     * Function definition for getSuggestions()
     * <p>
     *     Returns the suggestions
     * </p>
     * @return the suggestions
     */
    public ArrayList<Card> getSuggestions() {
        return suggestions;
    }
    /**
     * Function definition for getCpyCache()
     * <p>
     *     Returns the cpyCache
     * </p>
     * @return the cpyCache
     */
    public Cache getCpyCache() {
        return cpyCache;
    }
}
