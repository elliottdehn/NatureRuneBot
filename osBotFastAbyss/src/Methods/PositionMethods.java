package Methods;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class PositionMethods extends KernalPositionMethods {
    public boolean traversePath(Script script, Position[] path, boolean reversed)
            throws InterruptedException {

        while (!reversed ? script.map.distance(path[path.length - 1]) > 4
                : script.map.distance(path[0]) > 4) {
            this.runManager(script, 50, 10, true);
            Position bestPosition = null;
            int attempts = 0;
            if (!reversed) {
                for (int i = 0; i < path.length; i++) {
                    if (script.map.canReach(path[i])) {
                        MiniMapTileDestination mmtd = new MiniMapTileDestination(
                                script.bot, path[i]);// TODO
                        int distance = this.getDistance(script.myPlayer()
                                .getPosition(), path[i]);
                        if (mmtd != null && distance < 16) {
                            if (distance == 0) {
                                bestPosition = path[i + 1];
                            } else {
                                bestPosition = path[i];
                            }

                            script.log("set best position to " + bestPosition);
                        }
                    }
                }
            } else {
                for (int i = path.length - 1; i > 0; i--) {
                    if (script.map.canReach(path[i])) {
                        MiniMapTileDestination mmtd = new MiniMapTileDestination(
                                script.bot, path[i]);// TODO
                        if (mmtd != null && mmtd.isVisible()) {
                            bestPosition = path[i];
                        }
                    }
                }
            }
            if (bestPosition != null) {
                script.mouse.click(new MiniMapTileDestination(script.bot,
                        bestPosition));
                script.log("Tried to click position " + bestPosition);
                int failsafe = 0;
                while (failsafe < 10
                        && script.myPlayer().getPosition()
                                .distance(bestPosition) > 2) {
                    MethodProvider.sleep(600);
                    failsafe++;
                    if (script.myPlayer().isMoving()) {
                        failsafe = 0;
                    }
                }
                return true;
            } else {
                attempts++;
                if (attempts > 5) {
                    return false;
                }
                script.camera.moveYaw(script.camera.getYawAngle()
                        + MethodProvider.random(60, 80));
            }
        }

        return true;
    }

    public void runManager(Script script, int toggleRunOnAt,
            int toggleRunOffAt, boolean toggleRunOnInCombat) {
        if (script.settings.getRunEnergy() >= toggleRunOnAt
                || (toggleRunOnInCombat ? script.settings.getRunEnergy() > 0
                        && script.myPlayer().isUnderAttack() : false)) {
            script.settings.setRunning(true);
        }
        if (script.settings.getRunEnergy() <= toggleRunOffAt
                && (toggleRunOnInCombat ? !script.myPlayer().isUnderAttack()
                        : true)) {
            script.settings.setRunning(false);
        }
    }

    public int getDistance(Position p1, Position p2) {
        int x1 = p1.getX() - p2.getX();
        int y1 = p1.getY() - p2.getY();
        int distance = (int) Math.sqrt((x1 * x1) + (y1 * y1));
        return distance;
    }

}
