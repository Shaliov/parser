package by.bsuir.parser.dialog;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Shaliou_AG
 */
public class Dialogs {
    private static Dialogs instance = null;
    private ProgressIndicator progressIndicator;

    private Dialogs() {
    }

    public File choseDirectory(String title, Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            return file;
        }
        return null;
    }

    public void errorDialog(String title, String textError) {
        showAlert(title, textError, Alert.AlertType.ERROR);
    }

    public void showDialog(String title, String test) {
        showAlert(title, test, Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String test, Alert.AlertType information) {
        Alert alert = new Alert(information);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(test);

        alert.showAndWait();
    }

    public void showDialog (String name, String content, Stage stage) {
        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(textArea);

        Scene secondScene = new Scene(secondaryLayout, stage.getWidth(), stage.getHeight());

        Stage newWindow = new Stage();
        newWindow.setTitle(name);
        newWindow.setScene(secondScene);

        newWindow.initModality(Modality.WINDOW_MODAL);

        newWindow.initOwner(stage);

        newWindow.setX(stage.getX());
        newWindow.setY(stage.getY());

        newWindow.show();
    }

    public static Dialogs getInstance() {
        if (instance == null) {
            instance = new Dialogs();
        }
        return instance;
    }
}
