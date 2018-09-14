package ui.venue.controller;

import bll.Building;
import bll.Rule.RuleException;
import bll.Rule.RuleFactory;
import bll.model.Address;
import bll.service.VenueService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Label errorLabel;

    private Building building;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venueService = new VenueService();
    }

    @FXML
    private void addNewBuilding(ActionEvent event) throws IOException {
        try {
            validateFields();
            addBuilding();
            changeScene("/ui/venue/fxml/rooms.fxml",btnAdd);
        }catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    private void addBuilding() {
        String buildingNumber = buildingNumberTextField.getText().trim();
        String roomNumber = roomNumberTextField.getText().trim();
        String city = cityTextField.getText().trim();
        String state = stateTextField.getText().trim();
        String street = streetTextField.getText().trim();
        int zipCode = Integer.parseInt(zipCodeTextField.getText().trim());
        this.venueService.addBuilding(buildingNumber,roomNumber,city,state,street,zipCode);
    }

    @FXML
    private void editBuilding(ActionEvent event) {
        try{
            String buildingNumber = buildingNumberTextField.getText().trim();
            bll.model.Building building = new bll.model.Building(buildingNumber);
            RuleFactory.getRule(AddVenueController.class).validate(building);
            this.venueService.editBuilding(this.building.getId(),buildingNumber);
            changeScene("/ui/venue/fxml/rooms.fxml",btnEdit);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        changeScene("/ui/venue/fxml/rooms.fxml",btnCancel);
    }

    private void validateFields() throws RuleException {
        bll.model.Building building = new bll.model.Building(roomNumberTextField.getText());
        RuleFactory.getRule(AddVenueController.class).validate(building);
        Address address = new Address(zipCodeTextField.getText(),cityTextField.getText(),streetTextField.getText(),stateTextField.getText());
        RuleFactory.getRule(Address.class).validate(address);
    }

    private void changeScene(String fxmlLocation,Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            errorLabel.setText("Something goes wrong! :-(");
        }
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
