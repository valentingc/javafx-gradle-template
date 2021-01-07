package com.valentingc.javafxclient.controller;

import com.valentingc.javafxclient.controller.internal.Parentable;
import com.valentingc.javafxclient.dto.DemoDto;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstratory Controller for the search.
 *
 * @author Valentin Goronjic
 */
public class SearchController implements Initializable, Parentable<TabPaneController> {

    private static final Logger LOG = LogManager.getLogger(
        SearchController.class);
    private TabPaneController parentController;
    private ResourceBundle resourceBundle;
    @FXML
    private Label lblType;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;
    @FXML
    private TableView<DemoDto> tblResults;
    @FXML
    private TableColumn<DemoDto, Button> columnTitle;
    @FXML
    private TableColumn<DemoDto, Button> columnDescription;
    @FXML
    private Button btnSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        this.btnSearch.setOnAction(e -> {
            LOG.debug("Search button pressed");
            List<DemoDto> demoResult = new LinkedList<>();
            demoResult.add(new DemoDto("Demo 1", "Lorem Ipsum"));
            demoResult.add(new DemoDto("Demo 2", "Lorem Ipsum"));
            this.tblResults.setItems(
                FXCollections.observableArrayList(demoResult)
            );
        });


        LOG.debug("Initialized SearchController");
    }

    @Override
    public TabPaneController getParentController() {
        return this.parentController;
    }

    @Override
    public void setParentController(TabPaneController controller) {
        this.parentController = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeWithParent() {
        LOG.debug("Initialized SearchController with parent");
    }

}
