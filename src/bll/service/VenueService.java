package bll.service;

import bll.Address;
import bll.Building;
import bll.MeetingRoom;
import bll.Room;
import factory.RoomFactory;
import factory.RoomType;

import java.sql.SQLException;
import java.util.List;

public class VenueService {

    public void addBuilding(String buildingNumber, String roomNumber, String city, String state, String street, int zipCode) {
        try {
            Address address = new Address(zipCode,city,street,state);
            int addressId = address.add();
            Building building = new Building(buildingNumber,address);
            building.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Building> getAllBuildings() throws Exception {
        Building building = new Building();
        try {
            return building.viewAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error: Cannot get building list");
        }
    }

    public void editBuilding(int id, String buildingNumber) throws Exception {
        Building building = new Building(id,buildingNumber);
        try {
            building.update();
        } catch (SQLException e) {
            throw new Exception("Error: Cannot update building. please try again.");
        }
    }

    public void removeBuilding(int id) throws SQLException {
        Building building = new Building(id);
        building.remove();
    }

    public void addRoom(int buildingId, String roomNumber, RoomType roomType,double price) throws Exception {
        Building building = new Building();
        try {
            building = building.findById(buildingId);
            Room room = RoomFactory.createRoom(roomType,building,roomNumber,price);
            room.add();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error: Cannot found building with the specified id.");
        }
    }

    public List<Room> getAllRooms(int buildingId) throws SQLException {
        Room room = new MeetingRoom(new Building(buildingId));
        return room.viewBuildingRooms();
    }

    public void editRoom(int roomId, String roomNumber, double price,RoomType roomType) throws Exception {
        Room room = RoomFactory.createRoom(roomId,roomNumber,price,roomType);
        try {
            room.update();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while updating room.");
        }
    }
}
