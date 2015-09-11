package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityOutOfBankFixPouches extends Node {

    public SubActivityOutOfBankFixPouches(Script script) {
        super(script);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute() throws InterruptedException {
        this.pouchMethods.repairLunar(this.script, this.c.BANK_ID,
                this.c.BANK_OPTION);

    }

    @Override
    public boolean validate() {
        int[] brokenIds = { 5511, 5513, 5515 };
        boolean isBroken = this.script.inventory.contains(brokenIds);
        boolean astrals = this.script.inventory.getAmount(9075) >= 1;
        boolean cosmics = this.script.inventory.getAmount(564) >= 1;
        boolean airs = this.script.inventory.getAmount(556) >= 2;
        boolean hasRunes = (astrals && cosmics && airs);
        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7937,
                2434, 139, 141, 143, 9075, 564, 556, this.c.foodID };
        boolean isClean = this.script.inventory.isEmptyExcept(allowed);
        this.script.log("Trying to repair pouches: "
                + (isBroken && hasRunes && isClean && !this.script.inventory
                        .isFull()));
        return isBroken && hasRunes && isClean
                && !this.script.inventory.isFull();
    }

}
