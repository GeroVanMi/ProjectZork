package ch.bbw.zork;

import java.util.ArrayList;

/**
 * Backpack-class that holds a list of items that the player can collect.
 */
public class Backpack {
    private ArrayList<Item> inventory;
    private double limit;

    /**
     * Constructor method that creates an empty backpack.
     */
    public Backpack() {
        this.inventory = new ArrayList<>();
        this.limit = 50.0;
    }

    /**
     * Adds an item to the backpack.
     */
    public void addItem(Item item) {
        if (!inventory.contains(item)) {
            if(limit >= getBackpackWeight()){

                inventory.add(item);
            } else{
                System.out.println("your backpack is full");
            }

        }
    }

    /**
     * Remove item from backpack by name.
     */
    public void removeItem(String name) {
        for(Item item : inventory) {
            if(item.getName().toLowerCase().equals(name.toLowerCase())) {
                inventory.remove(item);
            }
        }
    }

    /**
     * Remove item from backpack.
     */
    public void removeItem(Item item) {
        inventory.remove(item);
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

    public double getBackpackWeight(){
        double fullWeight = 0;
        for(Item i: inventory){
            fullWeight= fullWeight + i.getWeight();
        }
        System.out.println(fullWeight);
        return fullWeight;
    }
}
