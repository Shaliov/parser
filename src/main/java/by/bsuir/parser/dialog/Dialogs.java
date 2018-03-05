package by.bsuir.parser.dialog;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Shaliou_AG
 */
public class Dialogs {
    private static Dialogs instance = null;

    private Dialogs() {
    }

    public void errorDialog(String error, String textError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText("");
        alert.setContentText(textError);

        alert.showAndWait();
    }

    public void showDialog (String name, String content, Stage mainStage) {
        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(textArea);


        Scene secondScene = new Scene(secondaryLayout, mainStage.getWidth(), mainStage.getHeight());

        Stage newWindow = new Stage();
        newWindow.setTitle(name);
        newWindow.setScene(secondScene);

        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.initOwner(mainStage);

        newWindow.setX(mainStage.getX());
        newWindow.setY(mainStage.getY());

        newWindow.show();
    }

    public static Dialogs getInstance() {
        if (instance == null) {
            instance = new Dialogs();
        }
        return instance;
    }
}
