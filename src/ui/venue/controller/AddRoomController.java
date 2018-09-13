package ui.venue.controller;

import bll.Room;
import bll.service.VenueService;
import factory.RoomType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {

    private VenueService venueService;

    @FXML private RadioButton meetingRdBtn;
    @FXML private RadioButton conferenceRbBtn;
    @FXML private RadioButton concertRdBtn;
    @FXML private TextField roomNumberTextField;
    @FXML private TextField priceTextField;
    @FXML private Button btnAddNewRoom;
    @FXML private Button btnEditRoom;
    @FXML private Button btnCancel;

    private ToggleGroup toggleGroup;

    private RoomType roomType;
    private Room room;
    public static int buildingId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venueService = new VenueService();
        initializeRadioButtons();
        System.out.println(buildingId);
    }

    private void initializeRadioButtons() {
        toggleGroup = new ToggleGroup();
        groupRadioButtons();
        addRadioBtnEventListener();
    }

    private void addRadioBtnEventListener() {
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(toggleGroup.getSelectedToggle() != null) {
                    RadioButton selectedRadioButton = (RadioButton)toggleGroup.getSelectedToggle();
                    roomType = (RoomType) selectedRadioButton.getUserData();
                }
            }
        });
    }

    private void groupRadioButtons() {
        meetingRdBtn.setUserData(RoomType.MEETING);
        meetingRdBtn.setToggleGroup(toggleGroup);
        conferenceRbBtn.setUserData(RoomType.CONFERENCE);
        conferenceRbBtn.setToggleGroup(toggleGroup);
        concertRdBtn.setUserData(RoomType.HALL);
        concertRdBtn.setToggleGroup(toggleGroup);
    }

    @FXML
    private void addNewRoom(ActionEvent event) throws Exception {
        String roomNumber = roomNumberTextField.getText().trim();
        double price = Double.parseDouble(priceTextField.getText().trim());
        this.venueService.addRoom(buildingId,roomNumber,roomType,price);
        Stage stage = (Stage) btnAddNewRoom.getScene().getWindow();
        changeScene("/ui/venue/fxml/rooms.fxml",stage);
    }
    @FXML
    private void editRoom(ActionEvent event) throws Exception {
        String roomNumber = roomNumberTextField.getText().trim();
        double price =  Double.parseDouble(priceTextField.getText().trim());
        this.venueService.editRoom(room.getRoomId(),roomNumber,price,roomType);
    }

    private void changeScene(String fxmlLocation,Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        changeScene("/ui/venue/fxml/rooms.fxml",stage);
    }

    public RadioButton getMeetingRdBtn() {
        return meetingRdBtn;
    }

    public RadioButton getConferenceRbBtn() {
        return conferenceRbBtn;
    }

    public RadioButton getConcertRdBtn() {
        return concertRdBtn;
    }

    public TextField getRoomNumberTextField() {
        return roomNumberTextField;
    }

    public TextField getPriceTextField() {
        return priceTextField;
    }

    public Button getBtnEditRoom() {
        return btnEditRoom;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Button getBtnAddNewRoom() {
        return btnAddNewRoom;
    }
}
