package ch.bbw.zork.commands;

import ch.bbw.zork.Game;

public class CommandHandlerQuit implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "quit";
    }

    @Override
    public void handle(Command command, Game game) {
        game.end();
    }
}
