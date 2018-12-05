package ch.bbw.zork.commands;

import ch.bbw.zork.Game;
import ch.bbw.zork.Room;

public class CommandHandlerMap implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "map";
    }

    @Override
    public void handle(Command command, Game game) {
        for (Room room : game.getMap()) {
            String out = room.getName();
            if (room == game.getCurrentRoom()) {
                out = out + "(current)";
            }
            System.out.println("\n" + out);
        }
    }
}
