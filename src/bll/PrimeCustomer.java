package bll;

public class PrimeCustomer extends Customer{
	private String CustomerType;
	
	public PrimeCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, String zip, String userType, String customerType) {
		super(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType);
		CustomerType = customerType;
	}

	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}
	
}
