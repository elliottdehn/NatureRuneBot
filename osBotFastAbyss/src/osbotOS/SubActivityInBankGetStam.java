package osbotOS;

import java.awt.Color;

import org.osbot.rs07.script.Script;

public class SubActivityInBankGetStam extends Node {

    public boolean needToWithdrawStam;

    public SubActivityInBankGetStam(Script script) {
        super(script);
    }

    @Override
    public void execute() {
        this.script.log("tried to withdraw stams");
        int[] stamIDs = { 12625, 12627, 12629, 12631 };
        if (this.script.bank.getAmount(stamIDs[0]) != 0) {
            int[] items = { stamIDs[0] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(stamIDs[1]) != 0) {
            int[] items = { stamIDs[1] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(stamIDs[2]) != 0) {
            int[] items = { stamIDs[2] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        } else if (this.script.bank.getAmount(stamIDs[3]) != 0) {
            int[] items = { stamIDs[3] };
            int[] amounts = { 1 };
            this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                    this.c.BANK_ID, this.c.BANK_OPTION);
        }

        this.needToWithdrawStam = false;
    }

    @Override
    public boolean validate() {
        Color stamColor = new Color(228, 115, 61); //color at 581 141
        boolean needAStam = !this.script.colorPicker.colorAt(581, 141).equals(
                stamColor);
        this.script.log("Need stam?: " + needAStam);
        int[] stamIDs = { 12625, 12627, 12629, 12631 };
        boolean hasStam = this.script.inventory.contains(stamIDs);
        return needAStam && this.c.usingStam && !hasStam;
    }

}
