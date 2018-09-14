package ui.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import bll.Booking;
import bll.Customer;
import bll.NormalCustomer;
import bll.dal.DBConnection;
import factory.CustomerFactory;
import factory.CustomerType;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookingController implements Initializable{
	
	public static int customerId;
	private Customer customer = null;
	
	
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<Booking> tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDelete.setDisable(true);
    	btnEdit.setDisable(true);
    	
		//TableColumn customerColumn = new TableColumn("Customer");
		//customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		
		TableColumn RoomColumn = new TableColumn("Room");
		RoomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		
		TableColumn startTimeColumn = new TableColumn("Start Time");
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn endTimeColumn = new TableColumn("End Time");
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		TableColumn statusColumn = new TableColumn("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusName"));
		
		tableView.getColumns().addAll(RoomColumn, startTimeColumn, endTimeColumn, statusColumn);
				
		initEvents();
		addItemsToView();
	}
	
	private void addItemsToView() {
		tableView.getItems().clear();
		List<Booking> books = null;
		
		try {
			books = getCustomer().getBookings();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		initEvents();
		
		
		tableView.getItems().addAll(books);
	}
	
	private void initEvents() {
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Stage parentStage = (Stage) btnAdd.getScene().getWindow();
				Stage dialog = new Stage();
				Parent root = null;
				
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer/fxml/booking_add.fxml"));
	            
	        	BookingAddController controller = new BookingAddController();
	        	fxmlLoader.setController(controller);
	        	
	        	try {
					root = (Parent)fxmlLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}

				dialog.setScene(new Scene(root));
				dialog.initOwner(parentStage);
				dialog.initModality(Modality.APPLICATION_MODAL); 
				dialog.showAndWait();
				
				if(controller.getStatus()) {
					try {
						customer.addBooking(controller.getRoom().getRoomId(), controller.getStartDateTime(), controller.getEndDateTime());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					addItemsToView();
				}
			}
		});
	
		tableView.setRowFactory(tv -> {
		    TableRow<Booking> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		             && event.getClickCount() == 1) {

		        	Booking booking = row.getItem();
		            if(booking.getStatus()==0) {
		            	btnEdit.setDisable(false);
		            	btnDelete.setDisable(false);
		            }
		            else{
		            	btnDelete.setDisable(true);
		            	btnEdit.setDisable(true);
		            }
		        }
		        else {
		        	btnDelete.setDisable(true);
		        	btnEdit.setDisable(true);
		        }
		    });
		    return row ;
		});
		
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Booking booking = tableView.getSelectionModel().getSelectedItem();
				if(booking != null) {
					//
					Stage parentStage = (Stage) btnAdd.getScene().getWindow();
					Stage dialog = new Stage();
					Parent root = null;
					
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer/fxml/booking_add.fxml"));
		            
		        	BookingAddController controller = new BookingAddController(booking);
		        	fxmlLoader.setController(controller);
		        	
		        	try {
						root = (Parent)fxmlLoader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}

					dialog.setScene(new Scene(root));
					dialog.initOwner(parentStage);
					dialog.initModality(Modality.APPLICATION_MODAL); 
					dialog.showAndWait();
					
					if(controller.getStatus()) {
						booking.setStartTime(controller.getStartDateTime());
						booking.setEndTime(controller.getEndDateTime());
						booking.setRoom(controller.getRoom());
						booking.setRoomId(controller.getRoom().getRoomId());
						try {
							booking.save();
						} catch (SQLException e) {
							e.printStackTrace();
						}
							
						addItemsToView();
					}
					//
				}
			}
		});
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Booking booking = tableView.getSelectionModel().getSelectedItem();
				if(booking != null) {
					try {
						customer.deleteBooking(booking);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					addItemsToView();
				}
			}
		});
	}
	
	private Customer getCustomer() throws SQLException {
		if(customer == null) {
			System.out.println(customerId);
			DBConnection db = DBConnection.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM users LEFT JOIN addresses ON addresses.zip=users.zip WHERE userId='"+customerId+"'");
			while(rs.next()) {
				String c = rs.getString("customerType");
				CustomerType custType = CustomerFactory.getCustomerType(c);
				customer = CustomerFactory.createCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"), customerId, custType);
			}
		}
		
		return customer;
	}
}
