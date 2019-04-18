/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling.controllers;
import dataStructure.cardHierarchy.Card;
import dataStructure.cardHierarchy.MonsterCard;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterType;
import dataStructure.cardHierarchy.enumerators.SpellType;
import dataStructure.cardHierarchy.enumerators.TrapType;
import dataStructure.containerHierarchy.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import programFunctions.ProgramFunctions;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
public class DeckAnalysisController implements Initializable {
    /**
     * overviewChart holds a PieChart
     */
    @FXML private PieChart overviewChart;
    /**
     * spellChart holds a PieChart
     */
    @FXML private PieChart spellChart;
    /**
     * trapChart holds a PieChart
     */
    @FXML private PieChart trapChart;
    /**
     * monsterChart1 holds a PieChart
     */
    @FXML private PieChart monsterChart1;
    /**
     * monsterChart2 holds a PieChart
     */
    @FXML private PieChart monsterChart2;
    /**
     * monsterChart3 holds a PieChart
     */
    @FXML private PieChart monsterChart3;
    /**
     * overviewText holds details
     */
    @FXML private TextArea overviewText;
    /**
     * spellText holds spell details
     */
    @FXML private TextArea spellText;
    /**
     * trapText holds trap details
     */
    @FXML private TextArea trapText;
    /**
     * monsterText holds the monster details
     */
    @FXML private TextArea monsterText;
    /**
     * redraw holds the button for redraw
     */
    @FXML private Button redraw;
    /**
     * testHandText holds the test hand info
     */
    @FXML private TextArea testHandText;
    /**
     * probabilities holds the probability of drawing a card
     */
    @FXML private TextArea probabilities;
    /**
     * numSpells holds the number of spells
     */
    private int numSpells;
    /**
     * numTraps holds the number of traps
     */
    private int numTraps;
    /**
     * numMonsters holds the number of monsters
     */
    private int numMonsters;
    /**
     * deck holds the deck being analyzed
     */
    private Deck deck;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck = ProgramFunctions.getProgramData().getUserInterface().accessSceneCache().getDeck();
        testHandText.setWrapText(true);
        getProbabilities();
        getOverviewData();
        getSpellData();
        getTrapData();
        getMonsterData();
        redraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Card> cards = new ArrayList<>();
                cards.addAll(deck.getOnlyDeck());
                draw(cards);
            }
        });
    }
    /**
     * Function definition for draw()
     * <p>
     *     Draws and removes cards from the deck and outputs the draw to the test hand box
     * </p>
     * @param cards is the card pool to draw from
     */
    private void draw(ArrayList<Card> cards) {
        testHandText.clear();
        ArrayList<Card> draws = new ArrayList<>();
        while(draws.size() < 5) {
            Random rnd = new Random();
            int curIndex = rnd.nextInt(cards.size());
            draws.add(cards.get(curIndex));
            cards.remove(curIndex);
        }
        for(Card c : draws) {
            testHandText.appendText(c.getCardName() + "\n");
        }
        return;
    }
    /**
     * Function definition for getOverviewData()
     * <p>
     *     Calculates the overview data of the album and puts it in the appropriate text area
     * </p>
     */
    private void getOverviewData() {
        numSpells = ProgramFunctions.getUtilities().getFilter().filterSpells(deck.getCards()).size();
        numTraps = ProgramFunctions.getUtilities().getFilter().filterTraps(deck.getCards()).size();
        numMonsters = ProgramFunctions.getUtilities().getFilter().filterMonsters(deck.getCards()).size();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Monsters", numMonsters),
                        new PieChart.Data("Spells", numSpells),
                        new PieChart.Data("Traps", numTraps));
        overviewChart.setData(pieChartData);
        overviewChart.setTitle("Overview");
        overviewText.appendText("Name: " + deck.getContainerName() + "\n");
        overviewText.appendText("Number of Spells: " + numSpells + "\n");
        overviewText.appendText("Number of Traps: " + numTraps + "\n");
        overviewText.appendText("Number of Monsters: " + numMonsters + "\n");
        overviewText.appendText("TOTAL CARDS: " + deck.getCards().size() + "\n");
    }
    /**
     * Function definition for getSpellData()
     * <p>
     *     Calculates the spell data for the album and puts the details in the appropriate text area
     * </p>
     */
    private void getSpellData() {
        int numField = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.FIELD).size();
        int numQuick = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.QUICK_PLAY).size();
        int numNorm = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.NORMAL).size();
        int numRit = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.RITUAL).size();
        int numCont = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.CONTINUOUS).size();
        int numEqu = ProgramFunctions.getUtilities().getFilter().filterSpellType(deck.getCards(), SpellType.EQUIP).size();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Field Spells", numField),
                        new PieChart.Data("Quick-Play Spells", numQuick),
                        new PieChart.Data("Ritual Spells", numRit),
                        new PieChart.Data("Continuous Spells", numCont),
                        new PieChart.Data("Equip Spells", numEqu),
                        new PieChart.Data("Normal Spells", numNorm));
        spellChart.setData(pieChartData);
        spellChart.setTitle("Spell Overview");
        spellText.appendText("Number of Field Spells: " + numField + "\n");
        spellText.appendText("Number of Quick-Play Spells: " + numQuick + "\n");
        spellText.appendText("Number of Ritual Spells: " + numRit + "\n");
        spellText.appendText("Number of Continuous Spells: " + numCont + "\n");
        spellText.appendText("Number of Equip Spells: " + numEqu + "\n");
        spellText.appendText("Number of Normal Spells: " + numNorm + "\n");
        spellText.appendText("TOTAL SPELLS: " + numSpells + "\n");
    }
    /**
     * Function definition for getTrapData()
     * <p>
     *     Calculates the trap data and outputs the details to the appropriate text area
     * </p>
     */
    private void getTrapData() {
        int numNorm = ProgramFunctions.getUtilities().getFilter().filterTrapType(deck.getCards(), TrapType.NORMAL).size();
        int numCont = ProgramFunctions.getUtilities().getFilter().filterTrapType(deck.getCards(), TrapType.CONTINUOUS).size();
        int numCount = ProgramFunctions.getUtilities().getFilter().filterTrapType(deck.getCards(),TrapType.COUNTER).size();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Normal Traps", numNorm),
                        new PieChart.Data("Continuous Traps", numCont),
                        new PieChart.Data("Counter Traps", numCount));
        trapChart.setData(pieChartData);
        trapChart.setTitle("Trap Overview");
        trapText.appendText("Number of Normal Traps: " + numNorm + "\n");
        trapText.appendText("Number of Continuous Traps: " + numCont + "\n");
        trapText.appendText("Number of Counter Traps: " + numCount + "\n");
        trapText.appendText("TOTAL TRAPS: " + numTraps + "\n");
    }
    /**
     * Function definition for getMonsterData()
     * <p>
     *     Calculates the monster data and outputs the details to the appropriate text area
     * </p>
     */
    private void getMonsterData() {
        int numFusion = ProgramFunctions.getUtilities().getFilter().filterFusions(deck.getCards()).size();
        int numSynchro = ProgramFunctions.getUtilities().getFilter().filterSynchros(deck.getCards()).size();
        int numXYZ = ProgramFunctions.getUtilities().getFilter().filterXYZs(deck.getCards()).size();
        int numPend = ProgramFunctions.getUtilities().getFilter().filterPendulums(deck.getCards()).size();
        int numRit = ProgramFunctions.getUtilities().getFilter().filterRituals(deck.getCards()).size();
        int numEffect = ProgramFunctions.getUtilities().getFilter().filterEffects(deck.getCards()).size() - numFusion - numSynchro - numXYZ - numPend - numRit;
        int numNormal = ProgramFunctions.getUtilities().getFilter().filterMonsters(deck.getCards()).size() - numFusion - numSynchro - numXYZ - numPend - numEffect - numRit;
        ObservableList<PieChart.Data> pieChartData1 =
                FXCollections.observableArrayList(
                        new PieChart.Data("Normal Monsters", numNormal),
                        new PieChart.Data("Effect Monsters", numEffect),
                        new PieChart.Data("Fusion Monsters", numFusion),
                        new PieChart.Data("Synchro Monsters", numSynchro),
                        new PieChart.Data("XYZ Monsters", numXYZ),
                        new PieChart.Data("Ritual Monsters", numRit),
                        new PieChart.Data("Pendulum Monsters", numPend));
        monsterChart1.setData(pieChartData1);
        monsterChart1.setTitle("Card Types");
        ArrayList<MonsterCard> monsterCards = ProgramFunctions.getUtilities().getFilter().filterMonsters(deck.getCards());
        int numFire = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.FIRE).size();
        int numEarth = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.EARTH).size();
        int numWater = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.WATER).size();
        int numWind = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.WIND).size();
        int numDark = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.DARK).size();
        int numLight = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.LIGHT).size();
        int numDiv = ProgramFunctions.getUtilities().getFilter().filterMonsterAttribute(monsterCards, MonsterAttribute.DIVINE).size();
        ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList(
                        new PieChart.Data("Fire Monsters", numFire),
                        new PieChart.Data("Earth Monsters", numEarth),
                        new PieChart.Data("Water Monsters", numWater),
                        new PieChart.Data("Wind Monsters", numWind),
                        new PieChart.Data("Dark Monsters", numDark),
                        new PieChart.Data("Light Monsters", numLight),
                        new PieChart.Data("Divine Monsters", numDiv));
        monsterChart2.setData(pieChartData2);
        monsterChart2.setTitle("Monster Attribute");
        int numAqua = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.AQUA).size();
        int numBeast = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.BEAST).size();
        int numBeastWar = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.BEAST_WARRIOR).size();
        int numDino = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.DINOSAUR).size();
        int numDivBeast = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.DIVINE_BEAST).size();
        int numDrag = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.DRAGON).size();
        int numFair = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.FAIRY).size();
        int numFiend = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.FIEND).size();
        int numFish = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.FISH).size();
        int numInsect = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.INSECT).size();
        int numMach = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.MACHINE).size();
        int numPlant = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.PLANT).size();
        int numPsych = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.PSYCHIC).size();
        int numPyro = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.PYRO).size();
        int numRep = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.REPTILE).size();
        int numRock = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.ROCK).size();
        int numSea = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.SEA_SERPENT).size();
        int numSpellc = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.SPELLCASTER).size();
        int numThun = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.THUNDER).size();
        int numWar = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.WARRIOR).size();
        int numWing = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.WINGED_BEAST).size();
        int numWyrm = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.WYRM).size();
        int numZom = ProgramFunctions.getUtilities().getFilter().filterMonsterType(monsterCards, MonsterType.ZOMBIE).size();
        ObservableList<PieChart.Data> pieChartData3 =
                FXCollections.observableArrayList(
                        new PieChart.Data("Aqua Monsters", numAqua),
                        new PieChart.Data("Beast Monsters", numBeast),
                        new PieChart.Data("Beast-Warrior Monsters", numBeastWar),
                        new PieChart.Data("Dinosaur Monsters", numDino),
                        new PieChart.Data("Divine Beast Monsters", numDivBeast),
                        new PieChart.Data("Dragon Monsters", numDrag),
                        new PieChart.Data("Fairy Monsters", numFair),
                        new PieChart.Data("Fiend Monsters", numFiend),
                        new PieChart.Data("Fish Monsters", numFish),
                        new PieChart.Data("Insect Monsters", numInsect),
                        new PieChart.Data("Machine Monsters", numMach),
                        new PieChart.Data("Plant Monsters", numPlant),
                        new PieChart.Data("Psychic Monsters", numPsych),
                        new PieChart.Data("Pyro Monsters", numPyro),
                        new PieChart.Data("Reptile Monsters", numRep),
                        new PieChart.Data("Rock Monsters", numRock),
                        new PieChart.Data("Sea-Serpent Monsters", numSea),
                        new PieChart.Data("Spellcaster Monsters", numSpellc),
                        new PieChart.Data("Thunder Monsters", numThun),
                        new PieChart.Data("Warrior Monsters", numWar),
                        new PieChart.Data("Winged-Beast Monsters", numWing),
                        new PieChart.Data("Wyrm Monsters", numWyrm),
                        new PieChart.Data("Zombie Monsters", numZom));
        monsterChart3.setData(pieChartData3);
        monsterChart3.setTitle("Monster Type");
        monsterText.appendText("Number of Normal Monsters: " + numNormal + "            Number of Effect Monsters: " + numEffect + "\nNumber of Fusion Monsters: " + numFusion + "        Number of Synchro Monsters: " + numSynchro + "\nNumber of XYZ Monsters: " + numXYZ + "        Number of Pendulum Monsters: " + numPend + "\nNumber of Ritual Monsters: " + numRit + "\n");
        monsterText.appendText("Number of Fire Monsters: " + numFire  + "           Number of Earth Monsters: " + numEarth + "\nNumber of Water Monsters: " + numWater + "          Number of Wind Monsters: " + numWind + "\nNumber of Dark Monsters: " + numDark + "          Number of Light Monsters: " + numLight + "\nNumber of Divine Monsters: " + numDiv + "\n");
        monsterText.appendText("Number of Aqua Monsters: " + numAqua + "            Number of Beast Monsters: " + numBeast + "\nNumber of Beast-Warrior Monsters: " + numBeastWar + "           Number of Dinosaur Monsters: " + numDino + "\nNumber of Divine Beast Monsters: " + numDivBeast + "          Number of Dragon Monsters: " + numDrag + "\nNumber of Fairy Monsters: " + numFair + "           Number of Fiend Monsters: " + numFiend + "\nNumber of Fish Monsters: " + numFish + "            Number of Insect Monsters: " + numInsect + "\nNumber of Machine Monsters: " + numMach + "           Number of Plant Monsters: " + numPlant + "\nNumber of Psychic Monsters: " + numPsych + "            Number of Pyro Monsters: " + numPyro + "\nNumber of Reptile Monsters: " + numRep + "            Number of Rock Monsters: " + numRock + "\nNumber of Sea-Serpent Monsters: " + numSea + "            Number of Spellcaster Monsters: " + numSpellc + "\nNumber of Thunder Monsters: " + numThun + "          Number of Warrior Monsters: " + numWar + "\nNumber of Winged-Beast Monsters: " + numWing + "            Number of Wyrm Monsters: " + numWyrm + "\nNumber of Zombie Monsters: " + numZom + "\n");
        monsterText.appendText("TOTAL MONSTERS: " + monsterCards.size());
    }
    /**
     * Function definition for getProbabilities()
     * <p>
     *     Gets and outputs the probabilities of drawing a particular card within the deck, for all cards
     *     within the deck
     * </p>
     */
    private void getProbabilities() {
        for(Card c1 : deck.getOnlyDeck()) {
            double count = 0;
            for(Card c2 :deck.getOnlyDeck()) {
                if(c1.getCardName().equals(c2.getCardName())) {
                    count += 1;
                }
            }
            probabilities.appendText(c1.getCardName() + " = " + (count / (double) deck.getOnlyDeck().size()) + "\n");
        }
    }
    /**
     * Function definition for back()
     * <p>
     *     Reverts to the beginningScene
     * </p>
     */
    @FXML
    private void back() {
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getBeginningScene());
    }
    /**
     * COLLECTION FOR MENUBAR
     */
    @FXML
    private void newProfile(ActionEvent event) {
        /*Make a profile using user input*/
        ProgramFunctions.createProfile(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().input("Create Profile...", "Input Name:"));
        /*Update title*/
        ProgramFunctions.getProgramData().getUserInterface().updateTitle();
    }
    @FXML
    private void loadProfile(ActionEvent event) {
        /*Load a profile*/
        ProgramFunctions.makeActive(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().boxSelector(ProgramFunctions.getUtilities().getFileHandler().searchUserFolder(), "Select Profile..."));
        /*Update title*/
        ProgramFunctions.getProgramData().getUserInterface().updateTitle();
    }
    @FXML
    private void exit(ActionEvent event) {
        ProgramFunctions.exit();
    }
    @FXML
    private void about(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("About", "Yu-Gi-Oh! Deck Builder by Samuel John Malpass\nVersion : " + ProgramFunctions.getProgramData().getVersionNumber());
    }
    @FXML
    private void check(ActionEvent event) {
        if (ProgramFunctions.checkVersion()) {
            ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().alert("Check for updates...", "No Update Available.");
        } else {
            if (ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().yesNo("Update available", "Would you like to update now?")) {
                /*Download the update*/
            } else {
            }
        }
    }
    @FXML
    private void settings(ActionEvent event) {
        ProgramFunctions.getProgramData().getUserInterface().updateScene(ProgramFunctions.getProgramData().getUserInterface().getSettingsScene());
    }
}
