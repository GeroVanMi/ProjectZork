package ch.bbw.zork.commands;

import ch.bbw.zork.items.Backpack;
import ch.bbw.zork.Game;
import ch.bbw.zork.items.Item;
import ch.bbw.zork.Room;

public class CommandHandlerGet implements CommandHandler {
    @Override
    public String getCommandWord() {
        return "get";
    }

    @Override
    public void handle(Command command, Game game) {
        if (command.hasSecondWord()) {
            Room currentRoom = game.getCurrentRoom();
            Item item = currentRoom.getItem(command.getSecondWord());
            if (item == null) {
                System.out.println("Such an item is not in this room");
            } else {
                Backpack backpack = game.getBackpack();
                backpack.addItem(item);
            }
        } else {
            System.out.println("Get what?");
        }
    }
}
