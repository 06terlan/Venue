package ui.admin.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	
	@FXML
	private Button btnBooking;
	@FXML private Button btnBuilding;

	@FXML
	public void bookingsSence(ActionEvent event) throws IOException {
		changeScene("/ui/admin/fxml/admin_booking.fxml",btnBooking);
	}

	@FXML
	private void viewBuildingList(ActionEvent event) throws IOException {
		changeScene("/ui/venue/fxml/rooms.fxml",btnBuilding);
	}

	private void changeScene(String resourceLocation, Node node) throws IOException {
		Stage primaryStage = (Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(resourceLocation));
		primaryStage.setTitle("Main");
		primaryStage.setScene(new Scene(root));
	}
}
