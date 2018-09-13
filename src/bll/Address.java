package bll;

import bll.dal.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

    private int zip;
    private String city;
    private String street;
    private String state;

    public Address() {
    }

    public Address(int zip, String city, String street, String state) {
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return city + " " + state + " " + zip + " " + street;
    }

    public int add() throws Exception {
        String sqlQuery = "select * from addresses where zip="+zip;
        DBConnection connection = DBConnection.getInstance();
        Address address = resultSetToAddress(connection.executeQuery(sqlQuery));
        if(address == null) {
            sqlQuery = "insert into addresses(zip,street,city,state) " +
                    "values(" + zip + ",\"" + street + "\",\"" + city + "\",\"" + state + "\")";
            return connection.update(sqlQuery);
        }
        return address.zip;
    }

    public Address findByZip(int zip) throws SQLException {
        String sqlQuery = "select * from addresses where zip = " + zip;
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToAddress(resultSet);
    }

    private Address resultSetToAddress(ResultSet resultSet) throws SQLException {
        Address address = null;
        while (resultSet.next()) {
            address = new Address();
            address.setCity(resultSet.getString("city"));
            address.setState(resultSet.getString("state"));
            address.setStreet(resultSet.getString("street"));
            address.setZip(resultSet.getInt("zip"));
        }
        return address;
    }
}
