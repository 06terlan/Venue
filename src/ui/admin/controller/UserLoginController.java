package ui.admin.controller;

import java.io.IOException;

import bll.Admin;
import bll.Customer;
import bll.User;
import bll.Rule.RuleException;
import bll.Rule.RuleFactory;
import bll.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.customer.controller.BookingController;

public class UserLoginController {
    @FXML private TextField userName;
    @FXML private PasswordField passwordField;
    @FXML private Label UserNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label incorrextUserandPassLabel;
    @FXML private Button btnSignup;
    
    @FXML protected void handleLoginAction(ActionEvent event) {
    	
    	bll.model.LoginUser lUser = new bll.model.LoginUser(userName.getText(), passwordField.getText());
		try {
			RuleFactory.getRule(UserLoginController.class).validate(lUser);			
			incorrextUserandPassLabel.setText(null);
    			
	    	 UserService userService = new UserService();
	    	 User user = userService.findByUserNamePassword(userName.getText(), passwordField.getText());
	    	 if(user != null)
	    	 {
	    		
	    		 if(user instanceof Admin)
	    		 {
	    			 //System.out.println("Redirect to Admin page"); 
	    			 Stage primaryStage = (Stage) btnSignup.getScene().getWindow();
	    				
	    				Parent root = null;
	    				try {
	    					root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/admin_main.fxml"));
	    				} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    		        primaryStage.setTitle("Admin");
	    		        primaryStage.setScene(new Scene(root, 600, 575));
	    		        primaryStage.show();
	    		 }
	    		 else
	    		 {
	    			// System.out.println("Redirect to Booking page");
	    			 Stage primaryStage = (Stage) btnSignup.getScene().getWindow();
	    				
	    				//Parent root = null;
	    				try {
	    					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer/fxml/booking.fxml"));
	    					BookingController controller = new BookingController();
	    					controller.setCustomerId(user.getUserId());
	    					
	    					fxmlLoader.setController(controller);
	    					
	    					Parent root = (Parent) fxmlLoader.load();
	    					
	    					primaryStage.setTitle("Admin");
		    		        primaryStage.setScene(new Scene(root, 600, 575));
		    		        primaryStage.show(); 
	    				} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    		        
	    		 }	    		 
	    	 }
	    	 else
	    	 {	    		
	    		 setErrorMessage(incorrextUserandPassLabel, "Incorrect user name and password.");
	    	 }
		} catch (RuleException e) {			
			setErrorMessage(incorrextUserandPassLabel,e.getMessage());			
		}
		
      
    }
    @FXML protected void handleSignUpAction(ActionEvent event) { 
    	
    	Stage primaryStage = (Stage) btnSignup.getScene().getWindow();
		
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/signUp.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        primaryStage.setTitle("Sign Up");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }
    public static boolean textFieldNotEmpty(TextField i)
    {
    	boolean r = false;
    	if(i.getText() != null && !i.getText().isEmpty()) {
    		r = true;
    	}
    	return r;
    	
    }
    public static boolean textFieldNotEmpty(TextField i,Label l, String sValidationText) {
    	boolean r = true;
    	String c = null;
    	if(!textFieldNotEmpty(i)) {
    		r = false;
    		c = sValidationText;
    	}
    	l.setText(c);
    	return r;
    }
    public static void setErrorMessage(Label l,String sValidationText) {
    	l.setText(sValidationText);
    }
    

}
