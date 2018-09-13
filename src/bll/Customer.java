package bll;

import bll.dal.DBConnection;

public abstract class Customer extends User {
	private String CustomerType;

	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}
	
	public Customer()
	{
	
	}

	public Customer(String firstName, String lastName, String phoneNo, String userName, String password, String street,
			String city, String state, int zip, String userType,String CustomerType) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNo(phoneNo);
		this.setUserName(userName);
		this.setPassword(password);
		Address addr = new Address(zip, city, street, state);		
		this.setAddress(addr);
		this.setUserType(userType);			
		this.CustomerType = CustomerType;
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
