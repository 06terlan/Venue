package bll;

public abstract class Room {

    private String roomNumber;
    private String roomType;
    private Building building;

    public Room() {
    }

    public Room(String roomNumber, String roomType, Building building) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
