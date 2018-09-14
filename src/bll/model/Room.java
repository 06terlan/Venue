package bll.model;

import factory.RoomType;

public final class Room {

    private final String roomNumber;
    private final RoomType roomType;
    private final String price;


    public Room(String roomNumber, RoomType roomType, String price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getPrice() {
        return price;
    }
}
