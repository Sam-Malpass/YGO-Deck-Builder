/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package graphicalUserInterface.basicWindows.controllers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;

import java.net.URL;
import java.util.ResourceBundle;
public class YesNoController implements Initializable {
    /**
     * no holds the no button
     */
    @FXML private Button no;
    /**
     * yes holds the yes button
     */
    @FXML private Button yes;
    /**
     * label holds the window's prompt
     */
    @FXML private Label label;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setWrapText(true);
        label.setText(ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().getLabel());
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) no.getScene().getWindow();
                stage.close();
            }
        });
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*Set the flag to true*/
                ProgramFunctions.getProgramData().getUserInterface().getBasicWindows().setFlag(true);
                Stage stage = (Stage) yes.getScene().getWindow();
                stage.close();
            }
        });
    }
}
