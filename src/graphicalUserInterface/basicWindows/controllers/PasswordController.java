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
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import programFunctions.ProgramFunctions;

import java.net.URL;
import java.util.ResourceBundle;
public class PasswordController implements Initializable {
    /**
     * cancel holds the cancel button
     */
    @FXML private Button cancel;
    /**
     * confirm holds the confirm button
     */
    @FXML private Button confirm;
    /**
     * pass holds the password entry area
     */
    @FXML private PasswordField pass;
    /**
     * Function definition for initialize()
     * <p>
     *     Sets up the controller
     * </p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }
        });
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*Set the sResult to the input*/
                ProgramFunctions.getGUI().getBasicWindows().setsResult(pass.getText());
                /*Close the window*/
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }
        });
        pass.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            /*If the ENTER key is pressed*/
            switch (keyEvent.getCode()) {
                case ENTER:
                    /*Set the sResult to the input*/
                    ProgramFunctions.getGUI().getBasicWindows().setsResult(pass.getText());
                    /*Close the window*/
                    Stage stage = (Stage) cancel.getScene().getWindow();
                    stage.close();
            }
        });
    }
}
