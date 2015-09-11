package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityInBankGetTabs extends Node {

    public boolean needToWithdrawTabs;
    public boolean usingTabs;

    public SubActivityInBankGetTabs(Script script) {
        super(script);
        this.c.usingTabs = this.c.usingTabs;
    }

    @Override
    public void execute() {

        int[] items = { 8013 };
        int[] amounts = { this.c.tabAmount };
        this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                this.c.BANK_ID, this.c.BANK_OPTION);

    }

    @Override
    public boolean validate() {
        boolean hasTabs = this.script.inventory.getAmount("Teleport to house") != 0;
        this.script.log("using tabs?: " + this.usingTabs);
        return !hasTabs && this.c.usingTabs;
    }

}
