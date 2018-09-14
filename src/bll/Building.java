package bll;

import bll.dal.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Building {

    private int id;
    private String buildingNumber;
    private Address address;
    private List<Room> rooms = new ArrayList<>();

    public Building() {
    }

    public Building(String buildingNumber, Address address) {
        this.buildingNumber = buildingNumber;
        this.address = address;
//        this.rooms.add(new Room());
    }

    public Building(int id, String buildingNumber) {
        this.id = id;
        this.buildingNumber = buildingNumber;
    }

    public Building(int id) {
        this.id = id;
    }

    public int add() throws Exception {
        String sqlQuery = "insert into buildings(buildingNumber,zip) " +
                "values(\"" + buildingNumber + "\"," + address.getZip() + ")";
        DBConnection connection = DBConnection.getInstance();
        return connection.update(sqlQuery);
    }

    public void update() throws SQLException {
        String updateQuery = "update buildings set buildingNumber=" + buildingNumber + " where buildingId=" + id;
        DBConnection connection = DBConnection.getInstance();
        connection.update(updateQuery);
    }

    public void remove() throws SQLException {
        String deleteQuery = "delete from buildings where buildingId=" + id;
        DBConnection connection = DBConnection.getInstance();
        connection.update(deleteQuery);
    }

    public List<Building> viewAll() throws SQLException {
        String sqlQuery = "select * from buildings";
        DBConnection dbConnection = DBConnection.getInstance();
        ResultSet resultSet = dbConnection.executeQuery(sqlQuery);
        return resultSetToBuildings(resultSet);
    }

    public Building findById(int id) throws SQLException {
        String sqlQuery = "select * from buildings where buildingId=" + id;
        DBConnection dbConnection = DBConnection.getInstance();
        ResultSet resultSet = dbConnection.executeQuery(sqlQuery);
        return resultSetToBuilding(resultSet);
    }

    private Building resultSetToBuilding(ResultSet resultSet) throws SQLException {
        while(resultSet.next()) {
            int id = resultSet.getInt("buildingId");
            String buildingNumber = resultSet.getString("buildingNumber");
            int zip = resultSet.getInt("zip");
            Address address = new Address();
            address = address.findByZip(zip);
            Building building = new Building(buildingNumber,address);
            building.setId(id);
            return building;
        }
        return null;
    }


    private List<Building> resultSetToBuildings(ResultSet resultSet) throws SQLException {
        List<Building> buildings = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt("buildingId");
            String buildingNumber = resultSet.getString("buildingNumber");
            int zip = resultSet.getInt("zip");
            Address address = new Address();
            address = address.findByZip(zip);
            Building building = new Building(buildingNumber,address);
            building.setId(id);
            buildings.add(building);
        }
        return buildings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public Address getAddress() {
        return address;
    }
}
