package osbotOS;

import org.osbot.rs07.script.Script;

public class ActivityGetToZanaris extends Node {
    public ActivityInBank inBank;

    public ActivityGetToZanaris(Script script) {
        super(script);
        this.inBank = new ActivityInBank(script);
    }

    @Override
    public void execute() throws InterruptedException {
        this.positionMethods
                .traversePath(this.script, this.c.pathToRing, false);
        if (this.script.objects.closest("Fairy ring").isVisible()) {
            this.script.objects.closest("Fairy ring").interact("Use");
        }
    }

    @Override
    public boolean validate() {
        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7936,
                7937, this.c.foodID };
        boolean isInRegion = this.positionMethods.isPlayerInBox(this.script,
                3093, 3150, 3487, 3519);
        boolean isClean = this.script.inventory.isEmptyExcept(allowed);
        boolean doneInBank = !this.inBank.validate() && isInRegion && isClean;
        this.script.log("Validating zanaris node?: " + doneInBank);
        return doneInBank && this.script.inventory.isFull();
    }

}
