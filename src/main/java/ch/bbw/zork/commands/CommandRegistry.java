package ch.bbw.zork.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * This class registers all commands and their handlers.
 */
public class CommandRegistry {

    private List<CommandHandler> commandHandlers;

    public CommandRegistry() {
        commandHandlers = new ArrayList<>();
    }

    public List<CommandHandler> getCommandHandlers() {
        return commandHandlers;
    }

    /**
     * Returns the command handler for a given command. If this handler is not registered, null will be returned
     *
     * @param commandWord the command to search the handler for
     * @return the command handler or null
     */
    public CommandHandler getCommandHandler(String commandWord) {
        for (CommandHandler handler : commandHandlers) {
            if (handler.getCommandWord().equals(commandWord))
                return handler;
        }
        return null;
    }

    /**
     * Checks whether the given command is registered
     *
     * @param commandWord the command to check
     * @return whether this command is registered or not
     */
    public boolean hasCommandHandler(String commandWord) {
        return getCommandHandler(commandWord) != null;
    }

    /**
     * Registers the given command. If a handler for this command is already registered, this handler won't be added
     * and is ignored
     *
     * @param handler the handler which should be registered
     */
    public void registerCommand(CommandHandler handler) {
        if (!hasCommandHandler(handler.getCommandWord()))
            commandHandlers.add(handler);
    }
}
