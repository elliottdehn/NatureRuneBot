package Methods;

import org.osbot.rs07.script.Script;

public class KernalPouchMethods extends BankingMethods {

    //if this is used and we have broken pouches, THIS WILL INFINITELY LOOP (it won't for now because of failsafe)
    //if this is used and one of the pouches is not completely empty, this will infinity loop

    public void attemptToFill(Script script, int id, int waitTime) {
        int count = 0;
        while (count < 2) {
            int pouchSlot = script.inventory.getSlot(id);
            script.inventory.interact(pouchSlot, "Fill");
            this.waitTime(waitTime);
            count++;
        }
    }

    //not guarunteed to be the repaired id, check for broken bag ids before using this method
    public void attemptToEmpty(Script script, int id, int waitTime) {
        int count = 0;
        while (count < 2) {
            int pouchSlot = script.inventory.getSlot(id);
            script.inventory.interact(pouchSlot, "Empty");
            this.waitTime(waitTime);
            count++;
        }
    }
}
