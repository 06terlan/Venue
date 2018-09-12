package bll;

public class NormalCustomer extends Customer {
	
	private String CustomerType;

	public NormalCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType) {
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
