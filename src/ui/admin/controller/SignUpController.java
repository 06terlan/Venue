package ui.admin.controller;


import java.sql.ResultSet;
import java.sql.SQLException;

import bll.Rule.RuleException;
import bll.Rule.RuleFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import bll.Customer;
import bll.NormalCustomer;
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
    
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label phoneLabel;
    @FXML private Label streetLabel;
    @FXML private Label cityLabel;
    @FXML private Label stateLabel;
    @FXML private Label zipLabel;
    @FXML private Label UserNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label confirmPasswordLabel;
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws SQLException {

		bll.model.User user = new bll.model.User(firstName.getText(),lastName.getText(),phoneNo.getText(),userName.getText(),password.getText());
		try {
			RuleFactory.getRule(SignUpController.class).validate(user);
		} catch (RuleException e) {
			System.out.println(e.getMessage());
		}
		//to-do list Validation
		boolean fname = textFieldNotEmpty(firstName, firstNameLabel, "First Name is required!");
		boolean lname = textFieldNotEmpty(lastName, lastNameLabel, "Last Name is required!");
		boolean phone = textFieldNotEmpty(phoneNo, phoneLabel, "Phone No. is required!");
		boolean Addrstreet = textAreaNotEmpty(street, streetLabel, "Street is required!");
		boolean addrCity = textFieldNotEmpty(city, cityLabel, "City is required!");
		boolean addrState = textFieldNotEmpty(state, stateLabel, "State is required!");
		boolean addrZip = textFieldNotEmpty(zip,zipLabel, "Zip is required!");
		boolean uName = textFieldNotEmpty(userName,UserNameLabel, "User Name is required!");
		boolean pass = textFieldNotEmpty(password,passwordLabel, "Password is required!");
		boolean cPass = textFieldNotEmpty(confirmPassword,confirmPasswordLabel, "Confirm Password is required!");
    	
    	 if(fname && lname && phone && Addrstreet && addrState && addrCity && addrZip && uName && pass && cPass)
    	 { 
    		 
    	 boolean isInteger = zip.getText().matches("[0-9]+");
    	 if(isInteger) {
    		 
    		    if(password.getText().equals(confirmPassword.getText()))
    		    {
    		    	
			    	//Check for existing user
			    	if(isExistingUser())
			    	{
			    		
			    	   setErrorMessage(existingUserLabel, "The username already exists. Please use a different username.");	
			    	}
			    	else
			    	{    				    	
			    	User customer = null;
			    	if(isPrime.isSelected())
			    	{
			    		
			    		 customer = new NormalCustomer(firstName.getText(), lastName.getText(), phoneNo.getText(), userName.getText(), password.getText(),
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
			    	
			    	
    		    }
    		    else
    		    {
    		    	setErrorMessage(confirmPasswordLabel,"Password and Confirm Password must be same.");
    		    }
    	 }
    	 else
    	 {
    		 setErrorMessage(zipLabel,"Zip code must be number.");
    	 }

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
    public static boolean textAreaNotEmpty(TextArea i)
    {
    	boolean r = false;
    	if(i.getText() != null && !i.getText().isEmpty()) {
    		r = true;
    	}
    	return r;
    	
    }
    public static boolean textAreaNotEmpty(TextArea i,Label l, String sValidationText) {
    	boolean r = true;
    	String c = null;
    	if(!textAreaNotEmpty(i)) {
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
