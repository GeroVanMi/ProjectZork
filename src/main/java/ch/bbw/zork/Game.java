package ch.bbw.zork;

import ch.bbw.zork.commands.*;
import ch.bbw.zork.items.Backpack;
import ch.bbw.zork.items.Item;
import ch.bbw.zork.items.Key;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class Game - the main class of the "Zork" game.
 * <p>
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * <p>
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game.  Users can walk around some scenery.
 * That's all. It should really be extended to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates the
 * commands that the parser returns.
 */

public class Game {

    private Parser parser;
    private CommandRegistry commandRegistry;
    private Room currentRoom;
    private Backpack backpack;
    private Stack<Room> previousRooms;
    private ArrayList<Room> map;
    private boolean finished;
    private Room winningRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand(new CommandHandlerBack());
        commandRegistry.registerCommand(new CommandHandlerBackpack());
        commandRegistry.registerCommand(new CommandHandlerGet());
        commandRegistry.registerCommand(new CommandHandlerGo());
        commandRegistry.registerCommand(new CommandHandlerHelp());
        commandRegistry.registerCommand(new CommandHandlerMap());
        commandRegistry.registerCommand(new CommandHandlerPut());
        commandRegistry.registerCommand(new CommandHandlerQuit());
        commandRegistry.registerCommand(new CommandRestart());

        parser = new Parser(System.in);
        initialize();
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        if(currentRoom == winningRoom){
            finished = true;
            System.out.println("you won");
        }
        this.currentRoom = currentRoom;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Stack<Room> getPreviousRooms() {
        return previousRooms;
    }

    public ArrayList<Room> getMap() {
        return map;
    }

    public void end() {
        finished = true;
    }

    public void initialize() {
        backpack = new Backpack();
        // Create all the rooms and link their exits together.
        Room outside = new Room("outside G block on Peninsula campus", "Campus");
        Room lab = new Room("lab, a lecture theatre in A block", "Lecture theatre");
        Room tavern = new Room("the Seahorse Tavern (the campus pub)", "Seahorse tavern",true);
        Room gblock = new Room("the G Block", "G block");
        Room office = new Room("the computing admin office", "Computing Office");
        Room garden = new Room("the garden outside G Block", "Garden");

        //set winning Room
        winningRoom = tavern;

        // initialise room exits
        garden.setExits(gblock, null, null, null);
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

        Item hammer = new Item("Hammer", "A Hammer", 10.0);
        Item key = new Key(tavern);

        lab.addItem(hammer);
        lab.addItem(key);
    }

    public void restart() {
        initialize();

        System.out.println("Game restarted");
        printWelcome();
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            processCommand(command);
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
     */
    private void processCommand(Command command) {
        CommandHandler handler = commandRegistry.getCommandHandler(command.getCommandWord());
        if (handler == null) {
            System.out.println("I don't know what you mean...");
        } else {
            handler.handle(command, this);
        }
    }
}
