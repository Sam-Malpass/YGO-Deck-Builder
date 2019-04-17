/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import dataStructure.UserProfile;
import dataStructure.banList.BanList;
import dataStructure.containerHierarchy.Container;
import dataStructure.containerHierarchy.Deck;
import dataStructure.containerHierarchy.Series;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class FileHandler {
    /**
     * Constructor with no arguments
     */
    FileHandler(){
    }
    /**
     * Function definition for loadUserProfile()
     * <p>
     * Loads the selected file and returns the object in the file
     * <p>
     * @param S is the name of the file
     * @return the profile
     */
    public UserProfile loadUserProfile(String S) {
        /*Open a file*/
        File file = new File("profile/" + S + ".ser");
        /*Create a UserProfile*/
        UserProfile profile = new UserProfile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            /*Attempt to write file to profile*/
            profile = (UserProfile) ois.readObject();
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
        /*Return profile*/
        return profile;
    }
    /**
     * Function definition for saveUserProfile()
     * <p>
     * Write the UserProfile P to a file
     * <p>
     * @param P is the UserProfile to be saved
     */
    public void saveUserProfile(UserProfile P) {
        /*Opens a file*/
        File file = new File("profile/" + P.getProfileName() + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            /*Write object to file*/
            oos.writeObject(P);
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
    }
    /**
     * Function definition for deleteUserProfile()
     * <p>
     * Opens a file of a given name, then deletes the file
     * <p>
     * @param U is the name of the file to delete
     */
    public void deleteUserProfile(String U) {
        /*Opens a file*/
        File file = new File("profile/" + U + ".ser");
        /*Delete the file*/
        file.delete();
        /*Return*/
    }
    /**Function definition for searchUserFolder()
     * <p>
     *     Searches a folder for all items within the folder,
     *     and adds the names of the files to a List to be returned.
     * </p>
     * @return the list of file names
     */
    public ArrayList<String> searchUserFolder() {
        /*Create a List*/
        ArrayList<String> users = new ArrayList<>();
        /*Open the folder*/
        File folder = new File("profile/");
        /*Create a list of all files in the folder*/
        File[] fileList = folder.listFiles();
        if(fileList != null) {
            /*For all files in the list*/
            for (File aFileList : fileList) {
                /*If the item is a file*/
                if (aFileList.isFile()) {
                    /*Add the name to the list*/
                    users.add(aFileList.getName().replace(".ser", ""));
                }
            }
        }
        /*Return users*/
        return users;
    }
    /**
     * Function definition for loadSeries()
     * <p>
     * Loads the selected file and returns the object in the file
     * <p>
     * @param S is the name of the file
     * @return the Series
     */
    public Series loadSeries(String S) {
        /*Open a file*/
        File file = new File("series/" + S + ".ser");
        /*Create a UserProfile*/
        Series series = new Series();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            /*Attempt to write file to profile*/
            series = (Series) ois.readObject();
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
        /*Return profile*/
        return series;
    }
    /**
     * Function definition for saveSeries()
     * <p>
     * Write the SeriesContainer S to a file
     * <p>
     * @param S is the SeriesContainer to be saved
     */
    public void saveSeries(Series S) {
        /*Opens a file*/
        File file = new File("series/" + S.getContainerName() + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            /*Write object to file*/
            oos.writeObject(S);
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
    }
    /**
     * Function definition for deleteSeries()
     * <p>
     * Opens a file of a given name, then deletes the file
     * <p>
     * @param S is the name of the file to delete
     */
    public void deleteSeries(String S) {
        /*Opens a file*/
        File file = new File("series/" + S + ".ser");
        /*Delete the file*/
        file.delete();
        /*Return*/
    }
    /**Function definition for searchSeriesFolder()
     * <p>
     *     Searches a folder for all items within the folder,
     *     and adds the names of the files to a List to be returned.
     * </p>
     * @return the list of file names
     */
    public ArrayList<String> searchSeriesFolder() {
        /*Create a List*/
        ArrayList<String> series = new ArrayList<>();
        /*Open the folder*/
        File folder = new File("series/");
        /*Create a list of all files in the folder*/
        File[] fileList = folder.listFiles();
        if(fileList != null) {
            /*For all files in the list*/
            for (File aFileList : fileList) {
                /*If the item is a file*/
                if (aFileList.isFile()) {
                    /*Add the name to the list*/
                    series.add(aFileList.getName().replace(".ser", ""));
                }
            }
        }
        /*Return users*/
        return series;
    }
    /**
     * Function definition for saveDeck()
     * <p>
     *     Attempts to write a file to a location given by a user
     * </p>
     * @param D is the deck to be saved
     * @param f is the file that is being written
     */
    public void saveDeck(Container D, File f) {
        /*Attempt to write the file*/
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            /*Write object to file*/
            oos.writeObject(D);
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
    }
    /**
     * Function definition for loadDeck()
     * <P>
     *     Makes a deck and then attempts to load a deck from
     *     a file into that object before returning said object;
     * </P>
     * @param f is the file to open
     * @return the deck
     */
    public Deck loadDeck(File f) {
        /*Create a deck*/
        Deck deck = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            /*Attempt to write file to profile*/
            deck = (Deck) ois.readObject();
            /*Exception handling*/
        } catch (Exception ex) {
            /*Print stack trace*/
            ex.printStackTrace();
        }
        /*Return deck*/
        return deck;
    }
    /**
     * Function definition for saveBanList()
     * <p>
     *     Saves a passed BanList object to the banlist file
     * </p>
     * @param banList is the BanList to be saved
     */
    public void saveBanList(BanList banList) {
        /*Open a file*/
        File file = new File("resources/banlist.ser");
        /*Attempt to write*/
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            /*Object to the file*/
            oos.writeObject(banList);
        }
        /*Catch*/
        catch(Exception ex) {
            /*Print the stack trace*/
            ex.printStackTrace();
        }
        /*Return*/
    }
    /**
     * Function definition for loadBanList()
     * <p>
     *     Load the banlist object from a file within the resources
     *     folder
     * </p>
     * @return the banlist
     */
    public BanList loadBanList() {
        /*Open a file*/
        File f = new File("resources/banlist.ser");
        /*Create a BanList*/
        BanList banList = null;
        /*Attempt to read the file*/
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            /*Set banList to the read object*/
            banList = (BanList) ois.readObject();
        }
        /*Catch*/
        catch(Exception ex) {
            /*Print the stack trace*/
            ex.printStackTrace();
        }
        /*Return the banList*/
        return banList;
    }
    /**
     * Function definition for findSetName()
     * <p>
     *     Looks up the full name of a set based of the setID
     * </p>
     * @param setID is the set to lookup
     * @return the full set name
     */
    public String findSetName(String setID) {
        String wholeName = "";
        ArrayList<String> list = new ArrayList<>();
        File file = new File("resources/Series Look-Up Table");
        try (Scanner input = new Scanner(file))
        {
            while (input.hasNextLine()) {
                System.out.print(input.hasNextLine());
                list.add(input.nextLine());
            }
            for(String s : list) {
                if(s.contains(setID)) {
                    wholeName = s.substring(s.indexOf(")")).replace(")", "");
                    return wholeName;
                }
            }
        }
        catch (Exception e) {

        }
        return wholeName;
    }
    /**
     * Function definition for loadArchetypeList()
     * <p>
     *     Loads and returns all the known archetype
     * </p>
     * @return the archetypes
     */
    public ArrayList<String> loadArchetypeList() {
        ArrayList<String> tmp = new ArrayList<>();
        File file = new File("resources/archetypeList");
        try(Scanner input = new Scanner(file)) {
            while(input.hasNextLine()) {
                String tmpI = input.nextLine() + " ";
                tmp.add(tmpI);

            }
        }
        catch (Exception e) {

        }
        return tmp;
    }
    /**
     * Function definition for loadDefaultSpellList()
     * <p>
     *     Loads a list of default spells
     * </p>
     * @return a list of default spells
     */
    public ArrayList<String> loadDefaultSpellList() {
        ArrayList<String> tmp = new ArrayList<>();
        File file = new File("resources/defaultSpells");
        try(Scanner input = new Scanner(file)) {
            while(input.hasNextLine()) {
                tmp.add(input.nextLine());
            }
        }
        catch (Exception e) {

        }
        return tmp;
    }
    /**
     * Function definition for loadDefaultTrapList()
     * <p>
     *     Loads and returns the default trap list
     * </p>
     * @return the default trap list
     */
    public ArrayList<String> loadDefaultTrapList() {
        ArrayList<String> tmp = new ArrayList<>();
        File file = new File("resources/defaultTraps");
        try(Scanner input = new Scanner(file)) {
            while(input.hasNextLine()) {
                tmp.add(input.nextLine());
            }
        }
        catch (Exception e) {

        }
        return tmp;
    }
}
