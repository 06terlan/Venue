package bll.Rule;

import bll.model.Room;
import factory.RoomType;

public class RoomRule  implements Rule<Room> {

    private Room room;
    
    @Override
    public void validate(Room data) throws RuleException {
        room = data;
        notEmptyFields();
        roomTypeSelected();
        numericPrice();
    }

    private void notEmptyFields() throws RuleException {
        if(room.getRoomNumber().trim().isEmpty() ||
                room.getPrice().trim().isEmpty()) {
            throw new RuleException("Room fields must not be empty!");
        }
    }

    private void roomTypeSelected() throws RuleException {
        if(room.getRoomType() == null ||
                !room.getRoomType().equals(RoomType.MEETING) &&
                !room.getRoomType().equals(RoomType.CONFERENCE) &&
                !room.getRoomType().equals(RoomType.HALL)) {
            throw new RuleException("Room type must be selected.");
        }
    }

    private void numericPrice() throws RuleException {
        String price = room.getPrice().trim();
        try {
            Double.parseDouble(price);
        }catch (Exception e) {
            throw new RuleException("Price must be numeric!");
        }
    }
}
