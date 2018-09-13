package ui.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import bll.Customer;
import bll.NormalCustomer;
import bll.dal.DBConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookingAddController implements Initializable{
	
	private int customerId;
	private Customer customer;
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		getRooms();
				
		//initEvents();
	}
	
	private void getRooms() throws SQLException {
		DBConnection db = DBConnection.getInstance();
		ResultSet rs = db.executeQuery("SELECT * FROM rooms LEFT JOIN buildings ON buildings.buildinId=rooms.buildinId");
		while(rs.next()) {
			if(rs.getString("customerType")=="normal") customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
			else customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
		}
	}
}
