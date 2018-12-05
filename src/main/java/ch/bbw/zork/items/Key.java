package ch.bbw.zork.items;

import ch.bbw.zork.Room;

public class Key extends Item {

    private Room room;

    public Key(Room room) {
        super("Key " + room.getName().toLowerCase(), "Key for " + room.getName().toLowerCase(), 0.5);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
