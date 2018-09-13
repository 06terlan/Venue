package bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import bll.dal.DBConnection;

public abstract class User {
	private String FirstName;
	private String LastName;
	private String	PhoneNo;
	private Address Address;
	private String UserName;
	private String Password;	
	private String UserType;

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	
	public abstract int add() throws Exception;
	public static User findByUserNamePassword(String userName,String password) throws SQLException {
        String sqlQuery = "select * from users where username = \'" + userName + "\' AND password= \'" + password + "\'";
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToUser(resultSet);
      
    }
	public static User findByUserName(String userName) throws SQLException {
        String sqlQuery = "select * from users where username = \'" + userName + "\'" ;
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToUser(resultSet);           
       
    }	
	 private static User resultSetToUser(ResultSet resultSet) throws SQLException {
	        User user = null;
	        while (resultSet.next()) {
	        	if(resultSet.getString("type").equals("Admin"))
	        	{        		
	        		user = new Admin(); 
	        		user.setFirstName(resultSet.getString("firstname"));
	        		user.setFirstName(resultSet.getString("surname"));
	        		user.setUserName(resultSet.getString("username"));
	                user.setUserType(resultSet.getString("type"));
	                user.setPhoneNo(resultSet.getString("phone"));                
	        	} 
	        	else
	        	{
	        		if(resultSet.getString("customerType").equals("Prime")) {
	        		 user = new PrimeCustomer(); 
	        		 user.setFirstName(resultSet.getString("firstname"));
	          		 user.setFirstName(resultSet.getString("surname"));
	          		 user.setUserName(resultSet.getString("username"));
	                 user.setUserType(resultSet.getString("type"));
	                 user.setPhoneNo(resultSet.getString("phone"));                  
	        		}
	        		else
	        		{
	        			user = new NormalCustomer(); 
	           	    	 user.setFirstName(resultSet.getString("firstname"));
	             		 user.setFirstName(resultSet.getString("surname"));
	             		 user.setUserName(resultSet.getString("username"));
	                    user.setUserType(resultSet.getString("type"));
	                    user.setPhoneNo(resultSet.getString("phone"));                       
	        			
	        		}
	        	}
	        }    
	        
	        return user;
	    }
}
