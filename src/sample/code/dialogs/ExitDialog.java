package sample.code.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


/**
 * Class used to show dialog when decided to close App
 *
 * @see javafx.scene.control.Alert
 */
public class ExitDialog extends Alert{
    /**
     * Basic constructor where main looking is prepared
     */
    public ExitDialog() {
        super(AlertType.CONFIRMATION);
        setTitle("Exit");
        setHeaderText("Exit");
        setContentText("Are you sure you want to quit?");
    }

    /**
     * Method that prepares a result and shows dialog
     *
     * @return if user clicked OK or not
     */
    public boolean showDialog() {
        Optional<ButtonType> result = showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }
        else return false;
    }
}
