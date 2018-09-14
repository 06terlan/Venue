package ui.venue.controller;

import bll.Building;
import bll.Room;
import bll.service.VenueService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {

    private VenueService venueService;

    @FXML private ObservableList<Building> buildings = FXCollections.observableArrayList();
    @FXML private ObservableList<Room> rooms = FXCollections.observableArrayList();

    @FXML private ListView buildingsListView;
    @FXML private ListView roomsListView;

    @FXML private Button addNewBuilding;
    @FXML private Button addNewRoom;
    @FXML private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venueService = new VenueService();
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO display error dialog
        }
        createBuildingItemInterface();
        buildingsListView.getSelectionModel().select(0);
        buildingsListView.setOnMouseClicked(e -> {
            listAllBuildingRooms();
        });
        listAllBuildingRooms();
    }

    private void createBuildingItemInterface() {
        int index = 0;
        for(Building building : buildings) {
            BorderPane borderPane = createListItemBorderPane("/ui/venue/fxml/addVenue.fxml",building.getBuildingNumber(),building.getAddress().toString(),index++, false);
            buildingsListView.getItems().add(borderPane);
        }
    }

    private void createRoomItemInterface() {
        int index = 0;
        for(Room room : rooms) {
            BorderPane borderPane = createListItemBorderPane("/ui/venue/fxml/addRoom.fxml",room.toString(), String.format("$%.2f",room.getRoomPrice()),index++, true);
            roomsListView.getItems().add(borderPane);
        }
    }

    private FXMLLoader changeScene(String fxmlLocation, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        return loader;
    }

    private BorderPane createListItemBorderPane(String editResourceLocation, String number, String additionalInfo, int index, boolean isRoom) {
        BorderPane borderPane = new BorderPane();
        GridPane labelGridPane = createBuildingInfo(number,additionalInfo);
        GridPane gridPane = createBuildingEditButtonGridPane(index,editResourceLocation,isRoom);
        borderPane.setLeft(labelGridPane);
        borderPane.setRight(gridPane);
        return borderPane;
    }

    private GridPane createBuildingEditButtonGridPane(int index,String editResourceLocation,boolean isRoom) {
        Button btnEdit = createEditButton(index,editResourceLocation,isRoom);
        Button btnRemove = createRemoveButton(index,isRoom);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.add(btnEdit,0,0);
        gridPane.add(btnRemove,1,0);
        return gridPane;
    }

    private Button createRemoveButton(int index,boolean isRoom) {
        Button btnRemove = new Button("remove");
        btnRemove.setOnAction(e -> {
            try {
                removeBuilding(index,isRoom);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return btnRemove;
    }

    @FXML
    private void addNewBuilding(ActionEvent event) throws IOException {
        changeScene("/ui/venue/fxml/addVenue.fxml",(Stage) addNewBuilding.getScene().getWindow());
    }

    @FXML
    private void addNewRoom(ActionEvent event) throws IOException {
        changeScene("/ui/venue/fxml/addRoom.fxml",(Stage) addNewRoom.getScene().getWindow());
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        changeScene("/ui/admin/fxml/admin_main.fxml",(Stage) btnBack.getScene().getWindow());
    }

    private void listAllBuildingRooms() {
        Building selectedBuilding = getSelectedBuilding();
        if(selectedBuilding != null) {
            roomsListView.getItems().clear();
            rooms.clear();
            rooms.addAll(selectedBuilding.getRooms());
            AddRoomController.buildingId = selectedBuilding.getId();
            loadRooms(selectedBuilding.getId());
            createRoomItemInterface();
        }
    }

    private void loadRooms(int buildingId) {
        try {
            buildingId = getSelectedBuildingId(buildingId);
            rooms.addAll(venueService.getAllRooms(buildingId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getSelectedBuildingId(int buildingId) {
        if(buildingId < 0 && buildings.size() > 0) {
            return 0;
        }
        return buildingId;
    }

    private Building getSelectedBuilding() {
        int selectedIndex = buildingsListView.getSelectionModel().getSelectedIndex();
        if(selectedIndex < 0) {
            return null;
        }
        return buildings.get(selectedIndex);
    }

    private void removeBuilding(int index,boolean isRoom) throws Exception {
        if(!isRoom) {
            venueService.removeBuilding(buildings.get(index).getId());
            buildingsListView.getItems().clear();
            buildings.clear();
            loadData();
            createBuildingItemInterface();
        }else {
            Room room = rooms.get(index);
            venueService.removeRoom(room.getRoomId(),room.getRoomType());
            roomsListView.getItems().clear();
            rooms.clear();
            loadRooms(buildings.get(index).getId());
            createRoomItemInterface();
        }
    }

    private Button createEditButton(int index,String editResourceLocation,boolean isRoom) {
        Button btnEdit = new Button("edit");
        btnEdit.setOnAction(e -> {
            try {
                FXMLLoader loader = changeScene(editResourceLocation, (Stage) btnEdit.getScene().getWindow());
                if(!isRoom) {
                    AddVenueController controller = loader.<AddVenueController>getController();
                    controller.setBuilding(buildings.get(index));
                    setBuildingEditFields(controller,buildings.get(index));
                }else {
                    AddRoomController controller = loader.<AddRoomController>getController();
                    controller.setRoom(rooms.get(index));
                    setRoomEditFields(controller,rooms.get(index));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        return btnEdit;
    }

    private void setRoomEditFields(AddRoomController controller, Room room) {
        if(room != null) {
            controller.getRoomNumberTextField().setText(room.getRoomNumber());
            controller.getPriceTextField().setText(String.valueOf(room.getPrice()));
            switch (room.getRoomType()) {
                case HALL:
                    controller.getConcertRdBtn().setSelected(true);
                    break;
                case MEETING:
                    controller.getMeetingRdBtn().setSelected(true);
                    break;
                case CONFERENCE:
                    controller.getConferenceRbBtn().setSelected(true);
                    break;
            }
            controller.getBtnEditRoom().setVisible(true);
            controller.getBtnAddNewRoom().setVisible(false);
        }
    }

    private void setBuildingEditFields(AddVenueController controller,Building building) {
        if(building != null) {
            controller.getBuildingNumberTextField().setText(building.getBuildingNumber());
//            roomNumberTextField.setText(building.getBuildingNumber());
            controller.getCityTextField().setText(building.getAddress().getCity());
            controller.getCityTextField().setEditable(false);
            controller.getStateTextField().setText(building.getAddress().getState());
            controller.getStateTextField().setEditable(false);
            controller.getStreetTextField().setText(building.getAddress().getStreet());
            controller.getStreetTextField().setEditable(false);
            controller.getZipCodeTextField().setText(String.valueOf(building.getAddress().getZip()));
            controller.getZipCodeTextField().setEditable(false);
            controller.getBtnAdd().setVisible(false);
            controller.getBtnEdit().setVisible(true);
        }
    }

    private GridPane createBuildingInfo(String number,String additionalInformation) {
        GridPane labelGridPane = new GridPane();
        labelGridPane.add(createBuildingInfoLabel(number),0,0);
        labelGridPane.add(createAddressInfoLabel(additionalInformation),0,1);
        return labelGridPane;
    }

    private Label createAddressInfoLabel(String address) {
        Label labelAddress = new Label(address);
        labelAddress.setMinWidth(100);
        labelAddress.setMinHeight(10);
        return labelAddress;
    }

    private Label createBuildingInfoLabel(String buildingNumber) {
        Label labelBuildingNumber = createAddressInfoLabel(buildingNumber);
        labelBuildingNumber.setFont(new Font(18));
        return labelBuildingNumber;
    }

    private void loadData() throws Exception {
        buildings.addAll(venueService.getAllBuildings());
    }

}
