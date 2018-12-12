package ch.bbw.zork.commands;

import ch.bbw.zork.Game;
import ch.bbw.zork.Room;
import ch.bbw.zork.items.Item;
import ch.bbw.zork.items.Key;

public class CommandHandlerGo implements CommandHandler {

    @Override
    public String getCommandWord() {
        return "go";
    }

    @Override
    public void handle(Command command, Game game) {
        if (command.hasSecondWord()) {
            String direction = command.getSecondWord();

            // Try to leave current room.
            Room currentRoom = game.getCurrentRoom();
            Room nextRoom = currentRoom.nextRoom(direction);

            boolean canEnter = true;
            if (nextRoom == null) {
                canEnter = false;
                System.out.println("There is no door!");
            } else if (nextRoom.isLocked()) {
                canEnter = false;
                for (Item item : game.getBackpack().getInventory()) {
                    if (item instanceof Key) {
                        Key key = (Key) item;
                        if (key.getRoom() == nextRoom) {
                            canEnter = true;
                            break;
                        }
                    }
                }

                if (!canEnter)
                    System.out.println("This Room is locked");
            }

            if (canEnter) {
                game.getPreviousRooms().push(currentRoom);
                if (!game.setCurrentRoom(nextRoom)) {
                    game.incrementScore();
                    System.out.println(nextRoom.longDescription());
                    if (game.getCurrentGhost() != null) {
                        System.out.println("There is a ghost in this room. Spoookie...");
                    }
                }
            }
        } else {
            System.out.println("Go where?");
        }
    }
}
