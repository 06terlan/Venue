package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.customer.controller.BookingController;

public class Customer extends Application {

		private int custId = 4;
	
        @Override
        public void start(Stage primaryStage) throws Exception{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer/fxml/booking.fxml"));
            
        	BookingController controller = new BookingController();
        	BookingController.customerId = custId;
        	fxmlLoader.setController(controller);
        	
        	Parent root = (Parent)fxmlLoader.load(); 
        	
            primaryStage.setTitle("Main");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }


//        public static void main(String[] args) {
//            launch(args);
//        }
}
