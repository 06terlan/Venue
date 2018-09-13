package ui.admin.controller;

import bll.Customer;
import bll.User;
import bll.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UserLoginController {
    @FXML private TextField userName;
    @FXML private PasswordField passwordField;
    @FXML private Label UserNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label incorrextUserandPassLabel;
    
    @FXML protected void handleLoginAction(ActionEvent event) {
    	
    	 boolean name = textFieldNotEmpty(userName, UserNameLabel, "User Name is required!");
    	 boolean password = textFieldNotEmpty(passwordField, passwordLabel, "Password is required!");
    	 incorrextUserandPassLabel.setText(null);
    	 if(name && password) {    		
	    	 UserService userService = new UserService();
	    	 User user = userService.findByUserNamePassword(userName.getText(), passwordField.getText());
	    	 if(user != null)
	    	 {
	    		 System.out.println("Redirect to Booking page");
	    	 }
	    	 else
	    	 {	    		
	    		 setErrorMessage(incorrextUserandPassLabel, "Incorrect user name and password.");
	    	 }
    	 }
      
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
