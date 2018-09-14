package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bll.dal.DBConnection;
import factory.CustomerFactory;
import factory.CustomerType;
import factory.RoomFactory;
import factory.RoomType;

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
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime.format(format);
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public String getCustomerName() throws SQLException {
		return getCustomer().getFirstName() + " " + getCustomer().getLastName();
	}
	
	public String getRoomNo() throws SQLException {
		return getRoom().getRoomNumber();
	}
	
	public Room getRoom() throws SQLException {
		if(room == null) {
			DBConnection db = DBConnection.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM rooms WHERE roomId='"+this.roomId+"'");
			while(rs.next()) {
				RoomType roomType = RoomType.values()[(rs.getInt("roomType"))];
				room = RoomFactory.createRoom(rs.getInt("roomId"), rs.getString("roomNo"), rs.getDouble("price"), roomType);
//				if(rs.getString("roomType")=="concert") room = new NormalCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"));
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
				CustomerType custType = CustomerFactory.getCustomerType(rs.getString("customerType"));
				customer = CustomerFactory.createCustomer(rs.getString("firstname"), rs.getString("surname"), rs.getString("phone"), rs.getString("username"), rs.getString("password"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("type"), rs.getString("customerType"), custType);
			}
		}
		
		return customer;
	}
	
		
		
	public int save() throws SQLException {
		String sqlQuery = "";
		int res = 0;
		
		if(bookingId > 0) {
			sqlQuery = "Update bookings set userId='"+userId+"',roomId='"+roomId+"',`status`='"+status+"',startTime='"+startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+"',endTime='"+endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))+"' WHERE bookId='"+bookingId+"'";
			DBConnection connection = DBConnection.getInstance();
			res = connection.update(sqlQuery);
			System.out.println(sqlQuery);
		}
		else {	
			sqlQuery = "insert into bookings(userId,roomId,startTime,endTime,status) values(\"" + userId + "\",\"" + roomId + "\",\"" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\",\"" + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\",'"+status+"')";
			DBConnection connection = DBConnection.getInstance();
	        res = connection.update(sqlQuery);
	        
	        sqlQuery = "SELECT bookId FROM bookings ORDER BY bookId DESC LIMIT 1";
	        ResultSet rs = connection.executeQuery(sqlQuery);
	        if(rs.next()) bookingId = rs.getInt("bookId");
		}
		
		return res;
	}
	
	public int delete() throws SQLException {
		String sqlQuery = "";
		int res = 0;
		
		if(bookingId > 0) {
			sqlQuery = "DELETE FROM bookings WHERE bookId='"+bookingId+"'";
			DBConnection connection = DBConnection.getInstance();
			res = connection.update(sqlQuery);
			System.out.println(sqlQuery);
		}
		
		return res;
	}
}
