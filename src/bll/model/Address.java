package bll.model;

public final class Address {

    private final String zip;
    private final String city;
    private final String street;
    private final String state;

    public Address(String zip, String city, String street, String state) {
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }
}
