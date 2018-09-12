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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerBooking implements Initializable{
	
	private DBConnection db;
	
	@FXML
	private TableView tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("=================================");
		//db = DBConnection.getInstance();
		
		TableColumn customerColumn = new TableColumn("Customer");
		customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		
		TableColumn RoomColumn = new TableColumn("Room");
		RoomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		
		TableColumn startTimeColumn = new TableColumn("Start Time");
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		TableColumn endTimeColumn = new TableColumn("End Time");
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		List<Booking> books = null;
		
		try {
			books = getBookings();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(books==null);
		tableView.getColumns().addAll(customerColumn);
		tableView.getItems().addAll(books);
	}
	
	private List<Booking> getBookings() throws SQLException{
		db = DBConnection.getInstance();
		List<Booking> books = new ArrayList<Booking>();
		
		ResultSet rs = db.executeQuery("SELECT * FROM bookings");
		while(rs.next()) {
			books.add( new Booking(rs.getInt("bookId"), rs.getInt("roomId"), rs.getInt("userId"), rs.getTimestamp("startTime").toLocalDateTime(), rs.getTimestamp("endTime").toLocalDateTime() ) );
		}
		
		return books;
	}
}
