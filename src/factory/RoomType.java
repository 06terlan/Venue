package factory;

public enum RoomType {

    MEETING(0),
    CONFERENCE(1),
    HALL(2);

    private final int value;

    RoomType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
