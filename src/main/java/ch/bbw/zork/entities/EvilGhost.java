package ch.bbw.zork.entities;

import ch.bbw.zork.Game;

public class EvilGhost extends Ghost {

    @Override
    public void respond(Game game) {
        System.out.println("Buh!");
        if(game.getBackpack().getItemByName("Magic Cape") == null) {
            game.killPlayer();
        } else{
            System.out.println("*you have been protected by the magic cape*");
        }
    }
}
