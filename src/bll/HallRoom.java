package bll;

import factory.RoomType;

public class HallRoom extends Room {

    public HallRoom(String roomNumber, Building building, double price) {
        super(roomNumber, RoomType.HALL,building,price);
    }

    public HallRoom(int roomId, String roomNumber, Building building, double price) {
        super(roomId,roomNumber, RoomType.HALL,building,price);
    }

    public HallRoom(int roomId, String roomNumber, double price) {
        super(roomId,roomNumber,price);
    }

    @Override
    public double getRoomPrice() {
        return RoomPrice.HALL.getValue() * getPrice();
    }
}
