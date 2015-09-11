package osbotOS;

import org.osbot.rs07.script.Script;

public class ActivityGetBackToEdgeTab extends Node {

    public ActivityGetBackToEdgeTab(Script script) {
        super(script);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute() throws InterruptedException {

        int slot = this.script.inventory.getSlot(8013);
        while (!this.script.myPlayer().isAnimating() && this.c.usingTabs) {
            this.script.inventory.interact(slot, "Break");
            this.coreMethods.waitTime(250);
        }

        while (this.script.objects.closest(4525) == null) {
            this.coreMethods.waitTime(500);
        }

    }

    @Override
    public boolean validate() {
        this.script.log("Checking in house: "
                + (this.script.objects.closest(13101) != null));
        boolean inAltar = this.positionMethods.isPlayerInBox(this.script, 2396,
                2405, 4833, 4845);
        this.script
        .log("TRYING TO USE TAB: "
                + (inAltar
                        && !this.script.inventory
                        .contains(this.c.UNNOTE_ESS) && this.c.usingTabs));
        return inAltar && !this.script.inventory.contains(this.c.UNNOTE_ESS)
                && this.c.usingTabs;
    }
}
