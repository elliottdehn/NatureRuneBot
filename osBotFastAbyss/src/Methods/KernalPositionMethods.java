package Methods;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class KernalPositionMethods extends Core {

    public boolean isPlayerInBox(Script script, int xMin, int xMax, int yMin,
            int yMax) {
        boolean isInBox = false;
        Position player = script.myPlayer().getPosition();
        int playerX = player.getX();
        int playerY = player.getY();
        boolean playerInX = (playerX >= xMin) && (playerX <= xMax);
        boolean playerInY = (playerY >= yMin) && (playerY <= yMax);
        if (playerInX && playerInY) {
            isInBox = true;
        }
        return isInBox;
    }

}
