package ch.bbw.zork;

import org.junit.Before;
import org.junit.Test;

public class BackpackTest {
    Backpack backpack;

    @Before
    public void setup() {
        backpack = new Backpack();
    }

    @Test
    public void testAdding() {
        backpack.addItem(new Item("Pickaxe", "It's a pickaxe"));
    }
}
