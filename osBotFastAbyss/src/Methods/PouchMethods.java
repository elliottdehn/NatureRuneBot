package Methods;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Spells.LunarSpells;
import org.osbot.rs07.script.Script;

public class PouchMethods extends KernalPouchMethods {

    private int UNNOTE_ESS = 7936;
    private int NOTE_ESS = 7937;

    private int SMALL_REPAIRED_ID = 5509;
    private int MED_REPAIRED_ID = 5510;
    private int LARGE_REPAIRED_ID = 5512;
    private int GIANT_REPAIRED_ID = 5514;
    private int MED_DAMAGED_ID = 5511;
    private int LARGE_DAMAGED_ID = 5513;
    private int GIANT_DAMAGED_ID = 5515;

    private int ALTAR_ID = 14901;
    private String BANK_NAME = "Bank chest";
    private String BANK_OPTION = "Use";

    public void fillAllPouches(Script script, boolean[] pouchesInUse) {
        assert pouchesInUse.length == 4 : "More than 4 pouches in existence";
        //finds how many ess we need to put in the pouch
        //pouch order is [small, medium, large, giant]
        int expectedAmount = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0 && pouchesInUse[i]) {
                expectedAmount += 3;
            } else if (i == 1 && pouchesInUse[i]) {
                expectedAmount += 6;
            } else if (i == 2 && pouchesInUse[i]) {
                expectedAmount += 9;
            } else if (i == 3 && pouchesInUse[i]) {
                expectedAmount += 12;
            }
        }

        int originalUnnotedEss = (int) script.inventory
                .getAmount(this.UNNOTE_ESS);
        int pouchedEss = 0;
        if (expectedAmount <= originalUnnotedEss) {
            //the loop for situations where we don't have to unnote ess in the middle of filling
            //gaurunteed to fill the pouches ( will loop trying to fill until expected is reached)
            int attemptsFailsafe = 0;
            while (pouchedEss < expectedAmount && attemptsFailsafe < 4) {
                for (int i = 0; i < 4; i++) {
                    if (pouchesInUse[i]) {
                        if (i == 0) {
                            this.attemptToFill(script, this.SMALL_REPAIRED_ID,
                                    50);
                        } else if (i == 1) {
                            this.attemptToFill(script, this.MED_REPAIRED_ID, 50);
                        } else if (i == 2) {
                            this.attemptToFill(script, this.LARGE_REPAIRED_ID,
                                    50);
                        } else if (i == 3) {
                            this.attemptToFill(script, this.GIANT_REPAIRED_ID,
                                    50);
                        }
                    }
                }

                this.waitTime(500);
                int currentUnnotedEss = (int) script.inventory
                        .getAmount(this.UNNOTE_ESS);
                pouchedEss = originalUnnotedEss - currentUnnotedEss;
                attemptsFailsafe++;
            }
        } else {
            //NOTE: this method is not gaurunteed to fill every pouch (assumes we successfully fill the pouch in the interest of saving time)
            //    int attemptsFailsafe = 0;
            //    while (pouchedEss < expectedAmount && attemptsFailsafe < 4) {
            for (int i = 0; i < 4; i++) {
                if (pouchesInUse[i]) {
                    if (i == 0
                            && script.inventory.getAmount(this.UNNOTE_ESS) >= 3) {
                        this.attemptToFill(script, this.SMALL_REPAIRED_ID, 50);
                    } else if (script.inventory.getAmount(this.UNNOTE_ESS) < 3) {
                        RS2Object closestBank = script.objects
                                .closest(this.BANK_NAME);
                        this.unnoteItem(script, this.NOTE_ESS, this.UNNOTE_ESS,
                                closestBank.getId(), this.BANK_OPTION);
                        this.attemptToFill(script, this.SMALL_REPAIRED_ID, 50);
                    }
                    if (i == 1
                            && script.inventory.getAmount(this.UNNOTE_ESS) >= 6) {
                        this.attemptToFill(script, this.MED_REPAIRED_ID, 50);
                    } else if (script.inventory.getAmount(this.UNNOTE_ESS) < 6) {
                        RS2Object closestBank = script.objects
                                .closest(this.BANK_NAME);
                        this.unnoteItem(script, this.NOTE_ESS, this.UNNOTE_ESS,
                                closestBank.getId(), this.BANK_OPTION);
                        this.attemptToFill(script, this.MED_REPAIRED_ID, 50);
                    }
                    if (i == 2
                            && script.inventory.getAmount(this.UNNOTE_ESS) >= 9) {
                        this.attemptToFill(script, this.LARGE_REPAIRED_ID, 50);
                    } else if (script.inventory.getAmount(this.NOTE_ESS) < 9) {
                        RS2Object closestBank = script.objects
                                .closest(this.BANK_NAME);
                        this.unnoteItem(script, this.NOTE_ESS, this.UNNOTE_ESS,
                                closestBank.getId(), this.BANK_OPTION);
                        this.attemptToFill(script, this.LARGE_REPAIRED_ID, 50);
                    }
                    if (i == 3
                            && script.inventory.getAmount(this.UNNOTE_ESS) >= 12) {
                        this.attemptToFill(script, this.GIANT_REPAIRED_ID, 50);
                    } else if (script.inventory.getAmount(this.UNNOTE_ESS) < 12) {
                        RS2Object closestBank = script.objects
                                .closest(this.BANK_NAME);
                        this.unnoteItem(script, this.NOTE_ESS, this.UNNOTE_ESS,
                                closestBank.getId(), this.BANK_OPTION);
                        this.attemptToFill(script, this.GIANT_REPAIRED_ID, 50);
                    }
                }
            }
            //          int currentUnnotedEss = (int) script.inventory
            //                  .getAmount(this.UNNOTE_ESS);
            //          this.waitTime(200);
            //           pouchedEss = originalUnnotedEss - currentUnnotedEss;
            //           attemptsFailsafe++;
            //       }
        }
    }

    public void emptyAllPouches(Script script, boolean[] pouchesInUse) {
        assert pouchesInUse.length == 4 : "More than 4 pouches in existence";
        //finds how many ess we need to put in the pouch
        //pouch order is [small, medium, large, giant]
        int expectedAmount = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0 && pouchesInUse[i]) {
                expectedAmount += 3;
            } else if (i == 1 && pouchesInUse[i]) {
                expectedAmount += 6;
            } else if (i == 2 && pouchesInUse[i]) {
                expectedAmount += 9;
            } else if (i == 3 && pouchesInUse[i]) {
                expectedAmount += 12;
            }
        }

        int emptySlots = script.inventory.getEmptySlots();
        int releasedEss = 0;
        if (expectedAmount <= emptySlots) {
            int attemptsFailsafe = 0;
            while (releasedEss < expectedAmount && attemptsFailsafe < 4) {
                for (int i = 0; i < 4; i++) {
                    if (pouchesInUse[i]) {
                        if (i == 0) {
                            this.attemptToEmpty(script, this.SMALL_REPAIRED_ID,
                                    50);
                        } else if (i == 1) {
                            this.attemptToEmptyAtAltar(script,
                                    this.MED_REPAIRED_ID, this.MED_DAMAGED_ID);
                        } else if (i == 2) {
                            this.attemptToEmptyAtAltar(script,
                                    this.LARGE_REPAIRED_ID,
                                    this.LARGE_DAMAGED_ID);
                        } else if (i == 3) {
                            this.attemptToEmptyAtAltar(script,
                                    this.GIANT_REPAIRED_ID,
                                    this.GIANT_DAMAGED_ID);
                        }
                    }
                }
                this.waitTime(500);
                releasedEss = (int) script.inventory.getAmount(this.UNNOTE_ESS);
                attemptsFailsafe++;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (pouchesInUse[i]) {
                    emptySlots = script.inventory.getEmptySlots();
                    if (i == 0 && emptySlots >= 3) {
                        this.attemptToEmpty(script, this.SMALL_REPAIRED_ID, 50);
                    } else if (emptySlots < 3) {
                        this.craftRunes(script, this.ALTAR_ID);
                        this.attemptToEmpty(script, this.SMALL_REPAIRED_ID, 50);
                    }
                    emptySlots = script.inventory.getEmptySlots();
                    if (i == 1 && emptySlots >= 6) {
                        this.attemptToEmptyAtAltar(script,
                                this.MED_REPAIRED_ID, this.MED_DAMAGED_ID);
                    } else if (script.inventory.getEmptySlots() < 6) {
                        this.craftRunes(script, this.ALTAR_ID);
                        this.attemptToEmptyAtAltar(script,
                                this.MED_REPAIRED_ID, this.MED_DAMAGED_ID);
                    }
                    emptySlots = script.inventory.getEmptySlots();
                    if (i == 2 && emptySlots >= 9) {
                        this.attemptToEmptyAtAltar(script,
                                this.LARGE_REPAIRED_ID, this.LARGE_DAMAGED_ID);
                    } else if (emptySlots < 9) {
                        this.craftRunes(script, this.ALTAR_ID);
                        this.attemptToEmptyAtAltar(script,
                                this.LARGE_REPAIRED_ID, this.LARGE_DAMAGED_ID);
                    }
                    emptySlots = script.inventory.getEmptySlots();
                    if (i == 3 && emptySlots >= 12) {
                        this.attemptToEmptyAtAltar(script,
                                this.GIANT_REPAIRED_ID, this.GIANT_DAMAGED_ID);
                    } else if (emptySlots < 12) {
                        this.craftRunes(script, this.ALTAR_ID);
                        this.attemptToEmptyAtAltar(script,
                                this.GIANT_REPAIRED_ID, this.GIANT_DAMAGED_ID);
                    }
                }
            }
        }
    }

    public void repairLunar(Script script, int bankId, String bankOption)
            throws InterruptedException {
        /*
         * int bank = script.objects.closest(this.BANK_NAME).getId();
         * this.openBank(script, bank, this.BANK_OPTION); int[] ids = { 9075,
         * 564, 556 }; int[] amounts = { 1, 1, 2 };
         * this.withdrawItemsAmounts(script, ids, amounts, bankId, bankOption);
         * this.closeBank(script);
         */

        while (script.getMagic().canCast(LunarSpells.NPC_CONTACT)
                && !script.getWidgets().isVisible(75, 12)) {
            script.getMagic().castSpell(LunarSpells.NPC_CONTACT);
            this.waitTime(250);
        }

        this.waitTime(1000);
        if (script.getWidgets().isVisible(75, 12)) {
            script.getWidgets().interact(75, 12, "Dark Mage");
            this.waitTime(1000);
        }

        while (!script.getWidgets().isVisible(231, 3)) {
            this.waitTime(20);
        }
        if (script.getWidgets().isVisible(231, 2)) {
            script.getWidgets().interact(231, 2, "Continue");
            this.waitTime(1000);
        }
        //    if (script.getWidgets().isVisible(219)) {
        this.moveClickInBox(script, false, 127, 303, 445, 455);
        this.waitTime(1000);
        script.mouse.click(false);

        //TODO
    }

    public void repairAbyss(Script script) {
        //TODO
    }

    private void attemptToEmptyAtAltar(Script script, int repairedID,
            int damagedID) {
        boolean isDamaged = !(script.inventory.getAmount(repairedID) == 1);
        if (!isDamaged) {
            this.attemptToEmpty(script, repairedID, 50);
        } else {
            this.attemptToEmpty(script, damagedID, 50);
        }
    }

    public void craftRunes(Script script, int altarID) {
        RS2Object altar = script.objects.closest(altarID);
        while (!altar.isVisible()) {
            script.camera.toEntity(altar);
        }
        while (script.inventory.getAmount(this.UNNOTE_ESS) > 0) {
            while (!script.myPlayer().isAnimating()) {
                altar.interact("Craft-rune");
            }
            this.waitTime(100);
        }
    }
}
