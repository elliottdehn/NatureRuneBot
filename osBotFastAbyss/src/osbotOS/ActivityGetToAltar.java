package osbotOS;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class ActivityGetToAltar extends Node {

    public ActivityGetToAltar(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {
        boolean altarIsVisible;

        if (this.script.objects.closest(15610) == null) {
            altarIsVisible = false;
        } else {
            altarIsVisible = this.script.objects.closest(15610).isVisible();
        }

        if (!altarIsVisible) {
            this.positionMethods.traversePath(this.script, this.c.pathToAltar,
                    false);
        } else {
            this.script.objects.closest(15610).interact("Enter");
        }
    }

    @Override
    public boolean validate() {
        Position taiFairyRing = new Position(2801, 3003, 0);
        boolean playerIsKaram = this.script.myPlayer().getPosition()
                .equals(taiFairyRing);
        boolean playerIsOnIsland = this.positionMethods.isPlayerInBox(
                this.script, 2750, 2900, 2950, 3050);

        return (playerIsKaram || playerIsOnIsland);
    }

}
