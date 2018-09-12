package ui.admin.controller;


import java.sql.ResultSet;
import java.sql.SQLException;

import bll.NormalCustomer;
import bll.dal.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    
    @FXML protected void handleSignUpAction(ActionEvent event) throws SQLException {
    	
    	//to-do list Validation
    	
    	NormalCustomer nCustomer = new NormalCustomer(firstName.getText(), lastName.getText(), phoneNo.getText(), userName.getText(), password.getText(),
    			street.getText(), city.getText(), state.getText(), zip.getText(), "Customer", "Normal");
    	
    	String sql = "select * from addresses";
    	DBConnection connection = DBConnection.getInstance();
    	ResultSet resultSet = connection.executeQuery(sql);
    	while(resultSet.next())
    	{
    		System.out.println(resultSet.getString(1));
    		System.out.println(resultSet.getString(2));
    	}
    	System.out.println("shit");  
    }
}
