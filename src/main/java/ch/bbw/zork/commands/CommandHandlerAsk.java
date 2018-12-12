package ch.bbw.zork.commands;

import ch.bbw.zork.Game;

public class CommandHandlerAsk implements CommandHandler {
    @Override
    public String getCommandWord() {
        return "ask";
    }

    @Override
    public void handle(Command command, Game game) {
        if (game.getCurrentGhost()!=null){
            game.getCurrentGhost().respond(game);
        }else {
            System.out.println("There is no ghost in this room you idiot.");
        }
    }
}
