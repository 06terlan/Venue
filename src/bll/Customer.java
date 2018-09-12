package bll;

public class Customer extends User {
	private String UserType;

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public Customer(String firstName, String lastName, String phoneNo, String userName, String password, String street,
			String city, String state, String zip, String userType) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip);
		UserType = userType;
	}
	
}
