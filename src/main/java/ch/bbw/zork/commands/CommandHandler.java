package ch.bbw.zork.commands;

import ch.bbw.zork.Game;

/**
 * A command handler is an interface to define the callback method which will be called when the command is entered
 * in the console.
 */
public interface CommandHandler {

    /**
     * Returns the identifier (command word) of the handler to which it should listen.
     *
     * @return the command identifier
     */
    String getCommandWord();

    /**
     * This method is invoked when the command was entered in the console.
     *
     * @param command the command which was entered in the console.
     * @param game    the current instance of the game
     */
    void handle(Command command, Game game);
}
