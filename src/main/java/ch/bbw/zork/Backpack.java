package ch.bbw.zork;

import java.util.ArrayList;

/**
 * Backpack-class that holds a list of items.
 */
public class Backpack {
    private ArrayList<Item> inventory;

    /**
     * Constructor method that creates an empty backpack.
     */
    public Backpack() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Adds an item to the backpack.
     */
    public void addItem(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }

    /**
     * Method to get an item from the backpack by its name.
     * @return Returns item if there is one with the given name or null if none is found.
     */
    public Item getItemByName(String name) {
        for(Item currentItem : inventory) {
            if(currentItem.getName().equals(name)) {
                return currentItem;
            }
        }
        return null;
    }
}
