package ch.bbw.zork;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTests {

    @Test
    public void testGettersAndSetters() {
        Item item = new Item();
        item.setWeight(-10);

        assertEquals(-10, item.getWeight(), 0.0001);
    }
}
