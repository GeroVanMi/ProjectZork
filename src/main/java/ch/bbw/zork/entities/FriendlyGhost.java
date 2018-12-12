package ch.bbw.zork.entities;

import ch.bbw.zork.Game;
import ch.bbw.zork.Room;

public class FriendlyGhost extends Ghost {
    public FriendlyGhost() {
    }

    @Override
    public void respond(Game game) {
        Room cloakRoom = null;
        for (Room room : game.getMap()) {
            if (room.containsItem("Magic Cape")) {
                cloakRoom = room;
                break;
            }
        }

        System.out.println("Hello traveler...");
        if(game.getBackpack().getItemByName("Magic cape") == null) {
            System.out.println("The invisibility cape may be in the " + cloakRoom.getName());
        }
    }
}
