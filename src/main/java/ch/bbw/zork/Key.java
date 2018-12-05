package ch.bbw.zork;

public class Key extends Item {

    private Room room;

    public Key(Room room) {
        super("Key " + room.getName(), "Key for " + room.getName(), 0.5);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
