package ch.bbw.zork.commands;

import ch.bbw.zork.Game;
import ch.bbw.zork.Room;

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

            boolean canEnter = false;
            if (nextRoom == null) {
                canEnter = false;
                System.out.println("There is no door!");
            } else if (nextRoom.isLocked()) {
                if (game.getBackpack().getItemByName("Key " + nextRoom.getName()) != null) {
                    System.out.println("You have unlocked the door to " + nextRoom.getName());
                    canEnter = true;
                } else {
                    System.out.println("the Room is locked");
                }
            } else {
                canEnter = true;
            }
            if (canEnter) {
                game.getPreviousRooms().push(currentRoom);
                game.setCurrentRoom(nextRoom);
                game.incrementScore();
                System.out.println(nextRoom.longDescription());
            }
        } else {
            System.out.println("Go where?");
        }
    }
}
