package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bll.dal.DBConnection;

public class Booking {
	private User customer = null;
	private Room room = null;
	private int bookingId;
	private int roomId;
	private int userId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private DateTimeFormatter format;
	private int status = 0;
	
	public Booking(int bookingId, int roomId, int userId, LocalDateTime startTime, LocalDateTime endTime, int status) {
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		
		format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusName() {
		switch (status) {
		case 0:
			return "Pending";
		case 1:
			return "Accepted";
		case 2:
			return "Rejected";
		default:
			return "Pending";
		}
	}
	
	public String getStartTime() {
		return startTime.format(format);
	}
	
	public String getEndTime() {
		return endTime.format(format);
	}
	
	public String getCustomerName() throws SQLException {
		return getCustomer().getFirstName() + " " + getCustomer().getLastName();
	}
	
	public String getRoomNo() {
		return "101";
	}
	
	public Room getRoom() throws SQLException {
		if(room == null) {
			DBConnection db = DBConnection.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM rooms WHERE roomId='"+this.roomId+"'");
			while(rs.next()) {
				if(rs.getString("roomType")=="concert") room = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
				//else customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
			}
		}
		
		return room;
	}
	
	public User getCustomer() throws SQLException {
		if(customer == null) {
			DBConnection db = DBConnection.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM users LEFT JOIN addresses ON addresses.zip=users.zip WHERE userId='"+this.userId+"'");
			while(rs.next()) {
				if(rs.getString("customerType")=="normal") customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
				else customer = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
			}
		}
		
		return customer;
	}
	
	public int save() throws SQLException {
		String sqlQuery = "";
		
		if(bookingId > 0) {
			sqlQuery = "Update bookings set userId='"+userId+"',roomId='"+roomId+"',status='"+status+"',startTime='"+startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+"',endTime='"+endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+"' WHERE bookId='"+bookingId+"'";
		}
		else {
			sqlQuery = "insert into bookings(userId,roomId,startTime,endTime,status) values(\"" + userId + "\",\"" + roomId + "\",\"" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\",\"" + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\",'"+status+"')";
		}
                
        System.out.println(sqlQuery);
        DBConnection connection = DBConnection.getInstance();
        return connection.update(sqlQuery);
	}
}
