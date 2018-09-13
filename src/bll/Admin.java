package bll;

import bll.dal.DBConnection;

public class Admin extends User {
	
	public Admin(String firstName, String lastName, String phoneNo, String userName, String password, String street,
			String city, String state, int zip, String userType) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhoneNo(phoneNo);
		this.setUserName(userName);
		this.setPassword(password);
		Address addr = new Address(zip, city, street, state);		
		this.setAddress(addr);		
	}
    public int add() throws Exception {
        String sqlQuery = "insert into users(firstname,surname,type,username,password,phone,zip) " +
                "values(\"" + this.getFirstName() + "\",\"" + this.getLastName() + "\",\"" + this.getUserType() + "\",\"" + this.getUserName() + "\",\"" + this.getPassword() +"\",\"" + this.getPhoneNo() +"\",\"" + this.getAddress().getZip() + "\")";
        System.out.println(sqlQuery);
        DBConnection connection = DBConnection.getInstance();
        return connection.update(sqlQuery);
    }
}
