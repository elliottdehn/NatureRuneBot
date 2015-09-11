package osbotOS;

import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;

public class SubActivityOutOfBankDepositLeftovers extends Node {
    public ActivityOutOfBank outBank;

    public SubActivityOutOfBankDepositLeftovers(Script script) {
        super(script);
    }

    @Override
    public void execute() {
        this.script.log("Trying to eat food and drink stam");
        if (this.script.inventory.contains(this.c.foodID)) {
            while (this.script.inventory.contains(this.c.foodID)) {
                this.script.inventory.interact("Eat", this.c.foodID);
            }
        }
        int[] stams = { 12625, 12627, 12629, 12631 };
        if (this.script.inventory.contains(stams)) {

            this.script.inventory.interact("Drink", stams);
        }

        if (this.script.inventory.contains(1712)) {
            int gloryInventorySlot = this.script.inventory.getSlot(1712);
            this.script.inventory.interact(gloryInventorySlot, "Wear");
            EquipmentSlot glorySlot2 = this.script.equipment
                    .getForNameThatContains("Amulet of glory");
            while (glorySlot2 == null) {
                glorySlot2 = this.script.equipment
                        .getForNameThatContains("Amulet of glory");
                this.coreMethods.waitTime(100);
            }
        }

        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7936,
                9075, 564, 556, 7937, this.c.foodID, 1712 };
        this.bankMethods.openBank(this.script, this.c.BANK_ID,
                this.c.BANK_OPTION);
        this.bankMethods.depositAllExcept(this.script, allowed);
        this.bankMethods.closeBank(this.script);

    }

    @Override
    public boolean validate() {
        int[] stams = { 12625, 12627, 12629, 12631, this.c.foodID, 561 };
        boolean hasStams = this.script.inventory.contains(stams);
        this.script.log("Needing to clean inventory? " + hasStams);
        return hasStams;
    }
}
