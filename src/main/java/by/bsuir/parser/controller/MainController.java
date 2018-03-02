package by.bsuir.parser.controller;

import by.bsuir.parser.ConfigReader;
import by.bsuir.parser.dialog.Dialogs;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author Shaliou_AG
 */
public class MainController implements Initializable {

    private Stage mainStage; // для загрузки
    private ResourceBundle resourceBundle;

    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        initLocale();
    }

    private void initLocale() {
        menuFileId.setText(resourceBundle.getString(MENU_FILE));
        menuSettingsId.setText(resourceBundle.getString(MENU_ITEM_SETTINGS));
        menuChangeLangId.setText(resourceBundle.getString(MENU_CHANGE_LANG));
        menuHelpId.setText(resourceBundle.getString(MENU_HELP));

        menuOpenCSVId.setText(resourceBundle.getString(MENU_ITEM_OPEN_CSV));
        menuChangeLangEn.setText(resourceBundle.getString(MENU_ITEM_LANG_EN));
        menuChangeLangRu.setText(resourceBundle.getString(MENU_ITEM_LANG_RU));
        menuAboutId.setText(resourceBundle.getString(MENU_ITEM_ABOUT));

        tableViewColumnName1Id.setText(resourceBundle.getString(TABLE_VIEW_COLUMN_NAME1));

        labelCustomizationId.setText(resourceBundle.getString(LABEL_CUSTOMIZATION));

        buttonInjectId.setText(resourceBundle.getString(BUTTON_INJECT));
        buttonViewId.setText(resourceBundle.getString(BUTTON_VIEW));
        buttonGenerateId.setText(resourceBundle.getString(BUTTON_GENERATE));
        if (mainStage != null) {
            mainStage.setTitle(resourceBundle.getString(APPLICATION_TITLE));
        }
    }

    public void openCsvListener(ActionEvent actionEvent) {
        File selectedFile = getFile();
        String[] extensions = ConfigReader.getExtensions().split(",");
        if (!Arrays.stream(extensions).anyMatch((it) -> selectedFile.toString().endsWith(it))) {
            Dialogs.getInstance().errorDialog(resourceBundle.getString(ERROR), resourceBundle.getString(ERROR_TEXT_1));
            openCsvListener(null);
        }


    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(resourceBundle.getString(FILE_CHOOSER_CHOOSE));
        return fileChooser.showOpenDialog(mainStage);
    }


    public void changeLangEn(ActionEvent actionEvent) throws IOException {
        initialize(null, ResourceBundle.getBundle(resourceBundle.getBaseBundleName(), new Locale("en")));
    }

    public void changeLangRu(ActionEvent actionEvent) throws IOException {
        initialize(null, ResourceBundle.getBundle(resourceBundle.getBaseBundleName(), new Locale("ru")));
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    public Menu menuFileId;
    public Menu menuSettingsId;
    public Menu menuChangeLangId;
    public Menu menuHelpId;

    public MenuItem menuOpenCSVId;
    public MenuItem menuChangeLangEn;
    public MenuItem menuChangeLangRu;
    public MenuItem menuAboutId;

    public TableColumn tableViewColumnName1Id;

    public Label labelCustomizationId;

    public Button buttonInjectId;
    public Button buttonViewId;
    public Button buttonGenerateId;

    private final String APPLICATION_TITLE = "application.title";
    private final String BUTTON_GENERATE = "button.generate";
    private final String BUTTON_INJECT = "button.inject";
    private final String BUTTON_VIEW = "button.view";
    private final String LABEL_CUSTOMIZATION = "label.customization";
    private final String MENU_FILE = "menu.file";
    private final String MENU_HELP = "menu.help";
    private final String MENU_CHANGE_LANG = "menu.menuItem.changeLang";
    private final String MENU_ITEM_ABOUT = "menuItem.about";
    private final String MENU_ITEM_OPEN_CSV = "menuItem.openCSV";
    private final String MENU_ITEM_SETTINGS = "menu.menuItem.settings";
    private final String MENU_ITEM_LANG_EN = "menuItem.settings.changeLang.en";
    private final String MENU_ITEM_LANG_RU = "menuItem.settings.changeLang.ru";
    private final String TABLE_VIEW_COLUMN_NAME1 = "tableView.columnName1";
    private final String FILE_CHOOSER_CHOOSE = "fileChooser.chooseFile";
    private final String ERROR = "error";
    private final String ERROR_TEXT_1 = "error.text.1";



}
