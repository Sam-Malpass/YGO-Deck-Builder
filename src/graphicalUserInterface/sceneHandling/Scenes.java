/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.sceneHandling;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class Scenes {
    /**
     * dataCache holds all scene related data
     */
    private SceneData dataCache = new SceneData();
    /**
     * width holds the window width
     */
    private double width = 1280;
    /**
     * height holds the window height
     */
    private double height = 720;
    /**
     * Scene holds the current scene
     */
    private Scene scene;
    /**
     * root holds the FXML loaded resource
     */
    private Parent root;
    /**
     * Function definition for getDataCache()
     * <p>
     *     Returns the dataCache object which could be useful for classes that utilise this class
     * </p>
     * @return
     */
    public SceneData getDataCache() {
        return dataCache;
    }
    /**
     * Function definition for beginningScene()
     * <p>
     * Creates a scene to show users an overview of their profile
     * </p>
     *
     * @return a scene
     */
    public Scene beginningScene() {
        double width = scene.getWidth();
        double height = scene.getHeight();
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Beginning Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for deckImportView()
     * <p>
     * Creates the scene for initial stage of deck importation,
     * displaying available cards, missing cards and offering a few options.
     * </P>
     * @return a scene
     */
    public Scene deckImportView() {
        double width = scene.getWidth();
        double height = scene.getHeight();
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Import Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for checklistScene()
     * <p>
     * Create a scene to show the checklist variable
     * </p>
     *
     * @return the scene
     */
    public Scene checklistScene() {
        dataCache.setChecklist(ProgramFunctions.checklistChecker());
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Checklist Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for forbiddenScene()
     * <p>
     * Create a Scene using the passed banList
     * </p>
     * @return the Scene
     */
    public Scene forbiddenScene() {
        dataCache.setBanList(ProgramFunctions.outputBanList());
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Ban List Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for deckAnalyzerScene()
     * <p>
     *      Create a Scene
     * </p>
     * @return the Scene
     */
    public Scene deckAnalyzerScene() {
        dataCache.setDeck(BeginningController.deck);
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Deck Analysis Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for profileSettingScene()
     * <p>
     *     Creates a Scene and then adds all the elements to it, allowing the
     *     user to modify their profile's settings
     * </p>
     * @return the scene
     */
    public Scene profileSettingScene() {
        if(dataCache.getTmpProfSettings() == null) {
            dataCache.tmpProfSettings = ProgramFunctions.getProgramData().getCurrentProfile().getProfileSettings();
        }
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Profile Settings.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for defaultScene()
     * <p>
     * Makes the defaultStartup scene
     * </p>
     *
     * @return the scene
     */
    public Scene defaultScene() {
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Default Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for deckBuilder()
     * <p>
     *     Creates a Scene and adds elements to it that allow for the the user to
     *     build decks
     * </p>
     * @return the scene
     */
    public Scene deckBuidler() {
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Deck Building Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for albumBuilder()
     * <p>
     *      Create a Scene
     * </p>
     * @return the Scene
     */
    public Scene albumBuilder() {
        dataCache.setAlbum(BeginningController.album);
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Album Builder Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for albumAnalysis()
     * <p>
     *      Create a Scene
     * </p>
     * @return the Scene
     */
    public Scene albumAnalysis() {
        dataCache.setAlbum(BeginningController.album);
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Album Analysis Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for viewSetCollected()
     * <p>
     *     Creates a Scene and adds teh elements to it to allow the user
     *     to see which cards in a given set are collected and which are missing
     * </p>
     * @return the scene
     */
    public Scene viewSetCollected() {
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Set Collected Scene.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
    /**
     * Function definition for settings()
     * <p>
     *      Create a Scene
     * </p>
     * @return the Scene
     */
    public Scene settings() {
        scene = null;
        try {
            root = FXMLLoader.load(getClass().getResource("controllersFXML/FXML/Global Settings.fxml"));
            scene = new Scene(root, width, height);
        }
        catch (Exception e) {

        }
        return scene;
    }
}
