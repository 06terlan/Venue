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
        String sqlQuery = "SELECT * FROM users LEFT JOIN addresses ON addresses.zip=users.zip WHERE userName= \'" + userName + "\' AND password= \'" + password + "\'";
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToUser(resultSet);
      
    }
	public static User findByUserName(String userName) throws SQLException {
       // String sqlQuery = "select * from users where username = \'" + userName + "\'" ;
        
		String sqlQuery = "SELECT * FROM users LEFT JOIN addresses ON addresses.zip=users.zip WHERE userName=\'" + userName + "\'";
		
        
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToUser(resultSet);           
       
    }	
	 private static User resultSetToUser(ResultSet resultSet) throws SQLException {
	        User user = null;
	        Address addr = null;
				        
	        while (resultSet.next()) {
	        	if(resultSet.getString("type").equals("Admin"))
	        	{        		
	        		user = new Admin();
	        		addr = new Address();
	        		user.setFirstName(resultSet.getString("firstname"));
	        		user.setFirstName(resultSet.getString("surname"));
	        		user.setUserName(resultSet.getString("username"));
	                user.setUserType(resultSet.getString("type"));
	                user.setPhoneNo(resultSet.getString("phone"));     
	                addr.setCity(resultSet.getString("city"));
	                addr.setState(resultSet.getString("state"));
	                addr.setStreet(resultSet.getString("street"));
	                addr.setZip(Integer.parseInt(resultSet.getString("zip")));
	                user.setAddress(addr);
	                
	        	} 
	        	else
	        	{
	        		if(resultSet.getString("customerType").equals("Prime")) {
	        		 user = new PrimeCustomer(); 
	        		 addr = new Address();
	        		 user.setFirstName(resultSet.getString("firstname"));
	          		 user.setFirstName(resultSet.getString("surname"));
	          		 user.setUserName(resultSet.getString("username"));
	                 user.setUserType(resultSet.getString("type"));
	                 user.setPhoneNo(resultSet.getString("phone"));   
	                 addr.setCity(resultSet.getString("city"));
		                addr.setState(resultSet.getString("state"));
		                addr.setStreet(resultSet.getString("street"));
		                addr.setZip(Integer.parseInt(resultSet.getString("zip")));
		                user.setAddress(addr);
	        		}
	        		else
	        		{
	        			user = new NormalCustomer(); 
	        			addr = new Address();
	           	    	 user.setFirstName(resultSet.getString("firstname"));
	             		 user.setFirstName(resultSet.getString("surname"));
	             		 user.setUserName(resultSet.getString("username"));
	                    user.setUserType(resultSet.getString("type"));
	                    user.setPhoneNo(resultSet.getString("phone")); 
	                    addr.setCity(resultSet.getString("city"));
		                addr.setState(resultSet.getString("state"));
		                addr.setStreet(resultSet.getString("street"));
		                addr.setZip(Integer.parseInt(resultSet.getString("zip")));
		                user.setAddress(addr);
	        			
	        		}
	        	}
	        }    
	        
	        return user;
	    }
}
