package bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import bll.dal.DBConnection;

public class NormalCustomer extends Customer {

	
	public NormalCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType) {

		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, zip);
		//CustomerType = customerType;
	}
	
	public NormalCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType, int userId) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, userId);
		//CustomerType = customerType;
	}

	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}	

	@Override
    public int add() throws Exception {
        String sqlQuery = "insert into users(firstname,surname,type,username,password,phone,zip,customerType) " +
                "values(\"" + this.getFirstName() + "\",\"" + this.getLastName() + "\",\"" + this.getUserType() + "\",\"" + this.getUserName() + "\",\"" + this.getPassword() +"\",\"" + this.getPhoneNo() +"\",\"" + this.getAddress().getZip() + "\",\"" + this.getCustomerType() + "\")";
        System.out.println(sqlQuery);
        DBConnection connection = DBConnection.getInstance();
        return connection.update(sqlQuery);
    }	
}
