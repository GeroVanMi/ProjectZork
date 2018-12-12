package ch.bbw.zork.commands;

import ch.bbw.zork.Game;

public class CommandRestart implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "restart";
    }

    @Override
    public void handle(Command command, Game game) {
        game.restart();
    }
}
