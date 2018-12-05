package ch.bbw.zork;

import ch.bbw.zork.items.Backpack;
import ch.bbw.zork.items.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackpackTest {
    Backpack backpack;

    /**
     * Create the backpack.
     */
    @Before
    public void setup() {
        backpack = new Backpack();
    }

    /**
     * Check if a single item can be added to the backpack.
     */
    @Test
    public void testAdding() {
        backpack.addItem(new Item("Pickaxe", "It's a pickaxe", 10.0));
        assertEquals("Expect one item to be in the inventory.", 1, backpack.getAmountOfItems());
    }

    /**
     * Check if an item can only be added once.
     */
    @Test
    public void addSameItem() {
        Item pickaxe = new Item("Pickaxe", "It's a pickaxe", 5.0);
        backpack.addItem(pickaxe);
        backpack.addItem(pickaxe);
        assertEquals("Expect second pickaxe to be deleted.", 1, backpack.getAmountOfItems());
    }

    /**
     * Check if multiple Items can be added to the backpack.
     */
    @Test
    public void addMultipleItems() {
        backpack.addItem(new Item("House", "A House", 15.0));
        backpack.addItem(new Item("Brick", "A Brick", 0.0));
        assertEquals("Expect 2 items to be in the backpack.", 2, backpack.getAmountOfItems());
    }

    /**
     * Test getting an item by name.
     */
    @Test
    public void getItemByName() {
        backpack.addItem(new Item("Axe", "An axe", 0.0));
        Item axe = backpack.getItemByName("Axe");
        assertEquals("Expect the name of the item to be 'Axe'.", "Axe", axe.getName());
    }

    /**
     * Test removing item from backpack.
     */
    @Test
    public void removeItemFromBackpack() {
        Item item = new Item();
        backpack.addItem(item);
        backpack.removeItem(item);
        assertEquals("Expect zero items in the backpack." , 0, backpack.getAmountOfItems());
    }
}
