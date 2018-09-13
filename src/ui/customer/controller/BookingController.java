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

public class BookingController implements Initializable{
	
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
		//parentStage = (Stage) btnAdd.getScene().getWindow();
				
		initEvents();
	}
	
	private void initEvents() {
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Stage parentStage = (Stage) btnAdd.getScene().getWindow();
				Stage dialog = new Stage();
				Parent root = null;
				
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer/fxml/booking_add.fxml"));

				try {
					root = (Parent)fxmlLoader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	            
	        	BookingAddController controller = fxmlLoader.<BookingAddController>getController();
	        	controller.setCustomerId(customerId);

				dialog.setScene(new Scene(root));
				dialog.initOwner(parentStage);
				dialog.initModality(Modality.APPLICATION_MODAL); 
				dialog.showAndWait();
			}
		});
	}
	
	private Customer getCustomer() throws SQLException {
		if(customer == null) {
			DBConnection db = DBConnection.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM users LEFT JOIN addresses ON addresses.zip=users.zip WHERE userId='"+this.customerId+"'");
			while(rs.next()) {
				if(rs.getString("customerType")=="normal") customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
				else customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
			}
		}
		
		return customer;
	}
}
