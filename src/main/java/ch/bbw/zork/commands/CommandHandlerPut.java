package ch.bbw.zork.commands;

import ch.bbw.zork.items.Backpack;
import ch.bbw.zork.Game;
import ch.bbw.zork.items.Item;

public class CommandHandlerPut implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "put";
    }

    @Override
    public void handle(Command command, Game game) {
        if (command.hasSecondWord()) {
            Backpack backpack = game.getBackpack();
            Item item = backpack.getItemByName(command.getSecondWord());
            if (item == null) {
                System.out.println("You don't have this item");
            } else {
                backpack.removeItem(item);
                game.getCurrentRoom().addItem(item);
            }
        } else {
            System.out.println("Put what?");
        }
    }
}
