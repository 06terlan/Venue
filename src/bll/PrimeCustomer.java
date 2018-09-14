package bll;

import java.sql.SQLException;
import java.time.LocalDateTime;

import bll.dal.DBConnection;

public class PrimeCustomer extends Customer{
		
	public PrimeCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, zip);
		CustomerType = customerType;
	}
	
	public PrimeCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType, int userId) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, userId);
		CustomerType = customerType;
	}

    public PrimeCustomer() {
        super();
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
         int id = connection.update(sqlQuery);
         System.out.println("-------------------------");
         System.out.println(id);
         return id;
    }	
	
	@Override
	public void addBooking(int roomId, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
		Booking booking = new Booking(0, roomId, getUserId(), startTime, endTime, 1);
		booking.save();
		if(bookings != null) bookings.add(booking);
	}
}
