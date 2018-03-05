package by.bsuir.parser.controller;

import by.bsuir.parser.ConfigReader;
import by.bsuir.parser.dialog.Dialogs;
import by.bsuir.parser.model.Table;
import by.bsuir.parser.service.Generator;
import by.bsuir.parser.service.ParserFabric;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Shaliou_AG
 */
public class MainController implements Initializable {

    private Stage mainStage;
    private ResourceBundle resourceBundle;
    private Table table;
    private Map<String, StringBuilder> templateMap = new HashMap<>();
    private List<String> tempHeaderList = new ArrayList<>();
    private StringBuilder searchTemplate = new StringBuilder("");
    private String selectedItemListView;

    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        if (listViewId.getItems().size() == 0) {
            textAreaTemplateId.setVisible(false);
            textFieldSearchId.setVisible(false);
            buttonCleanId.setVisible(false);
        }
        initLocale();
    }


    private void initLocale() {
        menuFileId.setText(resourceBundle.getString(MENU_FILE));
        menuSettingsId.setText(resourceBundle.getString(MENU_ITEM_SETTINGS));
        menuChangeLangId.setText(resourceBundle.getString(MENU_CHANGE_LANG));
        menuHelpId.setText(resourceBundle.getString(MENU_HELP));

        menuOpenCSVId.setText(resourceBundle.getString(MENU_ITEM_OPEN_FILE));
        menuChangeLangEn.setText(resourceBundle.getString(MENU_ITEM_LANG_EN));
        menuChangeLangRu.setText(resourceBundle.getString(MENU_ITEM_LANG_RU));
        menuAboutId.setText(resourceBundle.getString(MENU_ITEM_ABOUT));

        labelColumnNameId.setText(resourceBundle.getString(LABEL_COLUMN_NAME1));

        labelCustomizationId.setText(resourceBundle.getString(LABEL_CUSTOMIZATION));

        textFieldSearchId.setPromptText(resourceBundle.getString(FIELD_SEARCH));

        buttonInjectId.setText(resourceBundle.getString(BUTTON_INJECT));
        buttonViewId.setText(resourceBundle.getString(BUTTON_VIEW));
        buttonGenerateId.setText(resourceBundle.getString(BUTTON_GENERATE));
        buttonCleanId.setText(resourceBundle.getString(BUTTON_CLEAN));
        if (mainStage != null) {
            mainStage.setTitle(resourceBundle.getString(APPLICATION_TITLE));
        }
    }


    public void listViewMousePressed(MouseEvent mouseEvent) {
        if (selectedItemListView != null) {
            templateMap.put(selectedItemListView, new StringBuilder(textAreaTemplateId.getText()));
        }
    }

    public void listViewMouseReleased(MouseEvent mouseEvent) {
        selectedItemListView = listViewId.getSelectionModel().getSelectedItem();
        StringBuilder template = templateMap.get(selectedItemListView);
        if (template != null) {
            textAreaTemplateId.setText(template.toString());
        }
        if (listViewId.getItems().size() != 0) {
            textAreaTemplateId.setVisible(true);
        }
    }

    public void onKeyReleasedSearchField(KeyEvent keyEvent) {
        if (!keyEvent.getText().equals("")) {
            searchTemplate = searchTemplate.append(keyEvent.getText().toLowerCase());
        } else {
            searchTemplate = searchTemplate.delete(0,searchTemplate.length() - 1);
            tempHeaderList = table.getHeaderList();
        }
        tempHeaderList = tempHeaderList.stream().filter(it -> it.toLowerCase().contains(searchTemplate.toString())).collect(Collectors.toList());
        listViewId.setItems(FXCollections.observableArrayList(tempHeaderList));
    }

    public void onMouseClickedButtonClean(MouseEvent mouseEvent) {
        searchTemplate.delete(0, searchTemplate.length()).append("");
        textFieldSearchId.clear();
        List<String> headerList = table.getHeaderList();
        tempHeaderList = headerList;
        listViewId.setItems(FXCollections.observableArrayList(headerList));
    }

    public void viewOnClickedListener(MouseEvent mouseEvent) {
        if (templateMap.get(selectedItemListView).toString().equals("")) { /////////////////////////// добавить и в генерате
            templateMap.put(selectedItemListView, new StringBuilder(textAreaTemplateId.getText()));
        }
        Generator generator = new Generator();
        String content = generator.view(templateMap, table);
        if (!content.equals("")) {
            Dialogs.getInstance().showDialog(resourceBundle.getString(SHOW_DIALOG_VIEW_NAME), content, mainStage);
        } else {
           Dialogs.getInstance().errorDialog(resourceBundle.getString(ERROR), resourceBundle.getString(ERROR_TEXT_3));
        }
    }

    public void openFileListener(ActionEvent actionEvent) {
        textAreaTemplateId.clear();
        textFieldSearchId.clear();

        File selectedFile = getFile();
        Dialogs dialogs = Dialogs.getInstance();
        String[] extensions = ConfigReader.getExtensions().split(",");
        if (!Arrays.stream(extensions).anyMatch((it) -> selectedFile.toString().endsWith(it))) {
            dialogs.errorDialog(resourceBundle.getString(ERROR), resourceBundle.getString(ERROR_TEXT_1));
            openFileListener(null);
        }
        table = ParserFabric.getInstance().doParse(selectedFile);
        if (table == null) {
            dialogs.errorDialog(resourceBundle.getString(ERROR), resourceBundle.getString(ERROR_TEXT_2));
        } else {
            List<String> headerList = table.getHeaderList();
            ObservableList<String> items = FXCollections.observableArrayList (headerList);
            listViewId.setItems(items);
            tempHeaderList = listViewId.getItems();
            for (String item : headerList) {
                templateMap.put(item, new StringBuilder(""));
            }
            textFieldSearchId.setVisible(true);
            buttonCleanId.setVisible(true);
        }


    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        String[] extensions = ConfigReader.getExtensions().split(",");
        for (String extension : extensions) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extension, "*." + extension));
        }
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


    @FXML private Menu menuFileId;
    @FXML private Menu menuSettingsId;
    @FXML private Menu menuChangeLangId;
    @FXML private Menu menuHelpId;

    @FXML private MenuItem menuOpenCSVId;
    @FXML private MenuItem menuChangeLangEn;
    @FXML private MenuItem menuChangeLangRu;
    @FXML private MenuItem menuAboutId;

    @FXML private ListView<String> listViewId;

    @FXML private Label labelCustomizationId;
    @FXML private Label labelColumnNameId;

    @FXML private Button buttonInjectId;
    @FXML private Button buttonViewId;
    @FXML private Button buttonGenerateId;
    @FXML private Button buttonCleanId;


    @FXML private TextArea textAreaTemplateId;
    @FXML private TextField textFieldSearchId;

    private final String APPLICATION_TITLE = "application.title";
    private final String BUTTON_GENERATE = "button.generate";
    private final String BUTTON_INJECT = "button.inject";
    private final String BUTTON_VIEW = "button.view";
    private final String BUTTON_CLEAN = "button.clean";
    private final String LABEL_CUSTOMIZATION = "label.customization";
    private final String MENU_FILE = "menu.file";
    private final String MENU_HELP = "menu.help";
    private final String MENU_CHANGE_LANG = "menu.menuItem.changeLang";
    private final String MENU_ITEM_ABOUT = "menuItem.about";
    private final String MENU_ITEM_OPEN_FILE = "menuItem.openFile";
    private final String MENU_ITEM_SETTINGS = "menu.menuItem.settings";
    private final String MENU_ITEM_LANG_EN = "menuItem.settings.changeLang.en";
    private final String MENU_ITEM_LANG_RU = "menuItem.settings.changeLang.ru";
    private final String LABEL_COLUMN_NAME1 = "label.column.name";
    private final String FIELD_SEARCH = "text.field.search";
    private final String FILE_CHOOSER_CHOOSE = "fileChooser.chooseFile";
    private final String SHOW_DIALOG_VIEW_NAME = "showDialog.view.name";
    private final String ERROR = "error";
    private final String ERROR_TEXT_1 = "error.message.1";
    private final String ERROR_TEXT_2 = "error.message.2";
    private final String ERROR_TEXT_3 = "error.message.3";


    public void onMouseClickedGenerateListener(MouseEvent mouseEvent) {

    }
}
