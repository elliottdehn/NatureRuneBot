package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityOutOfBankUnnoteEss extends Node {
    public ActivityOutOfBank outBank;

    public SubActivityOutOfBankUnnoteEss(Script script) {
        super(script);

    }

    @Override
    public void execute() {
        this.bankMethods.unnoteItem(this.script, this.c.NOTE_ESS,
                this.c.UNNOTE_ESS, this.c.BANK_ID, this.c.BANK_OPTION);
        this.pouchMethods.fillAllPouches(this.script, this.c.pouchesInUse);
        this.bankMethods.unnoteItem(this.script, this.c.NOTE_ESS,
                this.c.UNNOTE_ESS, this.c.BANK_ID, this.c.BANK_OPTION);
    }

    @Override
    public boolean validate() {
        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7937,
                2434, 139, 141, 143, this.c.foodID };
        boolean isClean = this.script.inventory.isEmptyExcept(allowed);
        boolean astrals = this.script.inventory.getAmount(9075) >= 1;
        boolean cosmics = this.script.inventory.getAmount(564) >= 1;
        boolean airs = this.script.inventory.getAmount(556) >= 2;
        boolean hasRunes = (astrals && cosmics && airs);

        return !this.script.inventory.isFull() && isClean && !hasRunes;
    }
}
