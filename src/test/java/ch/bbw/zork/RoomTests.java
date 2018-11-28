package ch.bbw.zork;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTests {

    @Test
    public void testBasicRoom() {
        Room room = new Room("trash can");
        room.setExits(null, null, new Room("test"), null);
        assertEquals("trash can", room.shortDescription());
        assertNull(room.nextRoom("east"));
        assertNotNull(room.nextRoom("south"));
    }

    @Test
    public void testRoomItems() {
        Room room = new Room("trash can");

        Item i = new Item();
        i.setName("test");
        room.addItem(i);

        assertEquals(1, room.getItems().size());
        assertNull(room.getItem("trash"));
        assertNotNull(room.getItem("test"));
        assertNull(room.getItem("test"));
    }

}
