package bll;

public abstract class User {
	private String FirstName;
	private String LastName;
	private String	PhoneNo;
	private Address Address;
	private String UserName;
	private String Password;
	private int userId;
	
	public User(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip) {
		Address address = new Address(zip,city,street,state);
		this.FirstName = firstName;
		LastName = lastName;
		PhoneNo = phoneNo;
		Address = address;
		UserName = userName;
		Password = password;		
	}
	
	public User(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, int userId) {
		Address address = new Address(zip,city,street,state);
		this.FirstName = firstName;
		LastName = lastName;
		PhoneNo = phoneNo;
		Address = address;
		UserName = userName;
		Password = password;
		this.userId = userId;
	}
	
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
	public int getUserId() {
		return userId;
	}
	public abstract String getUserType();
	
}
