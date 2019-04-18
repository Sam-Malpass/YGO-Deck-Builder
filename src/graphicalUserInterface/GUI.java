/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface;
import dataStructure.cardHierarchy.Card;
import graphicalUserInterface.basicWindows.CardViewer;
import graphicalUserInterface.basicWindows.Windows;
import graphicalUserInterface.sceneHandling.SceneData;
import graphicalUserInterface.sceneHandling.Scenes;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;
import programFunctions.searching.SearchResult;
import java.io.File;
import java.util.ArrayList;
public class GUI {
    /**
     * windowHeight holds the window height
     */
    private double windowHeight = 720;
    /**
     * windowWidth holds the window width
     */
    private double windowWidth = 1280;
    /**
     * mainStage is the base window
     */
    private Stage mainStage;
    /**
     * basicWindows holds a BasicWindows object
     */
    private Windows basicWindows;

    private CardViewer cardViewer;
    /**
     * scenes holds all the functions to make Scenes
     */
    private Scenes scenes;
    /**
     * results holds a list of searchResults
     */
    private ArrayList<SearchResult> results;
    /**
     * Constructor with no arguments
     * <p>
     * Sets the icon for the GUI
     * </p>
     */
    public GUI() {
        basicWindows = new Windows();
        scenes = new Scenes();
    }
    /**
     * Function definition for getDefaultScene()
     * <p>
     * Return the defaultScene function result from in scenes
     * </p>
     *
     * @return the appropriate scene
     */
    public Scene getDefaultScene() {
        /*Return the Scene from defaultScene*/
        return scenes.defaultScene();
    }
    /**
     * Function definition for getBeginningScene()
     * <p>
     * Return the beginningScene function result from in scenes
     * </p>
     *
     * @return the appropriate scene
     */
    public Scene getBeginningScene() {
        /*Return the Scene from beginningScene*/
        return scenes.beginningScene();
    }
    /**
     * Function definition getChecklistScene()
     * <p>
     * Return the checklistScene function result from in scenes
     * </p>
     *
     * @param checklist is the list to be used in the window
     * @return the appropriate scene
     */
    public Scene getChecklistScene(ArrayList<String> checklist) {
        accessSceneCache().setMissing(checklist);
        /*Return the Scene from checklistScene*/
        return scenes.checklistScene();
    }
    /**
     * Function definition for getDeckImport()
     * <p>
     * Return the deckImportView function result from in scenes
     * </p>
     *
     * @param available is the list of available cards
     * @param missing   is the list of missing cards
     * @return the appropriate scene
     */
    public Scene getDeckImport(ArrayList<String> available, ArrayList<String> missing) {
        accessSceneCache().setAvailable(available);
        accessSceneCache().setMissing(missing);
        /*Return the Scene from deckImportView*/
        return scenes.deckImportView();
    }
    /**
     * Function definition for getDeckBuilder()
     * <p>
     * Returns the deckBuilder result from in scenes
     * </p>
     * @return the scene
     */
    public Scene getDeckBuilder() {
        /*Return the Scene from deckBuilder*/
        return scenes.deckBuidler();
    }
    /**
     * Function definition for getAlbumBuilder()
     * <p>
     *     Returns the albumBuilder Scene
     * </p>
     * @return albumBuilder
     */
    public Scene getAlbumBuilder() {
        return scenes.albumBuilder();
    }
    /**
     * Function definition for getProfSetScene()
     * <p>
     *     Return the profileSettingScene
     * </p>
     * @return profileSettingScene
     */
    public Scene getProfSetScene() {
        return scenes.profileSettingScene();
    }
    /**
     * Function definition for getForbiddenScene()
     * <p>
     *     Return the forbiddenScene Scene
     * </p>
     * @return forbiddenScene
     */
    public Scene getForbiddenScene(){
        return scenes.forbiddenScene();
    }
    /**
     * Function definition for getAlbumAnalyzer()
     * <p>
     *     Return the albumAnalysis Scene
     * </p>
     * @return albumAnalysis
     */
    public Scene getAlbumAnalyzer() {
        return scenes.albumAnalysis();
    }
    /**
     * Function definition for getViewSetCollected()
     * <p>
     *     Return the viewSetCollected Scene
     * </p>
     * @return viewSetCollected
     */
    public Scene getViewSetCollected() {
        return scenes.viewSetCollected();
    }
    /**
     * Function definition for getDeckAnalyzerScene()
     * <p>
     *     Return deckAnalyzerScene Scene
     * </p>
     * @return deckAnalyzerScene
     */
    public Scene getDeckAnalyzerScene() {
        return scenes.deckAnalyzerScene();
    }
    /**
     * Function definition for getSettingsScene()
     * <p>
     *     Return settings Scene
     * </p>
     * @return settings
     */
    public Scene getSettingsScene() {
        return scenes.settings();
    }
    /**
     * Function definition for getStage()
     * <p>
     *     Return the mainStage
     * </p>
     * @return mainStage
     */
    public Stage getStage() {
        return mainStage;
    }
    /**
     * Function definition for getBasicWindows()
     * <p>
     *     Return basicWindows
     * </p>
     * @return basicWindows
     */
    public Windows getBasicWindows() {
        return basicWindows;
    }
    /**
     * Function definition for getResults()
     * <p>
     *     Returns the results
     * </p>
     * @return results
     */
    public ArrayList<SearchResult> getResults() {
        return results;
    }
    /**
     * Function definition for setVerboseEnabled()
     * <p>
     *     Sets the verbose flag in the basicWindows to the passed value
     * </p>
     * @param flag is the value to use
     */
    public void setVerboseEnabled(boolean flag) {
        basicWindows.setVerboseFlag(flag);
    }
    /**
     * Function definition for updateTitle()
     * <p>
     * Updates the title of the windowS
     * </p>
     */
    public void updateTitle() {
        /*If there is no profile active*/
        if (ProgramFunctions.getProgramData().getCurrentProfile() == null) {
            /*Set the title*/
            mainStage.setTitle("Yu-Gi-Oh! Deck Builder : ");
            /*Return*/
            return;
        }
        /*Set the title*/
        mainStage.setTitle("Yu-Gi-Oh! Deck Builder : " + ProgramFunctions.getProgramData().getCurrentProfile().getProfileName());
    }
    /**
     * Function definition for updateScene()
     * <p>
     * Updates the scene in the window
     * </p>
     *
     * @param scene is the scene to use
     */
    public void updateScene(Scene scene) {
        /*Update the window*/
        mainStage.setScene(scene);
    }
    /**
     * Function definition for yesNoWindow()
     * <p>
     * Calls the yesNo function in the basicWindows
     * </p>
     *
     * @param title   is the title for the window
     * @param content is the label for the window
     * @return the result of said window
     */
    public boolean yesNoWindow(String title, String content) {
        /*The result of the window*/
        return basicWindows.yesNo(title, content);
    }
    /**
     * Function definition for boxSelectorWindow()
     * <p>
     * Calls the boxSelector function in the basicWindows
     * </P>
     *
     * @param content is the ArrayList to use for the drop down list
     * @param title   is the title of the window
     * @return the selected item
     */
    public String boxSelectorWindow(ArrayList<String> content, String title) {
        /*Return the selected item*/
        return basicWindows.boxSelector(content, title);
    }
    /**
     * Function definition for inputWindow()
     * <p>
     * Calls the input function in the basicWindows
     * </p>
     *
     * @param title   is the title for the window
     * @param content is the information for the window
     * @return the result
     */
    public String inputWindow(String title, String content) {
        /*Return the input from the window*/
        return basicWindows.input(title, content);
    }
    /**
     * Function definition for resultsWindow()
     * <p>
     * Calls the searchResult function in the basicWindows
     * </p>
     *
     * @param results is a list of SearchResult objects
     */
    public void resultsWindow(ArrayList<SearchResult> results) {
        this.results = results;
        /*Create a window to show the results*/
        basicWindows.searchResult();
    }
    /**
     * Function definition for exportWindow()
     * <p>
     * Calls the exportDialog function in the basicWindows
     * </p>
     *
     * @return the File
     */
    public File exportWindow() {
        /*Return the chosen file*/
        return basicWindows.exportDialog();
    }
    /**
     * Function definition for importWindow()
     * <p>
     * Calls the importDialog function in the basicWindows
     * </p>
     *
     * @return the File
     */
    public File importWindow() {
        /*Return the chosen file*/
        return basicWindows.importDialog();
    }
    /**
     * Function definition for passwordWindow()
     * <p>
     *     Gets the basicWindows object to create a passwordInput window with the provided title
     *     and returns the result string
     * </p>
     * @param title is the title for the window
     * @return the result string
     */
    public String passwordWindow(String title) {
        return basicWindows.passwordInput(title);
    }
    /**
     * Function definition for alertWindow()
     * <p>
     * Calls the alert function in basicWindows
     * </p>
     *
     * @param title   is the title of the window
     * @param content is the message for the window
     */
    public void alertWindow(String title, String content) {
        /*Make an alert window*/
        basicWindows.alert(title, content);
    }
    /**
     * Function definition for systemResults()
     * <p>
     *     Opens a window for systemResults
     * </p>
     * @param results to show
     * @return the selected card
     */
    public Card systemResults(ArrayList<SearchResult> results) {
        return basicWindows.systemResults(results);
    }
    /**
     * Function definition for deckBuilderResult()
     * <p>
     *     Show the deckBuilderResult window
     * </p>
     * @param results to show
     * @return the list of strings
     */
    public ArrayList<String> deckBuilderResult(ArrayList<SearchResult> results) {
        return basicWindows.searchResultDeckBuilder(results);
    }

    public void viewCard(Card X) {
        cardViewer.determineCardType(X);
    }
    /**
     * Function definition for accessSceneCache()
     * <p>
     *     Returns the dataCache in the scenes object
     * </p>
     * @return the scene dataCache
     */
    public SceneData accessSceneCache() {
        return scenes.getDataCache();
    }
    /**
     * Function definition for run()
     * <p>
     * Opens the base window for user GUI.
     * </p>
     *
     * @param S is the stage to use
     */
    public void run(Stage S) {
        /*Set mainStage to S*/
        mainStage = S;
        /*Set the title*/
        mainStage.setTitle("Yu-Gi-Oh! Deck Builder :");
        /*Add an icon*/
        mainStage.getIcons().add(ProgramFunctions.getProgramData().getIcon());
        /*Add the Scene to the mainStage*/
        mainStage.setScene(getDefaultScene());
        /*Make mainStage impossible to resize*/
        mainStage.setMinWidth(windowWidth);
        mainStage.setMinHeight(windowHeight);
        mainStage.setResizable(false);
        /*Show the window*/
        mainStage.show();
    }
}
