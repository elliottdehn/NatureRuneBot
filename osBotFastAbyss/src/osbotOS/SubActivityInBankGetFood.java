package osbotOS;

import org.osbot.rs07.script.Script;

public class SubActivityInBankGetFood extends Node {
    public boolean needToWithdrawFood;

    public SubActivityInBankGetFood(Script script) {
        super(script);
    }

    @Override
    public void execute() {
        this.script.log("tried to withdraw food");
        int[] items = { this.c.foodID };
        int[] amounts = { 2 };
        this.bankMethods.withdrawItemsAmounts(this.script, items, amounts,
                this.c.BANK_ID, this.c.BANK_OPTION);
        this.needToWithdrawFood = false;
    }

    @Override
    public boolean validate() {
        //   int playerMaxHealth = this.script.getSkills()
        //           .getStatic(Skill.HITPOINTS);

        int playerHealth = this.script.myPlayer().getHealth();
        boolean needFood = (playerHealth <= this.c.eatAt);
        this.script.log("Get food!: " + needFood);
        return needFood;
    }

}
