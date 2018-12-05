package ch.bbw.zork;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * 
 *  This class is the main class of the "Zork" application. Zork is a very
 *  simple, text based adventure game.  Users can walk around some scenery.
 *  That's all. It should really be extended to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  routine.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates the
 *  commands that the parser returns.
 */

public class Game {
	
	private Parser parser;
	private Room currentRoom;
	private Room outside, lab, tavern, gblock, office,garden;
	private Item hammer, key;
	private Backpack backpack;
	private ArrayList<Room> map;
	private Stack<Room> previousRooms;

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		
		parser = new Parser(System.in);
		backpack = new Backpack();
		// Create all the rooms and link their exits together.
		outside = new Room("outside G block on Peninsula campus","Campus");
		lab = new Room("lab, a lecture theatre in A block","Lecture theatre");
		tavern = new Room("the Seahorse Tavern (the campus pub)","Seahorse tavern");
		gblock = new Room("the G Block","G block");
		office = new Room("the computing admin office","Computing Office");
		garden = new Room("the garden outside G Block","Garden");

		// initialise room exits
        garden.setExits(gblock,null,null,null);
		outside.setExits(null, lab, gblock, tavern);
		lab.setExits(null, null, null, outside);
		tavern.setExits(null, outside, null, null);
		gblock.setExits(outside, office, garden, null);
		office.setExits(null, null, null, gblock);

		currentRoom = outside; // start game outside
		previousRooms = new Stack<>();
		
		map = new ArrayList<>();
		map.add(outside);
		map.add(lab);
		map.add(tavern);
		map.add(gblock);
		map.add(office);
		map.add(garden);

		hammer = new Item("Hammer", "A Hammer", 10.0);

		key = new Key(tavern);

		lab.addItem(hammer);
		lab.addItem(key);
	}


	/**
	 *  Main play routine.  Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop.  Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing. Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to Zork!");
		System.out.println("Zork is a simple adventure game.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.longDescription());
	}

	/**
	 * Given a command, process (that is: execute) the command.
	 * If this command ends the game, true is returned, otherwise false is
	 * returned.
	 */
	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
			
			// Game won
			if (currentRoom==tavern) {
				System.out.println("You have arrived in the tavern and won the game! Congratulations!");
				return true;
			}
			
		} else if (commandWord.equals("get")) {
			if (command.hasSecondWord()) {
				try {
                    backpack.addItem(currentRoom.getItem(command.getSecondWord()));
                } catch (NullPointerException npe) {
                    System.out.println("Get what?");
                }
			} else {
                System.out.println("Get what?");
            }
		} else if (commandWord.equals("put")) {
			try {
			    currentRoom.addItem(backpack.getItemByName(command.getSecondWord()));
			    backpack.removeItem(command.getSecondWord());
            } catch (NullPointerException npe) {
                System.out.println("Put what?");
            }
			
		} else if (commandWord.equals("backpack")) {
			System.out.println("Your backpack contains:\n");
			for(Item i:backpack.getInventory()){
				System.out.println(i.getName());
			}
			
		} else if (commandWord.equals("back")) {
			if (!previousRooms.isEmpty()) {
				currentRoom = previousRooms.pop();
				System.out.println(currentRoom.longDescription());
			} else {
				System.out.println("Don't know where to go :(");
			}
			
		} else if (commandWord.equals("quit")) {
			if (command.hasSecondWord()) {
				System.out.println("Quit what?");
			} else {
				return true; // signal that we want to quit
			}
		}
		else if (commandWord.equals("map")){
			System.out.println("Test");
			map.forEach(room -> {
					String out= room.getName();
					if (room == currentRoom){
						out = out + "(current)";
					}
					System.out.println("\n"+ out );
			});
		}
		return false;
	}

	/*
	 * implementations of user commands:
	 */ 

	/**
	 * Print out some help information.
	 * Here we print some stupid, cryptic message and a list of the 
	 * command words.
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at Monash Uni, Peninsula Campus.");
		System.out.println();
		System.out.println("Your command words are:");
		System.out.println(parser.showCommands());
	}

	/** 
	 * Try to go to one direction. If there is an exit, enter the new
	 * room, otherwise print an error message.
	 */
	private void goRoom(Command command) {
		// if there is no second word, we don't know where to go...
		if (!command.hasSecondWord()) {
			System.out.println("Go where?");
		} else {
			
			String direction = command.getSecondWord();
	
			// Try to leave current room.
			Room nextRoom = currentRoom.nextRoom(direction);
	
			if (nextRoom == null)
				System.out.println("There is no door!");

			// Check if room is locked
			else if(nextRoom.isLocked()) {
			    // Check if player has keys
				for(Item currentItem : backpack.getInventory()) {
					if(currentItem instanceof Key) {
						Key key = (Key) currentItem;
						// Check if the current item, which is a key, fits the next room.
						if(key.getRoom().equals(nextRoom)) {
                            System.out.println("You have unlocked " + nextRoom.getName());
							currentRoom = nextRoom;
							System.out.println(currentRoom.longDescription());
							break;
						}
					}
				}
                System.out.println("The room is locked.");
			}
			// Room is not locked, change room
			else {
				previousRooms.push(currentRoom);
				currentRoom = nextRoom;
				System.out.println(currentRoom.longDescription());
				
			}
		}
	}
}
