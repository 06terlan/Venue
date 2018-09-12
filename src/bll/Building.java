package bll;

import bll.dal.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Building {

    private String buildingNumber;
    private Address address;
    private List<Room> rooms = new ArrayList<>();

    public Building() {
    }

    public Building(String buildingNumber, Address address) {
        this.buildingNumber = buildingNumber;
        this.address = address;
        this.rooms.add(new Room());
    }

    public int add() throws Exception {
        String sqlQuery = "insert into buildings(buildingNumber,zip) " +
                "values(\"" + buildingNumber + "\"," + address.getZip() + ")";
        DBConnection connection = DBConnection.getInstance();
        return connection.update(sqlQuery);
    }

    public void update() {

    }

    public void remove() {

    }

    public List<Building> viewAll() throws SQLException {
        String sqlQuery = "select * from buildings";
        DBConnection dbConnection = DBConnection.getInstance();
        ResultSet resultSet = dbConnection.executeQuery(sqlQuery);
        return resultSetToBuilding(resultSet);
    }

    private List<Building> resultSetToBuilding(ResultSet resultSet) throws SQLException {
        List<Building> buildings = new ArrayList<>();
        while(resultSet.next()) {
            String buildingNumber = resultSet.getString("buildingNumber");
            int zip = resultSet.getInt("zip");
            Address address = new Address();
            address = address.findByZip(zip);
            buildings.add(new Building(buildingNumber,address));
        }
        return buildings;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public Address getAddress() {
        return address;
    }
}
