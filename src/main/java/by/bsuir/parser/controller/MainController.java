package by.bsuir.parser.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Shaliou_AG
 */
public class MainController implements Initializable {
    private Stage mainStage; // для загрузки
    private ResourceBundle resourceBundle;

    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;

    }


    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
