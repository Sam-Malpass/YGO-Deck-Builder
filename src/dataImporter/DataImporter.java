/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package dataImporter;
import dataStructure.cardHierarchy.*;
import dataStructure.cardHierarchy.enumerators.*;
import dataStructure.containerHierarchy.Series;
import neuralNetwork.mlp.NeuralNetwork;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class DataImporter {
    /**
     * name holds the name of the card to be created
     */
    private String name;
    /**
     * type holds the MonsterType (if there is one)
     */
    private MonsterType type;
    /**
     * attribute holds the MonsterAttribute (if there is one)
     */
    private MonsterAttribute attribute;
    /**
     * spellType holds the SpellType (if there is one)
     */
    private SpellType spellType;
    /**
     * trapType holds the TrapType (if there is one)
     */
    private TrapType trapType;
    /**
     * materials holds the information about summoning materials
     */
    private String materials;
    /**
     * description holds an effect/description
     */
    private String description;
    /**
     * atk holds the Attack Points (if they are present)
     */
    private int atk;
    /**
     * def holds the Defence Points (if they are present)
     */
    private int def;
    /**
     * level holds the Level of the card (if one is present)
     */
    private int level;
    /**
     * setID holds the Sets Identifier
     */
    private String setID;
    /**
     * subTypes holds the list of possible sub-types the card has
     */
    private ArrayList<MonsterSubType> subTypes;
    /**
     * Constructor with no arguments
     * <p>
     *     Simply makes the object
     * </p>
     */
    public DataImporter() {
    }
    /**
     * Function definition for importSeries()
     * <p>
     *     Reads data from the Python data scraper's output and converts it to Card
     *     and Series object(s)
     * </p>
     * @param filename is the name of the file to use
     * @return the Series
     */
    public Series importSeries(String filename) {
        NeuralNetwork net = NeuralNetwork.prep(6);
        /*Create a Series*/
        Series series = new Series(filename);
        /*Create an empty ArrayList*/
        ArrayList<String> list = new ArrayList<>();
        /*Create a String*/
        String tmp;
        /*Create a String for the file path*/
        String filepath = "Python Scripts/Data/"+filename+".txt";
        /*Open the file*/
        File f = new File(filepath);
        /*Set the setID to the filename*/
        setID = filename;
        /*Try to read in the file line by line*/
        try (Scanner input = new Scanner(f))
        {
            while (input.hasNextLine()) {
                list.add(input.nextLine());
            }
        }
        catch (Exception e) {
        }
        /*For all lines in the list*/
        for(int i = 0; i < list.size(); i++) {
            /*Set the name*/
            name = list.get(i);
            i++;
            /*Examine next line*/
            switch(list.get(i).toLowerCase()) {
                /*If a fire attribute*/
                case "fire":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.FIRE;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If it should be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Check the type and sort create the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If an earth attribute*/
                case "earth":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.EARTH;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Check the type and create the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a wind attribute*/
                case "wind":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.WIND;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Check the type and then create the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a water attribute*/
                case "water":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.WATER;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Checks the type and creates the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a dark attribute*/
                case "dark":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.DARK;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Check the type and create the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a light attribute*/
                case "light":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.LIGHT;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Checks the type and then creates the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a divine attribute*/
                case "divine":
                    /*Set the attribute*/
                    attribute = MonsterAttribute.DIVINE;
                    i++;
                    /*Set the level*/
                    level = Integer.parseInt(list.get(i).replace("Level ", ""));
                    i++;
                    /*Set the type*/
                    type = typeDeterminator(list.get(i));
                    i++;
                    /*Create a temporary copy*/
                    tmp = list.get(i);
                    /*If needs to be ignored for now*/
                    if(tmp.toLowerCase().equals("flip") || tmp.toLowerCase().equals("toon") || tmp.toLowerCase().equals("gemini") || tmp.toLowerCase().equals("tuner")) {
                        i++;
                        tmp = list.get(i);
                    }
                    i++;
                    /*Set the attack*/
                    atk = Integer.parseInt(list.get(i).replace("ATK ", ""));
                    i++;
                    /*Set the defence*/
                    def = Integer.parseInt(list.get(i).replace("DEF ", ""));
                    /*Checks the type and then creates the corresponding card*/
                    if(tmp.toLowerCase().contains("normal")) {
                        i++;
                        MonsterCard C = createNormal(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("fusion")) {
                        i++;
                        FusionMonster C = createFusion(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("effect")) {
                        i++;
                        EffectMonster C = createEffect(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("synchro")){
                        i++;
                        SynchroMonster C =createSynchro(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("xyz")) {
                        i++;
                        XYZMonster C = createXYZ(list.get(i));
                        series.addCard(C);
                    }
                    else if(tmp.toLowerCase().contains("link")) {
                        i++;
                        LinkMonster C = createLink(list.get(i));
                        series.addCard(C);
                    }
                    break;
                /*If a spell attribute*/
                case "spell":
                    i++;
                    /*Check for spell type and create card*/
                    switch(list.get(i).toLowerCase()) {
                        case "field":
                            spellType = SpellType.FIELD;
                            i++;
                            description = list.get(i);
                            break;
                        case "quick-play":
                            spellType = SpellType.QUICK_PLAY;
                            i++;
                            description = list.get(i);
                            break;
                        case "ritual":
                            spellType = SpellType.RITUAL;
                            i++;
                            description = list.get(i);
                            break;
                        case "continuous":
                            spellType = SpellType.CONTINUOUS;
                            i++;
                            description = list.get(i);
                            break;
                        case "equip":
                            spellType = SpellType.EQUIP;
                            i++;
                            description = list.get(i);
                            break;
                        default:
                            spellType = SpellType.NORMAL;
                            description = list.get(i);
                            break;
                    }
                    SpellCard spellCard = new SpellCard(name, description, spellType, setID);
                    series.addCard(spellCard);
                    break;
                /*If a trap attribute*/
                case "trap":
                    i++;
                    /*Check for the trap type and create card*/
                    switch (list.get(i).toLowerCase()) {
                        case "continuous":
                            trapType = TrapType.CONTINUOUS;
                            i++;
                            description = list.get(i);
                            break;
                        case "counter":
                            trapType = TrapType.COUNTER;
                            i++;
                            description = list.get(i);
                            break;
                        default:
                            trapType = TrapType.NORMAL;
                            description = list.get(i);
                            break;
                    }
                    TrapCard trapCard = new TrapCard(name, description, trapType, setID);
                    series.addCard(trapCard);
                    break;
            }
        }
        for(Card C : series.getCards()) {
            if(C instanceof EffectMonster || C instanceof SpellCard || C instanceof TrapCard) {
                C.setEffValue(PythonInterfacer.classifyEffect(C.getCardDescription()));
                System.out.println("HERE");
            }
            else {
                C.setEffValue(0);
            }
            if(!(C instanceof FusionMonster) && !(C instanceof SynchroMonster) && !(C instanceof XYZMonster) && !(C instanceof LinkMonster) && !(C instanceof SpellCard) && !(C instanceof TrapCard)) {
                C = (MonsterCard) C;
                ArrayList<Double> inputs = new ArrayList<>();
                inputs.add(((double)((MonsterCard) C).getAttackPoints()/1000));
                inputs.add(((double)((MonsterCard) C).getDefencePoints()/1000));
                inputs.add((double)((MonsterCard) C).getLevel());
                inputs.add((double) C.getEffValue());
                if(((MonsterCard) C).getSubTypes().contains(MonsterSubType.TUNER)) {
                    inputs.add(1.0);
                }
                else {
                    inputs.add(0.0);
                }
                if(C instanceof PendulumMonster) {
                    inputs.add(1.0);
                }
                else {
                    inputs.add(0.0);
                }
                net.setInput(inputs);
                net.activate();
                C.setValue(net.getOutput().get(0));
                System.out.println("[SYSTEM] Card value set to: " + C.getValue());
            }
        }
        /*Return the series*/
        return series;
    }
    /**
     * Function definition for typeDeterminatior()
     * <p>
     *     Converts an input string into a MonsterType
     * </p>
     * @param input is the string to be converted
     * @return the MonsterType
     */
    private MonsterType typeDeterminator(String input) {
        if(input.toLowerCase().contains("aqua")) {
            return MonsterType.AQUA;
        }
        else if(input.toLowerCase().contains("beast")) {
            return MonsterType.BEAST;
        }
        else if(input.toLowerCase().contains("beast-warrior")) {
            return MonsterType.BEAST_WARRIOR;
        }
        else if(input.toLowerCase().contains("dinosaur")) {
            return MonsterType.DINOSAUR;
        }
        else if(input.toLowerCase().contains("divine-beast")) {
            return MonsterType.DIVINE_BEAST;
        }
        else if(input.toLowerCase().contains("dragon")) {
            return MonsterType.DRAGON;
        }
        else if(input.toLowerCase().contains("fairy")) {
            return MonsterType.FAIRY;
        }
        else if(input.toLowerCase().contains("fiend")) {
            return MonsterType.FIEND;
        }
        else if(input.toLowerCase().contains("fish")) {
            return  MonsterType.FISH;
        }
        else if(input.toLowerCase().contains("insect")) {
            return MonsterType.INSECT;
        }
        else if(input.toLowerCase().contains("machine")) {
            return MonsterType.MACHINE;
        }
        else if(input.toLowerCase().contains("plant")) {
            return MonsterType.PLANT;
        }
        else if(input.toLowerCase().contains("psychic")) {
            return MonsterType.PSYCHIC;
        }
        else if(input.toLowerCase().contains("pyro")) {
            return MonsterType.PYRO;
        }
        else if(input.toLowerCase().contains("reptile")) {
            return MonsterType.REPTILE;
        }
        else if(input.toLowerCase().contains("rock")) {
            return MonsterType.ROCK;
        }
        else if(input.toLowerCase().contains("sea serpent")) {
            return MonsterType.SEA_SERPENT;
        }
        else if(input.toLowerCase().contains("spellcaster")) {
            return MonsterType.SPELLCASTER;
        }
        else if(input.toLowerCase().contains("thunder")) {
            return MonsterType.THUNDER;
        }
        else if(input.toLowerCase().contains("warrior")) {
            return MonsterType.WARRIOR;
        }
        else if(input.toLowerCase().contains("winged-beast")) {
            return MonsterType.WINGED_BEAST;
        }
        else if(input.toLowerCase().contains("wyrm")) {
            return MonsterType.WYRM;
        }
        else {
            return MonsterType.ZOMBIE;
        }
    }
    /**
     * Function definition for createMonster()
     * <p>
     *     Sets up the description then creates the monster
     * </p>
     * @param thing is the description text
     * @return the created card
     */
    private MonsterCard createNormal(String thing) {
        description = thing;
        return new MonsterCard(name, description, atk, def, level, attribute, type, setID);
    }
    /**
     * Function definition for createFusion()
     * <p>
     *     Sets up the materials and then adds the description
     * </p>
     * @param input is the string to sort out
     * @return the created card
     */
    private FusionMonster createFusion(String input) {
        /*Splits the string by "+"*/
        ArrayList<String> l = new ArrayList<>(Arrays.asList(input.split(" \\+ ")));
        /*Create an ArrayList for the materials*/
        ArrayList<String> materials = new ArrayList<>();
        /*Set up another empty ArrayList*/
        ArrayList<String> tmp = new ArrayList<>();
        /*For the strings in the split ArrayList*/
        for(int i = 0; i < l.size(); i++) {
            /*If i is the size of the split list*/
            if(i == l.size()) {
                /*Set the empty list to the split of this last element of the split list*/
                tmp = new ArrayList<>(Arrays.asList(l.get(i).split("\"")));
                /*Add the first element to the materials*/
                materials.add(tmp.get(0));
                break;
            }
            /*Add the element to the materials list*/
            materials.add(l.get(i).replace("\"", ""));
        }
        /*Set the description*/
        if(tmp.size() < 1) {
            description = "None.";
        }
        else {
            description = tmp.get(1);
        }
        return new FusionMonster(name, description, atk, def, level, attribute, type, setID, materials);
    }
    /**
     * Function definition for createEffect()
     * <p>
     *     Processes a String into the description then returns a created card
     * </p>
     * @param effect is the string to process
     * @return the created card
     */
    private EffectMonster createEffect(String effect) {
        /*Set the description*/
        description = effect;
        /*Return the created card*/
        return new EffectMonster(name, effect, atk, def, level, attribute, type, setID);
    }

    //CAN'T DO ANYTHING PAST HERE YET

    /**
     * Function definition for createSynchro()
     * <p>
     *     Creates a synchro monster using the passed string
     * </p>
     * @param input is the string to be processed
     * @return the created card
     */
    private SynchroMonster createSynchro(String input) {
        /*Return the created card*/
        return new SynchroMonster();
    }
    /**
     * Function definition for createXYZ()
     * <p>
     *     Creates the XYZMonster using the passed string
     * </p>
     * @param input is the string to process
     * @return the created card
     */
    private XYZMonster createXYZ(String input) {
        /*Return the created card*/
        return new XYZMonster();
    }
    /**
     * Function definition for createLink()
     * <p>
     *     Creates a LinkMonster using the passed String
     * </p>
     * @param input is the string to be processed
     * @return the created monster
     */
    public LinkMonster createLink(String input) {
        /*Return the created card*/
        return new LinkMonster();
    }
}
