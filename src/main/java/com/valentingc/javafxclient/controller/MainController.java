package com.valentingc.javafxclient.controller;

import com.jfoenix.controls.JFXSpinner;
import com.valentingc.javafxclient.controller.internal.TabPaneEntry;
import com.valentingc.javafxclient.dto.LoginDto;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * Main controller of the application. Mostly for utilities.
 *
 * @author Valentin Goronjic
 */
public class MainController implements Initializable {

    private static final Logger LOG = LogManager.getLogger(
        MainController.class);

    private static final JFXSpinner SPINNER = new JFXSpinner();

    @FXML
    public AnchorPane userHeaderMenu;

    @FXML
    public AnchorPane tabPane;

    @FXML
    private TabPaneController tabPaneController;

    @FXML
    private UserController userHeaderMenuController;

    private ResourceBundle resources;

    private LoginDto currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userHeaderMenuController.setParentController(this);
        this.tabPaneController.setParentController(this);
        this.resources = resources;
        LOG.debug("Initialized MainController");
    }

    // intentionally set package modifier
    Queue<TabPaneEntry> getPermittedTabs(
    ) {
        Queue<TabPaneEntry> result = new PriorityQueue<>(
            Comparator.comparingInt(TabPaneEntry::getOrder)
        );

        result.add(TabPaneEntry.SEARCH);

        if (result.isEmpty()) {
            LOG.debug("No Tabs allowed. Adding unsupported tab as backup");
            result.add(
                TabPaneEntry.UNSUPPORTED
            );
        }

        return result;
    }

    /**
     * Shows an error alert with a custom title and error message.
     *
     * @param alertTitle The alert title to use
     * @param errorText  The error text to use
     */
    protected static void showErrorAlert(String alertTitle, String errorText, String buttonTitle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setContentText(errorText);

        ButtonType okButton = new ButtonType(buttonTitle, ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.equals(okButton)) {
                alert.close();
            }
        });
    }

    /**
     * Switches the current scene to the given FXML.
     *
     * @param fxmlPath The path to the FXML file (ex. "/view/login.fxml")
     * @param bundle Current Resource Bundle
     * @param node Node that is required to aquire the current scene
     * @param <T> Type of target Calendar, ex. MainController
     * @return Controller instance of the loaded FXML
     */
    public static <T> T switchSceneTo(String fxmlPath, ResourceBundle bundle, Node node)
        throws IOException {
        Scene oldScene = node.getScene();
        Stage stage = (Stage) oldScene.getWindow();

        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxmlPath), bundle);
        Parent parent = loader.load();
        T controller = loader.getController();
        Scene newScene = new Scene(parent, oldScene.getWidth(), oldScene.getHeight());
        newScene.getStylesheets().add("css/styles.css");
        stage.setScene(newScene);

        return controller;
    }

    /**
     * Sets the currently logged in User.
     * @param user The currently logged in user
     */
    public synchronized void setLoginUser(LoginDto user) {
        this.currentUser = user;

        this.tabPaneController.initializeTabMenu();
        this.userHeaderMenuController.setUserShortcut(currentUser.getUsername());
        this.userHeaderMenuController.setUserTxtRole("USER (TODO)"); // TODO: implement own

    }


    /**
     * Adds and shows a Loading Spinner in the given AnchorPane.
     *
     * @param pane The AnchorPane which will have the Spinner added at the bottom-center
     */
    public static void enableSpinner(AnchorPane pane) {
        LOG.debug("Enabling Spinner");
        SPINNER.setPrefSize(50, 50);

        if (!pane.getChildren().contains(SPINNER)) {
            pane.getChildren().add(SPINNER);
        }

        AnchorPane.setBottomAnchor(SPINNER, 50d);
        AnchorPane.setLeftAnchor(SPINNER, (pane.getWidth() - SPINNER.getPrefWidth()) / 2);
    }

    /**
     * Removes an active Loading Spinner in the given AnchorPane.
     *
     * @param pane The AnchorPane which will have the Spinner removed again
     */
    public static void disableSpinner(AnchorPane pane) {
        LOG.debug("Disabling Spinner");
        if (pane.getChildren().contains(SPINNER)) {
            pane.getChildren().remove(SPINNER);
        }
    }

}
