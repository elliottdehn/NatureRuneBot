package osbotOS;

import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;

public class ActivityGetBackToEdgeGlory extends Node {

    public ActivityGetBackToEdgeGlory(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {

        while (!this.script.myPlayer().isAnimating() && this.c.usingGlory) {
            this.script.equipment.openTab();
            EquipmentSlot neckSlot = this.script.equipment
                    .getForNameThatContains("Amulet of glory");

            while (!this.script.myPlayer().isAnimating()) {
                this.script.equipment.interact(neckSlot, "Edgeville");
                this.coreMethods.waitTime(100);
            }
        }
        while (this.script.myPlayer().isAnimating()) {
            this.coreMethods.waitTime(100);
        }
        if (this.c.usingGlory) {
            this.positionMethods.traversePath(this.script, this.c.pathToBank,
                    false);
        }

    }

    @Override
    public boolean validate() {
        boolean inAltar = this.positionMethods.isPlayerInBox(this.script, 2396,
                2405, 4833, 4845);
        this.script
                .log("TRYING TO TELEPORT TO GLORY: "
                        + (inAltar
                                && !this.script.inventory
                                        .contains(this.c.UNNOTE_ESS) && this.c.usingGlory));
        this.script.log("Don't have ess?:"
                + !this.script.inventory.contains(this.c.UNNOTE_ESS));
        return inAltar && !this.script.inventory.contains(this.c.UNNOTE_ESS)
                && this.c.usingGlory;
    }

}
