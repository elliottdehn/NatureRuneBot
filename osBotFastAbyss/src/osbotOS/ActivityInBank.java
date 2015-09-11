package osbotOS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.Script;

import Data.Constants;

public class ActivityInBank extends Node {

    public boolean pouchesFull = false;
    public boolean needToWithdraw = false;
    public static ArrayList<Node> subnodes = new ArrayList<>();

    public boolean needToWithdrawFood;
    public boolean needToWithdrawGlory;
    public boolean needToWithdrawPrayer;
    public boolean needToWithdrawRunes;
    public boolean needToWithdrawTabs;
    public boolean needToWithdrawStam;
    public boolean nearBank;

    public Constants c = new Constants();

    public ActivityInBank(Script script) {

        super(script);

        this.setAllSubValidates();

        Collections.addAll(subnodes, new SubActivityInBankGetTabs(this.script),
                new SubActivityInBankGetRunes(this.script),
                new SubActivityInBankGetGlory(this.script),
                new SubActivityInBankGetFood(this.script),
                new SubActivityInBankGetPrayer(this.script),
                new SubActivityInBankGetStam(this.script)); //add all nodes to the ArrayList

        // TODO Auto-generated constructor stub
    }

    @Override
    public void execute() throws InterruptedException {

        this.bankMethods.openBank(this.script, this.c.BANK_ID,
                this.c.BANK_OPTION);
        while (this.validate()) {
            for (Node node : subnodes) {
                if (node.validate()) {
                    node.execute();
                    this.coreMethods.waitTime(200);
                }
            }
            this.setAllSubValidates();
            this.coreMethods.waitTime(50);
        }
        this.bankMethods.closeBank(this.script);

    }

    public void setAllSubValidates() {
        this.needToWithdrawFood = this.validateFood();
        this.needToWithdrawGlory = this.validateGlory();
        this.needToWithdrawPrayer = this.validatePrayer();
        this.needToWithdrawRunes = this.validateRunes();
        this.needToWithdrawTabs = this.validateTabs();
        this.needToWithdrawStam = this.validateStam();
        this.nearBank = this.validateNearBank();
    }

    @Override
    public boolean validate() {
        this.setAllSubValidates();
        this.needToWithdraw = (this.needToWithdrawFood
                || this.needToWithdrawGlory || this.needToWithdrawPrayer
                || this.needToWithdrawRunes || this.needToWithdrawTabs || this.needToWithdrawStam);
        this.script.log("InBank: "
                + (this.nearBank && this.needToWithdraw && (this.script.objects
                        .closest(13101) == null)));
        return this.nearBank && this.needToWithdraw
                && (this.script.objects.closest(13101) == null);
    }

    public boolean validateFood() {
        int playerHealth = this.script.myPlayer().getHealth();
        boolean needFood = (playerHealth <= this.c.eatAt);
        return needFood;
    }

    public boolean validateGlory() {
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

    public boolean validatePrayer() {
        int[] prayerIDs = new int[] { 2434, 139, 141, 143 };
        boolean hasPrayerPot = this.script.inventory.contains(prayerIDs);
        boolean needPrayer = !hasPrayerPot && this.c.usingPrayer;
        return needPrayer;
    }

    public boolean validateRunes() {
        int[] brokenIds = { 5511, 5513, 5515 };
        boolean isBroken = this.script.inventory.contains(brokenIds);
        boolean astrals = this.script.inventory.getAmount(9075) >= 1;
        boolean cosmics = this.script.inventory.getAmount(564) >= 1;
        boolean airs = this.script.inventory.getAmount(556) >= 2;

        return isBroken && !(astrals && cosmics && airs);
    }

    public boolean validateTabs() {
        boolean hasTabs = this.script.inventory.getAmount("Teleport to house") != 0;
        return !hasTabs && this.c.usingTabs;
    }

    public boolean validateNearBank() {
        boolean inBank = this.positionMethods.isPlayerInBox(this.script, 3095,
                3098, 3494, 3497);
        return inBank;
    }

    public boolean validateStam() {
        Color stamColor = new Color(228, 115, 61); //color at 581 141
        boolean needAStam = !this.script.colorPicker.colorAt(581, 141).equals(
                stamColor);

        int[] stamIDs = { 12625, 12627, 12629, 12631 };
        boolean hasStam = this.script.inventory.contains(stamIDs);
        return needAStam && this.c.usingStam && !hasStam;
    }

}
