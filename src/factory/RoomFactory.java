package factory;

import bll.*;

import static factory.RoomType.*;

public class RoomFactory {

    public static Room createRoom(RoomType roomType, Building building,String roomNumber,double price) {
        if (roomType.equals(MEETING)) {
            return new MeetingRoom(roomNumber,building,price);
        }else if (roomType.equals(CONFERENCE)) {
            return new ConferenceRoom(roomNumber,building,price);
        }else if (roomType.equals(HALL)) {
            return new HallRoom(roomNumber,building,price);
        }
        return null;
    }

    public static Room createRoom(int roomId,RoomType roomType, Building building,String roomNumber,double price) {
        if (roomType.equals(MEETING)) {
            return new MeetingRoom(roomId,roomNumber,building,price);
        }else if (roomType.equals(CONFERENCE)) {
            return new ConferenceRoom(roomId,roomNumber,building,price);
        }else if (roomType.equals(HALL)) {
            return new HallRoom(roomId,roomNumber,building,price);
        }
        return null;
    }

    public static Room createRoom(int roomId, String roomNumber, double price, RoomType roomType) {
        switch (roomType) {
            case MEETING:
                return new MeetingRoom(roomId,roomNumber,price);
            case CONFERENCE:
                return new ConferenceRoom(roomId,roomNumber,price);
            case HALL:
                return new HallRoom(roomId,roomNumber,price);
        }
        return null;
    }

    public static Room getRoom(RoomType roomType) {
        switch (roomType) {
            case MEETING:
                return new MeetingRoom();
            case CONFERENCE:
                return new ConferenceRoom();
            case HALL:
                return new HallRoom();
        }
        return null;
    }
}
