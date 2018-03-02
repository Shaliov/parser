package by.bsuir.parser.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * @author Shaliou_AG
 */
public class Dialogs {
    private static Dialogs instance = null;


    public void errorDialog(String error, String textError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText("");
        alert.setContentText(textError);

        alert.showAndWait();
    }

    public static Dialogs getInstance() {
        if (instance == null) {
            instance = new Dialogs();
        }
        return instance;
    }
}
