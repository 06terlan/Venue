package ui.venue.controller;

import bll.Address;
import bll.Building;
import bll.service.VenueService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class VenueController implements Initializable {

    private VenueService venueService;

    private ObservableList<Building> buildings = FXCollections.observableArrayList();

    @FXML
    private ListView buildingListView;

    @FXML
    private Button addNewBuilding;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venueService = new VenueService();
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO show some Error dialog
        }
        createListInterface();
    }

    @FXML
    private void addNewBuilding(ActionEvent event) throws IOException {
        changeScene("/ui/venue/fxml/addVenue.fxml");
    }

    private void changeScene(String fxmlLocation) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Stage stage = (Stage) addNewBuilding.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void createListInterface() {
        buildings.forEach(buiding -> {
            BorderPane borderPane = createListItemBorderPane(buiding);
            buildingListView.getItems().add(borderPane);
        });
    }

    private BorderPane createListItemBorderPane(Building buiding) {
        BorderPane borderPane = new BorderPane();
        GridPane labelGridPane = createBuildingInfo(buiding);
        GridPane gridPane = createBuildingEditButtonGridPane();
        borderPane.setLeft(labelGridPane);
        borderPane.setRight(gridPane);
        return borderPane;
    }

    private GridPane createBuildingEditButtonGridPane() {
        Button btnEdit = createEditButton();
        Button btnRemove = createRemoveButton();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.add(btnEdit,0,0);
        gridPane.add(btnRemove,1,0);
        return gridPane;
    }

    private Button createRemoveButton() {
        Button btnRemove = new Button("remove");
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        return btnRemove;
    }

    private Button createEditButton() {
        Button btnEdit = new Button("edit");
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        return btnEdit;
    }

    private GridPane createBuildingInfo(Building buiding) {
        GridPane labelGridPane = new GridPane();
        labelGridPane.add(createBuildingInfoLabel(buiding),0,0);
        labelGridPane.add(createAddressInfoLabel(buiding.getAddress().toString()),0,1);
        return labelGridPane;
    }

    private Label createAddressInfoLabel(String address) {
        Label labelAddress = new Label(address);
        labelAddress.setMinWidth(100);
        labelAddress.setMinHeight(10);
        return labelAddress;
    }

    private Label createBuildingInfoLabel(Building buiding) {
        Label labelBuildingNumber = createAddressInfoLabel(buiding.getBuildingNumber());
        labelBuildingNumber.setFont(new Font(18));
        return labelBuildingNumber;
    }

    private void loadData() throws Exception {
        buildings.addAll(venueService.getAllBuildings());
    }

}
