package ui.admin.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	
	@FXML
	private Button btnBooking;
	
	@FXML
	public void bookingsSence(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) btnBooking.getScene().getWindow();
		
		Parent root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/admin_booking.fxml"));
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root, 300, 275));
	}
}
