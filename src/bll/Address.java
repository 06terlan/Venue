package bll;

public class Address {
	private String Street;
	private String City;
	private String State;
	private String Zip;
	
	public Address(String street, String city, String state, String zip) {		
		Street = street;
		City = city;
		State = state;
		Zip = zip;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	
	
}
