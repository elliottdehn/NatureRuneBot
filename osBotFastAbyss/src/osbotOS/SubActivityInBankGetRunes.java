package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityInBankGetRunes extends Node {

    public SubActivityInBankGetRunes(Script script) {
        super(script);
    }

    @Override
    public void execute() {
        int[] ids = { 9075, 564, 556 };
        int[] amounts = { 1, 1, 2 };
        this.bankMethods.withdrawItemsAmounts(this.script, ids, amounts,
                this.c.BANK_ID, this.c.BANK_OPTION);
    }

    @Override
    public boolean validate() {
        int[] brokenIds = { 5511, 5513, 5515 };
        boolean isBroken = this.script.inventory.contains(brokenIds);
        boolean astrals = this.script.inventory.getAmount(9075) >= 1;
        boolean cosmics = this.script.inventory.getAmount(564) >= 1;
        boolean airs = this.script.inventory.getAmount(556) >= 2;

        this.script.log("Need runes?: "
                + (isBroken && !(astrals && cosmics && airs)));
        return isBroken && !(astrals && cosmics && airs)
                && !this.script.inventory.isFull();
    }

}
