package osbotOS;

import org.osbot.rs07.script.Script;

public class ActivityInAltarStuff extends Node {

    public ActivityInAltarStuff(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {
        this.script.log("entered the crafting runes node");
        if (!this.script.objects.closest(14905).isVisible()
                && this.script.inventory.contains(this.c.UNNOTE_ESS)) {
            this.script.camera.toEntity(this.script.objects.closest(14905));
        } else {
            if (this.script.inventory.contains(this.c.UNNOTE_ESS)) {
                this.pouchMethods.craftRunes(this.script, 14905);
                this.coreMethods.waitTime(3000);
                this.pouchMethods.emptyAllPouches(this.script,
                        this.c.pouchesInUse);
                this.coreMethods.waitTime(100);
                this.pouchMethods.craftRunes(this.script, 14905);
                this.coreMethods.waitTime(3000);
            }

        }

    }

    @Override
    public boolean validate() {
        boolean inAltar = this.positionMethods.isPlayerInBox(this.script, 2396,
                2405, 4833, 4845);
        this.script
                .log("validating make runes: "
                        + (inAltar && this.script.inventory
                                .contains(this.c.UNNOTE_ESS)));
        return inAltar && this.script.inventory.contains(7936)
                && !this.script.inventory.contains(561);
    }

}
