package ch.bbw.zork.items;

import ch.bbw.zork.Room;

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
