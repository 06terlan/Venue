package ui.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bll.Booking;
import bll.Customer;
import bll.NormalCustomer;
import bll.Room;
import bll.dal.DBConnection;
import factory.CustomerFactory;
import factory.CustomerType;
import factory.RoomFactory;
import factory.RoomType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookingAddController extends JFrame implements Initializable{
	
	private boolean status = false;
	private Room room;
	private LocalDate startDate;;
	private LocalDate endDate;
	private String startTime;
	private String endTime;
	private Booking booking = null;
	
	public BookingAddController() {}
	
	public BookingAddController(Booking booking) { this.booking = booking; }
	
	@FXML
	private ComboBox<Room> cmbRoom;
	@FXML
	private DatePicker dateStartDate;
	@FXML
	private TextField tbStartTime;
	@FXML
	private DatePicker dateEndDate;
	@FXML
	private TextField tbEndTime;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
<<<<<<< HEAD
		try {
			getRooms();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(booking != null) {
				tbStartTime.setText(booking.getStartTime().split(" ")[1]);
				tbEndTime.setText(booking.getEndTime().split(" ")[1]);
				dateStartDate.setValue(LocalDate.parse(booking.getStartTime().split(" ")[0], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
				dateEndDate.setValue(LocalDate.parse(booking.getEndTime().split(" ")[0], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
				try {
					cmbRoom.getSelectionModel().select(booking.getRoom());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
=======
//		getRooms();
>>>>>>> 6f00c4503330798c649e3518da83145facd90071
				
		initEvents();
	}
	
	private void initEvents() {
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage win = (Stage)btnClose.getScene().getWindow();
				status = false;
				win.close();
			}
		});
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage win = (Stage)btnClose.getScene().getWindow();
				room = cmbRoom.getSelectionModel().getSelectedItem();
				startDate = dateStartDate.getValue();
				endDate = dateEndDate.getValue();
				startTime = tbStartTime.getText();
				endTime = tbEndTime.getText();
				
				if(room == null) {
					JOptionPane.showMessageDialog(BookingAddController.this, "Room must be selected!");
					return;
				}
				else if(startDate == null || !startDate.toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
					JOptionPane.showMessageDialog(BookingAddController.this, "Stat date is not correct!");
					return;
				}
				else if(endDate == null || !endDate.toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
					JOptionPane.showMessageDialog(BookingAddController.this, "End date is not correct!");
					return;
				}
				else if(startTime.isEmpty() || !startTime.matches("\\d{2}:\\d{2}")) {
					JOptionPane.showMessageDialog(BookingAddController.this, "Start time is not correct!");
					return;
				}
				else if(endTime.isEmpty() || !endTime.matches("\\d{2}:\\d{2}")) {
					JOptionPane.showMessageDialog(BookingAddController.this, "End time is not correct!");
					return;
				}
				
				status = true;
				win.close();
			}
		});
	}
	
	public Room getRoom() {
		return room;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public LocalDateTime getStartDateTime() {
		return LocalDateTime.parse(startDate + " " + endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	public LocalDateTime getEndDateTime() {
		return LocalDateTime.parse(endDate + " " + endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	public boolean getStatus() {
		return status;
	}
	
	private void getRooms() throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ResultSet rs = db.executeQuery("SELECT * FROM rooms LEFT JOIN buildings ON buildings.buildingId=rooms.buildingId");
		while(rs.next()) {
			RoomType type = RoomType.values()[rs.getInt("roomType")];
			Room rm = RoomFactory.createRoom(rs.getInt("roomId"), rs.getString("roomNo"), rs.getDouble("price"), type);
			cmbRoom.getItems().add(rm);
		}
	}

}
