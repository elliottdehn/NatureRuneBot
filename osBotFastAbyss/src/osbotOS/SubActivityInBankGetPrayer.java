package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityInBankGetPrayer extends Node {

    public boolean needToWithdrawPrayer;
    public boolean usingPray;

    public SubActivityInBankGetPrayer(Script script) {
        super(script);

    }

    @Override
    public void execute() {
        //4 3 2 1
        int[] prayIDs = { 2434, 139, 141, 143 };
        if (this.script.bank.getAmount(prayIDs[0]) != 0) {
            int[] items = { prayIDs[0] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(prayIDs[1]) != 0) {
            int[] items = { prayIDs[1] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(prayIDs[2]) != 0) {
            int[] items = { prayIDs[2] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(prayIDs[3]) != 0) {
            int[] items = { prayIDs[3] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        }

        this.needToWithdrawPrayer = false;
    }

    @Override
    public boolean validate() {
        int[] prayerIDs = new int[] { 2434, 139, 141, 143 };
        boolean hasPrayerPot = this.script.inventory.contains(prayerIDs);
        boolean needPrayer = !hasPrayerPot && this.c.usingPrayer;
        return needPrayer;

    }
}
