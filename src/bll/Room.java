package bll;

import bll.dal.DBConnection;
import factory.RoomFactory;
import factory.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Room {

    private int roomId;
    private String roomNumber;
    private RoomType roomType;
    private double price;
    private Building building;

    public Room(String roomNumber, RoomType roomType, Building building,double price) {
        this.roomType = roomType;
        this.building = building;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public Room(int roomId,String roomNumber, RoomType roomType, Building building,double price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.building = building;
        this.roomNumber = roomNumber;
        this.price = price;
    }


    public Room(Building building) {
        this.building = building;
    }

    public Room(int roomId, String roomNumber, double price,RoomType roomType) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public Room() {

    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public abstract double getRoomPrice();

    public int add() throws SQLException {
        String insertSqlQuery = "insert into rooms(roomNo,buildingId,roomType,price)" +
                "values(\"" + roomNumber + "\"," + building.getId() + "," + roomType.getValue() + ","
                + price +")";
        System.out.println(insertSqlQuery);
        DBConnection connection = DBConnection.getInstance();
        return connection.update(insertSqlQuery);
    }

    public List<Room> viewBuildingRooms() throws SQLException {
        String sqlQuery = "select * from rooms where buildingId=" + building.getId();
        DBConnection connection = DBConnection.getInstance();
        ResultSet resultSet = connection.executeQuery(sqlQuery);
        return resultSetToRooms(resultSet);
    }

    public List<Room> resultSetToRooms(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while(resultSet.next()) {
            int roomId = resultSet.getInt("roomId");
            int buildingId = resultSet.getInt("buildingId");
            Building building = new Building();
            building = building.findById(buildingId);
            RoomType roomType = RoomType.values()[(resultSet.getInt("roomType"))];
            String roomNumber = resultSet.getString("roomNo");
            double price = resultSet.getDouble("price");
            rooms.add(RoomFactory.createRoom(roomId,roomType,building,roomNumber,price));
        }
        return rooms;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room No: " + roomNumber + " \nSuitable for " + roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public void update() throws SQLException {
        String updateQuery = "update rooms set roomNo=\"" + roomNumber + "\"," +
                "price=" + price + ",roomType=" + roomType.getValue() + " where roomId=" + roomId;
        DBConnection connection = DBConnection.getInstance();
        connection.update(updateQuery);
    }

    public void remove(int roomId) throws SQLException {
        String sqlQuery = "delete from rooms where roomId=" + roomId;
        DBConnection connection = DBConnection.getInstance();
        connection.update(sqlQuery);
    }
}
