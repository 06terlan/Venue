package ui.venue.controller;

import bll.Building;
import bll.service.VenueService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddVenueController implements Initializable {

    private VenueService venueService;

    @FXML private TextField buildingNumberTextField;
    @FXML private TextField roomNumberTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private Button btnAdd;
    @FXML private Button btnEdit;
    @FXML private Button btnCancel;

    private Building building;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venueService = new VenueService();
    }

    @FXML
    private void addNewBuilding(ActionEvent event) throws IOException {
        String buildingNumber = buildingNumberTextField.getText().trim();
        String roomNumber = roomNumberTextField.getText().trim();
        String city = cityTextField.getText().trim();
        String state = stateTextField.getText().trim();
        String street = streetTextField.getText().trim();
        int zipCode = Integer.parseInt(zipCodeTextField.getText().trim());
        this.venueService.addBuilding(buildingNumber,roomNumber,city,state,street,zipCode);
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        changeScene("/ui/venue/fxml/rooms.fxml",stage);
    }

    @FXML
    private void editBuilding(ActionEvent event) throws IOException {
        String buildingNumber = buildingNumberTextField.getText().trim();
        try {
            this.venueService.editBuilding(building.getId(),buildingNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        changeScene("/ui/venue/fxml/rooms.fxml",(Stage) btnEdit.getScene().getWindow());
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        changeScene("/ui/venue/fxml/rooms.fxml",stage);
    }

    private void changeScene(String fxmlLocation,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public TextField getZipCodeTextField() {
        return zipCodeTextField;
    }

    public TextField getStreetTextField() {
        return streetTextField;
    }

    public TextField getStateTextField() {
        return stateTextField;
    }

    public TextField getCityTextField() {
        return cityTextField;
    }

    public TextField getBuildingNumberTextField() {
        return buildingNumberTextField;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }
}
