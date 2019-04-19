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
        cardViewer = new CardViewer();
        results = new ArrayList<>();
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

    public void setResults(ArrayList<SearchResult> results) {
        this.results = results;
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
     * Function definition for viewCard()
     * <p>
     *     Opens a window of the card view
     * </p>
     * @param X is the card to view
     */
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
