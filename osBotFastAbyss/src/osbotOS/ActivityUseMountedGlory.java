package osbotOS;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.script.Script;

public class ActivityUseMountedGlory extends Node {

    public ActivityUseMountedGlory(Script script) {
        super(script);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute() throws InterruptedException {
        this.script.log("TRIED TO GO USE MOUNTED GLORY!");
        Position gloryPos = this.script.objects.closest("Amulet of Glory")
                .getPosition();
        Position clickPos = new Position(gloryPos.getX(), gloryPos.getY() + 2,
                0);
        MiniMapTileDestination mmtd = new MiniMapTileDestination(
                this.script.bot, clickPos);// TODO
        this.script.mouse.click(mmtd);
        while (this.positionMethods.getDistance(this.script.myPlayer()
                .getPosition(), clickPos) > 2) {
            this.coreMethods.waitTime(100);
        }
        if (!this.script.objects.closest("Amulet of Glory").isVisible()) {
            this.script.camera.toEntity(this.script.objects
                    .closest("Amulet of Glory"));
        }
        int count = 0;
        while (count < 40) {
            this.script.objects.closest("Amulet of glory")
                    .interact("Edgeville");
            count++;
        }
        while (this.script.myPlayer().isAnimating()) {
            this.coreMethods.wait(100);
        }
        this.coreMethods.waitTime(3000);
        this.positionMethods
                .traversePath(this.script, this.c.pathToBank, false);

    }

    @Override
    public boolean validate() {
        // TODO Auto-generated method stub
        return this.script.objects.closest(4525) != null;
    }

}
