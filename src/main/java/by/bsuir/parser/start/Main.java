package by.bsuir.parser.start;

import by.bsuir.parser.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Shaliou_AG
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("ru")));
        Parent fxmlMain = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();
        controller.setMainStage(primaryStage);

        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);

        primaryStage.setTitle(fxmlLoader.getResources().getString("application.title"));
        primaryStage.setScene(new Scene(fxmlMain));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
