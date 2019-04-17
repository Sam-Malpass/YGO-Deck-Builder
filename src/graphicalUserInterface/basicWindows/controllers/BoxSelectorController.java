/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.basicWindows.controllers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;

import java.net.URL;
import java.util.ResourceBundle;
public class BoxSelectorController implements Initializable {
    /**
     * cancel holds the cancel button
     */
    @FXML private Button cancel;
    /**
     * confirm holds the confirm button
     */
    @FXML private Button confirm;
    /**
     * comboBox holds the ComboBox
     */
    @FXML private ComboBox comboBox;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> containers = comboBox.getItems();
        for(String C : ProgramFunctions.getGUI().getBasicWindows().getItems()) {
            containers.add(C);
        }
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }
        });
        confirm.setOnAction(e -> {
            /*Attempt*/
            try {
                /*Get the result*/
                ProgramFunctions.getGUI().getBasicWindows().setsResult(comboBox.getValue().toString());
                /*Close the window*/
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }
            /*If failed*/ catch (NullPointerException e1) {
                /*Make result empty*/
                ProgramFunctions.getGUI().getBasicWindows().setsResult("");
                /*Make an alert*/
                ProgramFunctions.showAlert("Error 007", "Field filled incorrectly");
            }
        });
    }
}
