package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bll.dal.DBConnection;

public class Customer extends User {
	private String UserType;
	private List<Booking> bookings = null;

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public Customer(String firstName, String lastName, String phoneNo, String userName, String password, String street,
			String city, String state, int zip, String userType) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip);
		UserType = userType;
	}
	
	public Customer(String firstName, String lastName, String phoneNo, String userName, String password, String street,
			String city, String state, int zip, String userType, int userId) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userId);
		UserType = userType;
	}
	
	public void addBooking(int roomId, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
		Booking booking = new Booking(0, roomId, getUserId(), startTime, endTime, 0);
		booking.save();
		if(bookings != null) bookings.add(booking);
	}
	
	public List<Booking> getBookings() throws SQLException{
		
		if(bookings == null) {
			DBConnection db = DBConnection.getInstance();
			bookings = new ArrayList<Booking>();
			
			ResultSet rs = db.executeQuery("SELECT * FROM bookings WHERE userId = '"+getUserId()+"' ORDER BY userId DESC");
			while(rs.next()) {
				bookings.add( new Booking(rs.getInt("bookId"), rs.getInt("roomId"), rs.getInt("userId"), rs.getTimestamp("startTime").toLocalDateTime(), rs.getTimestamp("endTime").toLocalDateTime(), rs.getInt("status") ) );
			}
		}
		
		return bookings;
	}
	
	public int deleteBooking(Booking booking) throws SQLException {
		getBookings().remove(booking);
		return booking.delete();
	}
}
