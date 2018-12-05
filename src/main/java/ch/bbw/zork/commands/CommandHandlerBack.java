package ch.bbw.zork.commands;

import ch.bbw.zork.Game;
import ch.bbw.zork.Room;

public class CommandHandlerBack implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "back";
    }

    @Override
    public void handle(Command command, Game game) {
        if (game.getPreviousRooms().isEmpty()) {
            System.out.println("Don't know where to go :(");
        } else {
            Room previous = game.getPreviousRooms().pop();
            game.setCurrentRoom(previous);
            System.out.println(previous.longDescription());
        }
    }
}
