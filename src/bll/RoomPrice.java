package bll;

public enum RoomPrice {

    MEETING(1),
    CONFERENCE(2),
    HALL(4);

    private final int value;

    RoomPrice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
