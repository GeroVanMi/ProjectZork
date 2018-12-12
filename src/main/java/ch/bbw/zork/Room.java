package ch.bbw.zork;
/*
 * Class Room - a room in an adventure game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    August 2000
 *
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * "Room" represents one location in the scenery of the game.  It is
 * connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 */

import ch.bbw.zork.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String description, name;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> items;
    private boolean locked;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     */
    public Room(String description, String name) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.items = new ArrayList<>();
        this.locked = false;
    }

    public Room(String description, String name, boolean locked) {
        this(description, name);
        this.locked = locked;
    }

    /**
     * Define the exits of this room.  Every direction either leads to
     * another room or is null (no exit there).
     */
    public void setExits(Room north, Room east, Room south, Room west) {
        if (north != null) {
            exits.put("north", north);
        }
        if (east != null) {
            exits.put("east", east);
        }
        if (south != null) {
            exits.put("south", south);
        }
        if (west != null) {
            exits.put("west", west);
        }
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String shortDescription() {
        return description;
    }

    /**
     * Return a long description of this room, on the form:
     * You are in the kitchen.
     * Exits: north west
     */
    public String longDescription() {
        String tempStr = "You are in " + description + ".\n";
        tempStr += "Items in this room:\n";
        for (Item i : items) {
            tempStr += i.getName() + "\n";
        }
        tempStr += exitString();
        return tempStr;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west ".
     */
    private String exitString() {
        String returnString = "Exits:";
        for (String key : exits.keySet()) {
            returnString += " " + key;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room nextRoom(String direction) {
        return (Room) exits.get(direction);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (item != null && !items.contains(item)) {
            this.items.add(item);
        }
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public boolean containsItem(String name) {
        for (Item item : items) {
            if (item.getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }
}




