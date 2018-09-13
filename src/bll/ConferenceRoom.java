package bll;

import factory.RoomType;

public class ConferenceRoom extends Room{

    public ConferenceRoom(String roomNumber, Building building, double price) {
        super(roomNumber, RoomType.CONFERENCE,building,price);
    }

    public ConferenceRoom(int roomId, String roomNumber, Building building, double price) {
        super(roomId,roomNumber, RoomType.CONFERENCE,building,price);
    }

    public ConferenceRoom(int roomId, String roomNumber, double price) {
        super(roomId,roomNumber,price,RoomType.CONFERENCE);
    }

    public ConferenceRoom() {
    }

    @Override
    public double getRoomPrice() {
        return RoomPrice.CONFERENCE.getValue() * getPrice();
    }
}
