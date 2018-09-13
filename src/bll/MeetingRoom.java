package bll;

import factory.RoomType;

public class MeetingRoom extends Room {

    public MeetingRoom(String roomNumber, Building building, double price) {
        super(roomNumber, RoomType.MEETING,building,price);
    }

    public MeetingRoom(int roomId, String roomNumber, Building building, double price) {
        super(roomId,roomNumber, RoomType.MEETING,building,price);
    }

    public MeetingRoom(Building building) {
        super(building);
    }

    public MeetingRoom(int roomId, String roomNumber, double price) {
        super(roomId,roomNumber,price,RoomType.MEETING);
    }

    public MeetingRoom() {

    }


    @Override
    public double getRoomPrice() {
        return RoomPrice.MEETING.getValue() * getPrice();
    }
}
