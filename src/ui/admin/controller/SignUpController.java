package ui.admin.controller;


import java.sql.ResultSet;
import java.sql.SQLException;

import bll.Rule.RuleException;
import bll.Rule.RuleFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import bll.Customer;
import bll.NormalCustomer;
import bll.PrimeCustomer;
import bll.User;
import bll.dal.DBConnection;
import bll.service.UserService;
import bll.service.VenueService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SignUpController {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phoneNo;
    @FXML private TextArea street;
    @FXML private TextField city;
    @FXML private TextField state;
    @FXML private TextField zip;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private CheckBox isPrime;
    @FXML private Label existingUserLabel;
    

    @FXML private Label AllFieldNotEmpty;
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws SQLException {

		bll.model.User user = new bll.model.User(firstName.getText(),lastName.getText(),phoneNo.getText(),userName.getText(),password.getText(),confirmPassword.getText());
		bll.model.Address addr = new bll.model.Address(zip.getText(), city.getText(), street.getText(), state.getText());
		try {
			RuleFactory.getRule(SignUpController.class).validate(user);
			RuleFactory.getRule(bll.model.Address.class).validate(addr);
			
			AllFieldNotEmpty.setText("");
			//Check for existing user
	    	if(isExistingUser())
	    	{	    		
	    	   setErrorMessage(AllFieldNotEmpty, "The username already exists. Please use a different username.");	
	    	}
	    	else
	    	{    				    	
		    	User customer = null;
		    	if(isPrime.isSelected())
		    	{
		    		
		    		 customer = new PrimeCustomer(firstName.getText(), lastName.getText(), phoneNo.getText(), userName.getText(), password.getText(),
		        			street.getText(), city.getText(), state.getText(), Integer.parseInt(zip.getText()), "Customer", "Prime");
		        	    		  
		    	}
		    	else
		    	{
		    		 customer = new NormalCustomer(firstName.getText(), lastName.getText(), phoneNo.getText(), userName.getText(), password.getText(),
		        			street.getText(), city.getText(), state.getText(), Integer.parseInt(zip.getText()), "Customer", "Normal");
		        	      		
		    	}    	
		    	
		    	 UserService userService = new UserService();   	 
		    	  userService.addCustomer(customer);
		    	  System.out.println("Need to redirect to Booking page");
	    	}
	    	//end
			
		} catch (RuleException e) {
			//System.out.println(e.getMessage());
			setErrorMessage(AllFieldNotEmpty,e.getMessage());
			
		}
    }
    @FXML protected void handleClearAction(ActionEvent event) throws SQLException {
    	
    	firstName.setText("");
    	lastName.setText("");
    	phoneNo.setText("");
    	street.setText("");
    	city.setText("");
    	state.setText("");
    	zip.setText("");
    	userName.setText("");
    	password.setText("");
    	confirmPassword.setText("");
    }
    private Boolean isExistingUser()
    {
    	 UserService userService = new UserService();
    	 User user = userService.findByUserName(userName.getText());
    	 
    	 if(user != null)
    		 return true;
    	 
    	 return false;
    	
    }
  
    public static void setErrorMessage(Label l,String sValidationText) {
    	l.setText(sValidationText);
    }
    
}
