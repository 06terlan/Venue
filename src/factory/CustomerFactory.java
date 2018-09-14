package factory;

import bll.Customer;
import bll.NormalCustomer;
import bll.PrimeCustomer;

public class CustomerFactory {
	public static Customer createCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType, CustomerType custType) {
		
		switch (custType) {
			case NORMAL:
				return new NormalCustomer(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, customerType);
			case PRIME:
				return new PrimeCustomer(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, customerType);
		default:
			return null;
		}
	}
	
	public static Customer createCustomer(String firstName, String lastName, String phoneNo, String userName, String password,
			String street, String city, String state, int zip, String userType, String customerType, int userId, CustomerType custType) {
		
		switch (custType) {
			case NORMAL:
				return new NormalCustomer(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, customerType, userId);
			case PRIME:
				return new PrimeCustomer(firstName, lastName, phoneNo, userName, password, street, city, state, zip, userType, customerType, userId);
		default:
			return null;
		}
	}
	
	public static CustomerType getCustomerType(String customerType) {
		switch (customerType) {
		case "normal":
			return CustomerType.NORMAL;
		case "prime":
			return CustomerType.PRIME;	
		default:
			return null;
		}
	}
}
