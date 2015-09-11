package osbotOS;

import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;

public class SubActivityInBankGetGlory extends Node {
    public boolean needToWithdrawGlory;

    public SubActivityInBankGetGlory(Script script) {
        super(script);

    }

    @Override
    public void execute() {
        this.script.log("Tried to withdraw glory");
        int[] items = { 1712 };
        int[] amounts = { 1 };
        this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                this.c.BANK_ID, this.c.BANK_OPTION);

        this.needToWithdrawGlory = false;
    }

    @Override
    public boolean validate() {
        EquipmentSlot glorySlot = this.script.equipment
                .getForNameThatContains("Amulet of glory");
        boolean gloryIsEmpty = !this.script.equipment
                .isWearingItemThatContains(glorySlot, "Amulet of glory(");
        boolean needAGlory = false;
        if (gloryIsEmpty) {
            needAGlory = true;
        }

        boolean hasGlory = this.script.inventory.contains(1712);
        return (needAGlory && this.c.usingGlory && !hasGlory);
    }

}
