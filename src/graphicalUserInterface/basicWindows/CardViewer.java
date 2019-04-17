package graphicalUserInterface.basicWindows;
import dataStructure.cardHierarchy.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class CardViewer {
    /**
     * Function definition for monsterCardView()
     * <p>
     * Opens a window displaying the details of a given monster card
     * </p>
     *
     * @param X is the monster card to view
     */
    public static void monsterCardView(MonsterCard X) {
        /*Create a Stage*/
        Stage monsterCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(74);
        /*Set percentage width*/
        col1.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(74);
        /*Set percentage width*/
        col2.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(74);
        /*Set percentage width*/
        col3.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(74);
        /*Set percentage width*/
        col4.setPercentWidth(25);
        /*Add all column constraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(43);
        /*Add all row constraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Make it un-editable*/
        name.setEditable(false);
        /*Set the size*/
        name.setPrefSize(200, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Make it un-editable*/
        attribute.setEditable(false);
        /*Set the size*/
        attribute.setPrefSize(30, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + Integer.toString(X.getLevel()));
        /*Make it un-editable*/
        level.setEditable(false);
        /*Set the size*/
        level.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Make it un-editable*/
        type.setEditable(false);
        /*Set the size*/
        type.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea description = new TextArea("DESCRIPTION:\n" + X.getCardDescription());
        /*Make it un-editable*/
        description.setEditable(false);
        /*Set WrapText*/
        description.setWrapText(true);
        /*Set the size*/
        description.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(description, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(description, 4);
        /*Set RowSpan*/
        GridPane.setRowSpan(description, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + Integer.toString(X.getAttackPoints()));
        /*Make it un-editable*/
        atkPoints.setEditable(false);
        /*Set the size*/
        atkPoints.setPrefSize(147, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(atkPoints, 0, 5);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + Integer.toString(X.getDefencePoints()));
        /*Make it un-editable*/
        defPoints.setEditable(false);
        /*Set the size*/
        defPoints.setPrefSize(147, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(defPoints, 2, 5);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set an action*/
        close.setOnAction(event -> monsterCardView.close());
        /*Add the TextArea to GridPane*/
        gridPane.add(close, 0, 6);
        /*Set the size*/
        close.setPrefSize(476, 50);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Stage Title*/
        monsterCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Stage Icon*/
        monsterCardView.getIcons().addAll(ProgramFunctions.getIcon());
        /*Set the Scene*/
        monsterCardView.setScene(scene);
        /*Make un-resizable*/
        monsterCardView.setResizable(false);
        /*Show and Wait*/
        monsterCardView.showAndWait();
    }
    /**
     * Function definition for monsterCardView()
     * <p>
     * Opens a window displaying the details of a given effect monster card
     * </p>
     *
     * @param X is the effect monster card to view
     */
    public static void effectCardView(EffectMonster X) {
        /*Create a Stage*/
        Stage monsterCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(74);
        /*Set percentage width*/
        col1.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(74);
        /*Set percentage width*/
        col2.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(74);
        /*Set percentage width*/
        col3.setPercentWidth(25);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(74);
        /*Set percentage width*/
        col4.setPercentWidth(25);
        /*Add all column constraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(43);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(43);
        /*Add all row constraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Make it un-editable*/
        name.setEditable(false);
        /*Set the size*/
        name.setPrefSize(200, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Make it un-editable*/
        attribute.setEditable(false);
        /*Set the size*/
        attribute.setPrefSize(30, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + Integer.toString(X.getLevel()));
        /*Make it un-editable*/
        level.setEditable(false);
        /*Set the size*/
        level.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Make it un-editable*/
        type.setEditable(false);
        /*Set the size*/
        type.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea description = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Make it un-editable*/
        description.setEditable(false);
        /*Set WrapText*/
        description.setWrapText(true);
        /*Set the size*/
        description.setPrefSize(296, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(description, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(description, 4);
        /*Set RowSpan*/
        GridPane.setRowSpan(description, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + Integer.toString(X.getAttackPoints()));
        /*Make it un-editable*/
        atkPoints.setEditable(false);
        /*Set the size*/
        atkPoints.setPrefSize(147, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(atkPoints, 0, 5);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + Integer.toString(X.getDefencePoints()));
        /*Make it un-editable*/
        defPoints.setEditable(false);
        /*Set the size*/
        defPoints.setPrefSize(147, 30);
        /*Add the TextArea to GridPane*/
        gridPane.add(defPoints, 2, 5);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set an action*/
        close.setOnAction(event -> monsterCardView.close());
        /*Add the TextArea to GridPane*/
        gridPane.add(close, 0, 6);
        /*Set the size*/
        close.setPrefSize(476, 50);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Stage Title*/
        monsterCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Stage Icon*/
        monsterCardView.getIcons().addAll(ProgramFunctions.getIcon());
        /*Set the Scene*/
        monsterCardView.setScene(scene);
        /*Make un-resizable*/
        monsterCardView.setResizable(false);
        /*Show and Wait*/
        monsterCardView.showAndWait();
    }
    /**
     * Function definition for spellCardView()
     * <p>
     * Opens a window displaying the details of a given spell card
     * </p>
     *
     * @param X is the spell card to view
     */
    public static void spellCardView(SpellCard X) {
        /*Create a Stage*/
        Stage spellCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints();
        /*Set the percentage width*/
        col1.setPercentWidth(100);
        /*Add the ColumnConstraint to the GridPane*/
        gridPane.getColumnConstraints().addAll(col1);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints();
        /*Set the percentage height*/
        row1.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints();
        /*Set the percentage height*/
        row2.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints();
        /*Set the percentage height*/
        row3.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints();
        /*Set the percentage height*/
        row4.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints();
        /*Set the percentage height*/
        row5.setPercentHeight(20);
        /*Add all the RowConstraints to the GridPane*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set un-editable*/
        name.setEditable(false);
        /*Set the size*/
        name.setPrefSize(476, 50);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getSpellType().toString());
        /*Set un-editable*/
        type.setEditable(false);
        /*Set the size*/
        type.setPrefSize(476, 50);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 1);
        /*Create a TextArea*/
        TextArea description = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the WrapText*/
        description.setWrapText(true);
        /*Set un-editable*/
        description.setEditable(false);
        /*Set the size*/
        description.setPrefSize(476, 100);
        /*Add the TextArea to the GridPane*/
        gridPane.add(description, 0, 2);
        /*Set the RowSpan*/
        GridPane.setRowSpan(description, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the size*/
        close.setPrefSize(476, 60);
        /*Set an action*/
        close.setOnAction(event -> {
            /*Close the window*/
            spellCardView.close();
        });
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        spellCardView.setScene(scene);
        /*Set the Title*/
        spellCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        spellCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Make un-resizable*/
        spellCardView.setResizable(false);
        /*Show and the wait*/
        spellCardView.showAndWait();
    }
    /**
     * Function definition for trapCardView()
     * <p>
     * Opens a window displaying the details of a given trap card
     * </p>
     *
     * @param X is the trap card to view
     */
    public static void trapCardView(TrapCard X) {
        /*Create a Stage*/
        Stage trapCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints();
        /*Set the percentage width*/
        col1.setPercentWidth(100);
        /*Add the ColumnConstraint to the GridPane*/
        gridPane.getColumnConstraints().addAll(col1);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints();
        /*Set the percentage height*/
        row1.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints();
        /*Set the percentage height*/
        row2.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints();
        /*Set the percentage height*/
        row3.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints();
        /*Set the percentage height*/
        row4.setPercentHeight(20);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints();
        /*Set the percentage height*/
        row5.setPercentHeight(20);
        /*Add all the RowConstraints to the GridPane*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set un-editable*/
        name.setEditable(false);
        /*Set the size*/
        name.setPrefSize(476, 50);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getTrapType().toString());
        /*Set un-editable*/
        type.setEditable(false);
        /*Set the size*/
        type.setPrefSize(476, 50);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 1);
        /*Create a TextArea*/
        TextArea description = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the WrapText*/
        description.setWrapText(true);
        /*Set un-editable*/
        description.setEditable(false);
        /*Set the size*/
        description.setPrefSize(476, 100);
        /*Add the TextArea to the GridPane*/
        gridPane.add(description, 0, 2);
        /*Set the RowSpan*/
        GridPane.setRowSpan(description, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the size*/
        close.setPrefSize(476, 60);
        /*Set an action*/
        close.setOnAction(event -> {
            /*Close the window*/
            trapCardView.close();
        });
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        trapCardView.setScene(scene);
        /*Set the Title*/
        trapCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        trapCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Make un-resizable*/
        trapCardView.setResizable(false);
        /*Show and the wait*/
        trapCardView.showAndWait();
    }
    /**
     * Function definition for ritualCardView()
     * <p>
     * Opens a window displaying the details of a given ritual monster card
     * </p>
     *
     * @param X is the ritual monster card to view
     */
    public static void ritualCardView(RitualMonster X) {
        /*Create a Stage*/
        Stage ritualCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(39);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + X.getLevel());
        /*Set the Size*/
        level.setPrefHeight(40);
        /*Set un-editable*/
        level.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("REQUIRED SPELL: " + X.getSpellName());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 4);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + X.getDefencePoints());
        /*Set the Size*/
        defPoints.setPrefHeight(40);
        /*Set un-editable*/
        defPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(defPoints, 2, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> ritualCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        ritualCardView.setScene(scene);
        /*Make un-resizable*/
        ritualCardView.setResizable(false);
        /*Set the Title*/
        ritualCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        ritualCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        ritualCardView.showAndWait();
    }
    /**
     * Function definition for fusionCardView()
     * <p>
     * Opens a window displaying the details of a given fusion monster card
     * </p>
     *
     * @param X is the fusion monster card to view
     */
    public static void fusionCardView(FusionMonster X) {
        /*Create a Stage*/
        Stage fusionCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(39);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + X.getLevel());
        /*Set the Size*/
        level.setPrefHeight(40);
        /*Set un-editable*/
        level.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("FUSION MATERIALS: " + X.listFusionMaterial());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 4);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + X.getDefencePoints());
        /*Set the Size*/
        defPoints.setPrefHeight(40);
        /*Set un-editable*/
        defPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(defPoints, 2, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> fusionCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        fusionCardView.setScene(scene);
        /*Make un-resizable*/
        fusionCardView.setResizable(false);
        /*Set the Title*/
        fusionCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        fusionCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        fusionCardView.showAndWait();
    }
    /**
     * Function definition for synchroCardView()
     * <p>
     * Opens a window displaying the details of a given synchro monster card
     * </p>
     *
     * @param X is the synchro monster card to view
     */
    public static void synchroCardView(SynchroMonster X) {
        /*Create a Stage*/
        Stage synchroCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(39);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + X.getLevel());
        /*Set the Size*/
        level.setPrefHeight(40);
        /*Set un-editable*/
        level.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("REQUIREMENTS: " + X.listSynchroMaterials());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 4);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + X.getDefencePoints());
        /*Set the Size*/
        defPoints.setPrefHeight(40);
        /*Set un-editable*/
        defPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(defPoints, 2, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> synchroCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        synchroCardView.setScene(scene);
        /*Make un-resizable*/
        synchroCardView.setResizable(false);
        /*Set the Title*/
        synchroCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        synchroCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        synchroCardView.showAndWait();
    }
    /**
     * Function definition for XYZCardView()
     * <p>
     * Opens a window displaying the details of a given XYZ monster card
     * </p>
     *
     * @param X is the XYZ monster card to view
     */
    public static void XYZCardView(XYZMonster X) {
        /*Create a Stage*/
        Stage XYZCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(39);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + X.getLevel());
        /*Set the Size*/
        level.setPrefHeight(40);
        /*Set un-editable*/
        level.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("REQUIREMENTS: " + X.listXYZMaterials());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 4);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + X.getDefencePoints());
        /*Set the Size*/
        defPoints.setPrefHeight(40);
        /*Set un-editable*/
        defPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(defPoints, 2, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> XYZCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        XYZCardView.setScene(scene);
        /*Make un-resizable*/
        XYZCardView.setResizable(false);
        /*Set the Title*/
        XYZCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        XYZCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        XYZCardView.showAndWait();
    }
    /**
     * Function definition for linkCardView()
     * <p>
     * Opens a window displaying the details of a given link monster card
     * </p>
     *
     * @param X is the link monster card to view
     */
    public static void linkCardView(LinkMonster X) {
        /*Create a Stage*/
        Stage linkCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(35);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(35);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(33);
        /*Create a RowConstraint*/
        RowConstraints row9 = new RowConstraints(35);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea links = new TextArea("LINKS: " + X.listLinks());
        /*Set the Size*/
        links.setPrefHeight(35);
        /*Set un-editable*/
        links.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(links, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(links, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("REQUIREMENTS: " + X.getSumReq());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 4);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 5);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> linkCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 8);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        linkCardView.setScene(scene);
        /*Make un-resizable*/
        linkCardView.setResizable(false);
        /*Set the Title*/
        linkCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        linkCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        linkCardView.showAndWait();
    }
    /**
     * Function definition for pendulumCardView()
     * <p>
     * Opens a window displaying the details of a given pendulum monster card
     * </p>
     *
     * @param X is the pendulum monster card to view
     */
    public static void pendulumCardView(PendulumMonster X) {
        /*Create a Stage*/
        Stage pendulumCardView = new Stage();
        /*Create a Group*/
        Group root = new Group();
        /*Create a StackPane*/
        StackPane stackPane = new StackPane();
        /*Create a GridPane*/
        GridPane backDrop = new GridPane();
        /*Create a Canvas*/
        Canvas canvas = new Canvas(480, 305);
        /*Create a GraphicsContext*/
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        /*Set the Fill*/
        graphicsContext.setFill(Color.DARKGRAY);
        /*Draw a Rectangle*/
        graphicsContext.fillRect(0, 0, 480, 305);
        /*Add the Canvas to the GridPane*/
        backDrop.add(canvas, 1, 1);
        /*Create a GridPane*/
        GridPane gridPane = new GridPane();
        /*Create a ColumnConstraint*/
        ColumnConstraints col1 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col2 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col3 = new ColumnConstraints(119);
        /*Create a ColumnConstraint*/
        ColumnConstraints col4 = new ColumnConstraints(119);
        /*Set the ColumnConstraints*/
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
        /*Create a RowConstraint*/
        RowConstraints row1 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row2 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row3 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row4 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row5 = new RowConstraints(39);
        /*Create a RowConstraint*/
        RowConstraints row6 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row7 = new RowConstraints(37);
        /*Create a RowConstraint*/
        RowConstraints row8 = new RowConstraints(39);
        /*Set the RowConstraints*/
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8);
        /*Create a TextArea*/
        TextArea name = new TextArea("NAME: " + X.getCardName());
        /*Set the Size*/
        name.setPrefHeight(40);
        /*Set un-editable*/
        name.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(name, 0, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(name, 2);
        /*Create a TextArea*/
        TextArea attribute = new TextArea("ATTRIBUTE: " + X.getMonsterAttribute().toString());
        /*Set the Size*/
        attribute.setPrefHeight(40);
        /*Set un-editable*/
        attribute.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(attribute, 2, 0);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(attribute, 2);
        /*Create a TextArea*/
        TextArea level = new TextArea("LEVEL: " + X.getLevel());
        /*Set the Size*/
        level.setPrefHeight(40);
        /*Set un-editable*/
        level.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(level, 0, 1);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(level, 4);
        /*Create a TextArea*/
        TextArea type = new TextArea("TYPE: " + X.getMonsterType().toString());
        /*Set the Size*/
        type.setPrefHeight(40);
        /*Set un-editable*/
        type.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(type, 0, 2);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(type, 4);
        /*Create a TextArea*/
        TextArea spellName = new TextArea("BLUE SCALE: " + X.getBlueScale());
        /*Set un-editable*/
        spellName.setEditable(false);
        /*Set the Size*/
        spellName.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(spellName, 0, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(spellName, 2);
        /*Create a TextArea*/
        TextArea redScale = new TextArea("RED SCALE: " + X.getRedScale());
        /*Set un-editable*/
        redScale.setEditable(false);
        /*Set the Size*/
        redScale.setPrefHeight(40);
        /*Add the TextArea to the GridPane*/
        gridPane.add(redScale, 2, 3);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(redScale, 2);
        /*Create a TextArea*/
        TextArea effect = new TextArea("EFFECT:\n" + X.getCardDescription() + "\nSPELL EFFECT:\n" + X.getSpellEffect());
        /*Set the Size*/
        effect.setPrefHeight(40);
        /*Set un-editable*/
        effect.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(effect, 0, 4);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(effect, 4);
        /*Set the RowSpan*/
        GridPane.setRowSpan(effect, 2);
        /*Create a TextArea*/
        TextArea atkPoints = new TextArea("ATK: " + X.getAttackPoints());
        /*Set the Size*/
        atkPoints.setPrefHeight(40);
        /*Set un-editable*/
        atkPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(atkPoints, 0, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(atkPoints, 2);
        /*Create a TextArea*/
        TextArea defPoints = new TextArea("DEF: " + X.getDefencePoints());
        /*Set the Size*/
        defPoints.setPrefHeight(40);
        /*Set un-editable*/
        defPoints.setEditable(false);
        /*Add the TextArea to the GridPane*/
        gridPane.add(defPoints, 2, 6);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(defPoints, 2);
        /*Create a Button*/
        Button close = new Button("Close");
        /*Set the Size*/
        close.setPrefSize(476, 36);
        /*Set an action*/
        close.setOnAction(event -> pendulumCardView.close());
        /*Add the Button to the GridPane*/
        gridPane.add(close, 0, 7);
        /*Set the ColumnSpan*/
        GridPane.setColumnSpan(close, 4);
        /*Create the Insets*/
        Insets insets = new Insets(2, 2, 2, 2);
        /*Set the Padding*/
        gridPane.setPadding(insets);
        /*Add the GridPanes to the StackPane*/
        stackPane.getChildren().addAll(backDrop, gridPane);
        /*Add the StackPane to the Group*/
        root.getChildren().addAll(stackPane);
        /*Create a Scene*/
        Scene scene = new Scene(root, 480, 305);
        /*Set the Scene*/
        pendulumCardView.setScene(scene);
        /*Make un-resizable*/
        pendulumCardView.setResizable(false);
        /*Set the Title*/
        pendulumCardView.setTitle("View Card: " + X.getCardName());
        /*Set the Icon*/
        pendulumCardView.getIcons().add(ProgramFunctions.getIcon());
        /*Show and Wait*/
        pendulumCardView.showAndWait();
    }
}
