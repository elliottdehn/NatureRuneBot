package osbotOS;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class ActivityGetToTai extends Node {

    public ActivityGetToTai(Script script) {
        super(script);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute() throws InterruptedException {
        if (!this.script.objects.closest("Fairy ring").isVisible()) {
            this.script.camera.toEntity(this.script.objects
                    .closest("Fairy ring"));
        }
        while (!this.script.getWidgets().isVisible(398)) {
            this.script.objects.closest("Fairy ring").interact("Use");
            this.coreMethods.waitTime(200);
        }
        while (!this.script.getWidgets().isVisible(381, 51)) {
            this.coreMethods.moveClickInBox(this.script, false, 719, 728, 446,
                    454);
            this.coreMethods.waitTime(100);
        }
        Position taiFairyRing = new Position(2801, 3003, 0);
        boolean playerIsKaram = this.script.myPlayer().getPosition()
                .equals(taiFairyRing);
        while (!playerIsKaram) {
            this.script.getWidgets().interact(381, 51, "Use code");
            this.script.getWidgets().interact(398, 26, "Confirm");
            this.coreMethods.waitTime(200);
            playerIsKaram = this.script.myPlayer().getPosition()
                    .equals(taiFairyRing);
        }

    }

    @Override
    public boolean validate() {

        boolean inZanaris = this.positionMethods.isPlayerInBox(this.script,
                2409, 2418, 4429, 4440);
        this.script.log("Try to get to tai?: " + inZanaris);
        return inZanaris;
    }

}
