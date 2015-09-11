package osbotOS;

import java.util.ArrayList;
import java.util.Collections;

import org.osbot.rs07.script.Script;

public class ActivityOutOfBank extends Node {

    public boolean pouchesFull = false;
    public boolean needToWithdraw = false;
    public boolean needToClean = false;
    public boolean inBankLocation = false;
    public boolean needToDoOut = false;

    public static ArrayList<Node> subnodes = new ArrayList<>();

    private ActivityInBank inBank = new ActivityInBank(this.script);

    public ActivityOutOfBank(Script script) {
        super(script);
        this.setAllValidates();

        Collections.addAll(subnodes, new SubActivityOutOfBankDepositLeftovers(
                this.script), new SubActivityOutOfBankFixPouches(this.script),
                new SubActivityOutOfBankUnnoteEss(this.script)); //add all nodes to the ArrayList
        this.script.log("Constructed out of bank");
    }

    @Override
    public void execute() throws InterruptedException {
        while (!this.script.inventory.isFull()) {
            for (Node node : subnodes) {
                if (node.validate()) {
                    this.script.log("tried to execute subnodes");
                    node.execute();
                    this.coreMethods.waitTime(200);
                }
            }
            this.coreMethods.waitTime(50);
            this.setAllValidates();
        }

    }

    public void setAllValidates() {
        this.needToWithdraw = this.inBank.validate();
        this.needToClean = !this.validateCleanInventoryWithEss();
        this.inBankLocation = this.positionMethods.isPlayerInBox(this.script,
                3095, 3098, 3494, 3497);
    }

    @Override
    public boolean validate() {
        this.setAllValidates();
        this.needToDoOut = !this.needToWithdraw
                && (this.needToClean || !this.script.inventory.isFull());
        this.script.log("Validate out of bank node: " + this.needToDoOut);
        return this.needToDoOut && (this.script.objects.closest(13101) == null)
                && this.inBankLocation; /*
                                         * && this . inBank . nearBank
                                         */
    }

    public boolean validateCleanInventoryWithEss() {
        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7936,
                7937, this.c.foodID };
        boolean isClean = this.script.inventory.isEmptyExcept(allowed);
        return isClean;
    }

    public boolean validateCleanInventory() {
        int[] allowed = { 5509, 5510, 5511, 5512, 5513, 5514, 5515, 8013, 7937,
                2434, 139, 141, 143, this.c.foodID };
        boolean isClean = this.script.inventory.isEmptyExcept(allowed);
        return isClean;
    }
}