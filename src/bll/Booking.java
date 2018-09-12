package bll;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
	private User customer = null;
	private int bookingId;
	private int roomId;
	private int userId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Booking(int bookingId, int roomId, int userId, LocalDateTime startTime, LocalDateTime endTime) {
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		
		init();
	}
	
	private void init() {
		
	}
	
	public String getCustomerName() {
		
		
		return "asd";
	}
	
	public User getCustomer() {
		
	}
}
