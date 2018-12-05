package ch.bbw.zork.commands;

import ch.bbw.zork.Game;
import ch.bbw.zork.items.Item;

public class CommandHandlerBackpack implements CommandHandler {
    @Override
    public String getCommandWord() {
        return "backpack";
    }

    @Override
    public void handle(Command command, Game game) {
        System.out.println("Your backpack contains:\n");
        for (Item i : game.getBackpack().getInventory()) {
            System.out.println(i.getName());
        }
    }
}
