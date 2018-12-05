package ch.bbw.zork.commands;

import ch.bbw.zork.Game;

public class CommandHandlerHelp implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "help";
    }

    @Override
    public void handle(Command command, Game game) {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at Monash Uni, Peninsula Campus.");
        System.out.println();
        System.out.println("Your command words are:");

        for (CommandHandler handler : game.getCommandRegistry().getCommandHandlers()) {
            System.out.println(handler.getCommandWord());
        }
    }
}
