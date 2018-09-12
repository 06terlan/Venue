package ui.admin.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bll.Booking;
import bll.dal.DBConnection;
import bll.service.BookingService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class ControllerBooking implements Initializable{
	
	private DBConnection db;
	List<Booking> books = null;
	
	@FXML
	private TableView<Booking> tableView;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnReject;
	@FXML
	private Button btnBack;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TableColumn customerColumn = new TableColumn("Customer");
		customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		
		TableColumn RoomColumn = new TableColumn("Room");
		RoomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		
		TableColumn startTimeColumn = new TableColumn("Start Time");
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn endTimeColumn = new TableColumn("End Time");
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		TableColumn statusColumn = new TableColumn("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusName"));
		
		tableView.getColumns().addAll(customerColumn, RoomColumn, startTimeColumn, endTimeColumn, statusColumn);
		
		addItemsToView();
	}
	
	private void addItemsToView() {
		try {
			books = getBookings();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initEvents();
		
		
		tableView.getItems().addAll(books);
	}
	
	private void initEvents() {
		tableView.setRowFactory(tv -> {
		    TableRow<Booking> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		             && event.getClickCount() == 1) {

		        	Booking booking = row.getItem();
		            if(booking.getStatus()==2) {
		            	btnAccept.setDisable(false);
		            	btnReject.setDisable(true);
		            }
		            else if(booking.getStatus()==1) {
		            	btnAccept.setDisable(true);
		            	btnReject.setDisable(false);
		            }
		            else{
		            	btnAccept.setDisable(false);
		            	btnReject.setDisable(false);
		            }
		        }
		    });
		    return row ;
		});
		
		btnAccept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Booking selBooking = tableView.getSelectionModel().getSelectedItem();
				if(selBooking != null) {
					selBooking.setStatus(1);
					try {
						selBooking.save();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					tableView.getItems().clear();
					addItemsToView();
				}
			}
		});
		
		btnReject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Booking selBooking = tableView.getSelectionModel().getSelectedItem();
				if(selBooking != null) {
					selBooking.setStatus(2);
					try {
						selBooking.save();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					tableView.getItems().clear();
					addItemsToView();
				}
			}
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage primaryStage = (Stage) btnBack.getScene().getWindow();
				
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/admin_main.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            primaryStage.setTitle("Main");
	            primaryStage.setScene(new Scene(root));
	            primaryStage.show();
			}
		});
	}
	
	private List<Booking> getBookings() throws SQLException{
		db = DBConnection.getInstance();
		List<Booking> books = new ArrayList<Booking>();
		
		ResultSet rs = db.executeQuery("SELECT * FROM bookings ORDER BY userId DESC");
		while(rs.next()) {
			books.add( new Booking(rs.getInt("bookId"), rs.getInt("roomId"), rs.getInt("userId"), rs.getTimestamp("startTime").toLocalDateTime(), rs.getTimestamp("endTime").toLocalDateTime(), rs.getInt("status") ) );
		}
		
		return books;
	}
}
