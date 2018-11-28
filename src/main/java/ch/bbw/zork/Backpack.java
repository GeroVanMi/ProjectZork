package ch.bbw.zork;

import java.util.ArrayList;

/**
 * Backpack-class that holds a list of items that the player can collect.
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
     * @return Returns all the items in the backpack as a ArrayList.
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * @return Returns the amount of items that are stored in the inventory.
     */
    public int getAmountOfItems() {
        return inventory.size();
    }

    /**
     * Method to get an item from the backpack by its name.
     * @return Returns item if there is one with the given name or null if none is found.
     */
    public Item getItemByName(String name) {
        for(Item currentItem : inventory) {
            if(currentItem.getName().toLowerCase().equals(name.toLowerCase())) {
                return currentItem;
            }
        }
        return null;
    }
}
