/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.cardHierarchy.*;
import dataStructure.cardHierarchy.enumerators.MonsterAttribute;
import dataStructure.cardHierarchy.enumerators.MonsterType;
import dataStructure.cardHierarchy.enumerators.SpellType;
import dataStructure.cardHierarchy.enumerators.TrapType;
import dataStructure.containerHierarchy.Album;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import java.util.ArrayList;
public class Filters {
    /**
     * Constructor with no arguments
     */
    public Filters() {
    }
    /**
     * Function definition for filterSpells()
     * <p>
     *     Filters and returns all spell cards in a list of cards
     * </p>
     * @param cards is the list to be filtered
     * @return all the spells
     */
    public ArrayList<SpellCard> filterSpells(ArrayList<Card> cards) {
        ArrayList<SpellCard> tmp = new ArrayList<>();
        for(Card c : cards) {
            if(c instanceof SpellCard) {
                tmp.add((SpellCard) c);
            }
        }
        return tmp;
    }
    /**
     * Function definition for filterSpellType()
     * <p>
     *     Filters a list of cards into a list of spells, then filters the spells
     *     by type
     * </p>
     * @param cards is the list to be filtered
     * @param type is the type of spell to be filtered
     * @return the filtered list
     */
    public ArrayList<SpellCard> filterSpellType(ArrayList<Card> cards, SpellType type) {
        ArrayList<SpellCard> tmp = filterSpells(cards);
        ArrayList<SpellCard> tmpII = new ArrayList<>();
        for(SpellCard c : tmp) {
            if(c.getSpellType().equals(type)) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterTraps()
     * <p>
     *     Filters a list of cards for Traps and returns the list of Traps
     * </p>
     * @param cards is the list to be filtered
     * @return a list of TrapCards
     */
    public ArrayList<TrapCard> filterTraps(ArrayList<Card> cards) {
        ArrayList<TrapCard> tmp = new ArrayList<>();
        for(Card c : cards) {
            if(c instanceof TrapCard) {
                tmp.add((TrapCard) c);
            }
        }
        return tmp;
    }
    /**
     * Function definition for filterTrapType()
     * <p>
     *     Filters a list of cards for traps, then filters that list by type
     * </p>
     * @param cards is the list to be filtered
     * @param type the type to filter out
     * @return the filtered list
     */
    public ArrayList<TrapCard> filterTrapType(ArrayList<Card> cards, TrapType type) {
        ArrayList<TrapCard> tmp = filterTraps(cards);
        ArrayList<TrapCard> tmpII = new ArrayList<>();
        for(TrapCard c : tmp) {
            if(c.getTrapType().equals(type)) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterMonsters()
     * <p>
     *     Filters a list of cards for monster cards
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterMonsters(ArrayList<Card> cards) {
        ArrayList<MonsterCard> tmp = new ArrayList<>();
        for(Card c : cards) {
            if(c instanceof MonsterCard) {
                tmp.add((MonsterCard) c);
            }
        }
        return tmp;
    }
    /**
     * Function definition for filterEffects()
     * <p>
     *
     * </p>
     * @param cards
     * @return
     */
    public ArrayList<EffectMonster> filterEffects(ArrayList<Card> cards) {
        ArrayList<MonsterCard> tmp = filterMonsters(cards);
        ArrayList<EffectMonster> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c instanceof EffectMonster) {
                tmpII.add((EffectMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterRituals()
     * <p>
     *     Filters a list of cards into EffectMonsters then filters out the Ritual monsters
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<RitualMonster> filterRituals(ArrayList<Card> cards) {
        ArrayList<EffectMonster> tmp = filterEffects(cards);
        ArrayList<RitualMonster> tmpII = new ArrayList<>();
        for(EffectMonster c : tmp) {
            if(c instanceof RitualMonster) {
                tmpII.add((RitualMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterPendulums()
     * <p>
     *     Filters a list of cards by EffectMonsters and then filters that list into PendulumMonsters
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<PendulumMonster> filterPendulums(ArrayList<Card> cards) {
        ArrayList<EffectMonster> tmp = filterEffects(cards);
        ArrayList<PendulumMonster> tmpII = new ArrayList<>();
        for(EffectMonster c : tmp) {
            if(c instanceof PendulumMonster) {
                tmpII.add((PendulumMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterFusions()
     * <p>
     *     Filters a list of cards by EffectMonster and then filters that list
     *     by FusionMonsters
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<FusionMonster> filterFusions(ArrayList<Card> cards) {
        ArrayList<EffectMonster> tmp = filterEffects(cards);
        ArrayList<FusionMonster> tmpII = new ArrayList<>();
        for(EffectMonster c : tmp) {
            if(c instanceof FusionMonster) {
                tmpII.add((FusionMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterSynchros()
     * <p>
     *     Filters a list of cards by EffectMonsters then filters that list by SynchroMonsters
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<SynchroMonster> filterSynchros(ArrayList<Card> cards) {
        ArrayList<EffectMonster> tmp = filterEffects(cards);
        ArrayList<SynchroMonster> tmpII = new ArrayList<>();
        for(EffectMonster c : tmp) {
            if(c instanceof SynchroMonster) {
                tmpII.add((SynchroMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterXYZs()
     * <P>
     *     Filters a list of cards by EffectMonster, then filters that list by
     *     XYZMonster
     * </P>
     * @param cards is the list to be filtered
     * @return is the filtered list
     */
    public ArrayList<XYZMonster> filterXYZs(ArrayList<Card> cards) {
        ArrayList<EffectMonster> tmp = filterEffects(cards);
        ArrayList<XYZMonster> tmpII = new ArrayList<>();
        for(EffectMonster c : tmp) {
            if(c instanceof XYZMonster) {
                tmpII.add((XYZMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterLinks()
     * <p>
     *     Filters a list of cards by EffectMonster, then filters that list by
     *     LinkMonster
     * </p>
     * @param cards is the list to be filtered
     * @return the filtered list
     */
    public ArrayList<LinkMonster> filterLinks(ArrayList<Card> cards) {
        ArrayList<LinkMonster> tmpII = new ArrayList<>();
        for(Card c : cards) {
            if(c instanceof LinkMonster) {
                tmpII.add((LinkMonster) c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterMonsterType()
     * <p>
     *     Filters a list of cards for MonsterCard then filters that list
     *     based on passed type
     * </p>
     * @param cards is the list of cards to be filtered
     * @param type is the type of monster to be filtered
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterMonsterType(ArrayList<MonsterCard> cards, MonsterType type) {
        ArrayList<MonsterCard> tmp = cards;
        ArrayList<MonsterCard> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c.getMonsterType().equals(type)) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterMonsterAttribute()
     * <P>
     *     Filters a list of cards by MonsterCard then filters that list
     *     by passed attribute
     * </P>
     * @param cards is the list to be filtered
     * @param att is the attribute to be filterd
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterMonsterAttribute(ArrayList<MonsterCard> cards, MonsterAttribute att) {
        ArrayList<MonsterCard> tmp = cards;
        ArrayList<MonsterCard> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c.getMonsterAttribute().equals(att)) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterMonsterLevel()
     * <p>
     *     Filters a list of cards by MonsterCard then filter that list by
     *     passed level
     * </p>
     * @param cards is the list to be filtered
     * @param level is the level to filter by
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterMonsterLevel(ArrayList<MonsterCard> cards, int level) {
        ArrayList<MonsterCard> tmp = cards;
        ArrayList<MonsterCard> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c.getLevel() == level) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterAttackPoints()
     * <p>
     *     Filters a list of cards by MonsterCard then filters that list
     *     by a passed attack point value
     * </p>
     * @param cards is the list to be filtered
     * @param atk is the attack points to filter by
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterAttackPoints(ArrayList<Card> cards, int atk) {
        ArrayList<MonsterCard> tmp = filterMonsters(cards);
        ArrayList<MonsterCard> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c.getAttackPoints() == atk) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterDefencePoints()
     * <P>
     *     Filters a list of cards for MonsterCard then filters that list for
     *     passed defence points
     * </P>
     * @param cards is the list to be filtered
     * @param def is the defence points to filter by
     * @return the filtered list
     */
    public ArrayList<MonsterCard> filterDefencePoints(ArrayList<Card> cards, int def) {
        ArrayList<MonsterCard> tmp = filterMonsters(cards);
        ArrayList<MonsterCard> tmpII = new ArrayList<>();
        for(MonsterCard c : tmp) {
            if(c.getDefencePoints() == def) {
                tmpII.add(c);
            }
        }
        return tmpII;
    }
    /**
     * Function definition for filterSpellArchetype()
     * <p>
     *     Returns a list of SpellCards of a given Archetype
     * </p>
     * @param cards the list to be filtered
     * @param archetype is the archetype to filter
     * @return the list of spell cards
     */
    public ArrayList<SpellCard> filterSpellArchetype(ArrayList<SpellCard> cards, String archetype) {
        ArrayList<SpellCard> filtered = new ArrayList<>();
        for(SpellCard c : cards) {
            if(c.getCardDescription().contains(archetype)) {
                filtered.add(c);
            }
        }
        return filtered;
    }
    /**
     * Function definition for filterTrapArchetype()
     * <p>
     *     Returns a list of TrapCards of a given Archetype from a list of cards
     * </p>
     * @param cards is the list to be filtered
     * @param archetype is the archetype of to be found
     * @return the list of filtered traps
     */
    public ArrayList<TrapCard> filterTrapArchetype(ArrayList<TrapCard> cards, String archetype) {
        ArrayList<TrapCard> filtered = new ArrayList<>();
        for(TrapCard c : cards) {
            if(c.getCardDescription().contains(archetype)) {
                filtered.add(c);
            }
        }
        return filtered;
    }
    /**
     * Function definition for filterMonsterArchetype()
     * <p>
     *     Returns a list of MonsterCards of a given Archetype from a list of cards
     * </p>
     * @param cards is the list to be filterd
     * @param archetype is the archetype to find
     * @return the list of filtered cards
     */
    public ArrayList<MonsterCard> filterMonsterArchetype(ArrayList<MonsterCard> cards, String archetype) {
        ArrayList<MonsterCard> filtered = new ArrayList<>();
        for(MonsterCard c : cards) {
            if(c instanceof EffectMonster || c instanceof FusionMonster || c instanceof SynchroMonster || c instanceof  XYZMonster || c instanceof PendulumMonster) {
                if (c.getCardName().contains(archetype) || c.getCardName().contains(archetype)) {
                    filtered.add(c);
                }
            }
            else {
                if (c.getCardName().contains(archetype)) {
                    filtered.add(c);
                }
            }
        }
        return filtered;
    }
    /**
     * Function definition for filterAlbums()
     * <p>
     *     Filters out albums from a list of containers
     * </p>
     * @param containers is the list to filter
     * @return the albums only
     */
    public  ArrayList<Album> filterAlbums(ArrayList<Container> containers) {
        ArrayList<Album> albums = new ArrayList<>();
        for(Container c : containers) {
            if(c instanceof Album) {
                albums.add((Album) c);
            }
        }
        return albums;
    }
    /**
     * Function definition for filterDecks()
     * <p>
     *     Filters the decks out of a list of containers
     * </p>
     * @param containers is the list to filtered
     * @return the decks only
     */
    public  ArrayList<Deck> filterDecks(ArrayList<Container> containers) {
        ArrayList<Deck> decks = new ArrayList<>();
        for(Container c : containers) {
            if(c instanceof Deck) {
                decks.add((Deck) c);
            }
        }
        return decks;
    }
}
