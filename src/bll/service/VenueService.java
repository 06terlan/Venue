package bll.service;

import bll.Address;
import bll.Building;

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
}
