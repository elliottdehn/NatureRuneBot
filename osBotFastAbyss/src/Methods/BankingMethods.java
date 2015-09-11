package Methods;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

//important banking methods used to clean up code
public class BankingMethods extends KernalBankingMethods {
    //id is the ID of bank
    //turns camera toward bank

    public void depositAllExcept(Script script, int[] exceptions) {
        assert script.bank.isOpen() == true : "Bank was not open!";

        while (!script.inventory.isEmptyExcept(exceptions)) {
            script.bank.depositAllExcept(exceptions);
        }
    }

    public void withdrawItemsAmounts(Script script, int[] items, int[] amounts,
            int bankID, String bankOption) {
        assert items.length == amounts.length : "Lengths not equal!";

        if (!script.bank.isOpen()) {
            this.openBank(script, bankID, bankOption);
        }

        for (int i = 0; i < items.length; i++) {
            script.log("Tried to withdraw ID: " + items[i]);
            int id = items[i];
            int amount = amounts[i];

            //              if (script.bank.getAmount(id) < amount) {
            //                  script.log("Ran out of item id: " + items[i]);
            //                  script.stop();
            //              }
            script.log("looped");
            script.bank.withdraw(items[i], amounts[i]);
            while ((script.inventory.getAmount(id) < amount)) {
                script.bank.withdraw(items[i], amounts[i]);
                this.waitTime(100);
            }

        }
    }

    public void unnoteItem(Script script, int notedId, int unnotedId,
            int bankId, String option) {
        assert script.inventory.getAmount(notedId) > 0 : "Had nothing to unnote";

        RS2Object bankBooth = script.objects.closest(bankId);
        int itemSlot = script.inventory.getSlot(notedId);
        while (!bankBooth.isVisible()) {
            script.camera.toEntity(bankBooth);
        }

        this.closeBank(script);

        script.camera.toTop();
        int startAmount = (int) script.inventory.getAmount(unnotedId);
        while (script.inventory.getAmount(unnotedId) == startAmount) {
            this.closeBank(script);

            while (!script.inventory.isItemSelected()) {
                script.inventory.interact(itemSlot, "Use");
                this.waitTime(100);
            }

            while (script.inventory.isItemSelected()) {
                bankBooth.interact("Use");
                this.waitTime(900);
            }
            if (script.getWidgets().isVisible(219, 0)) {
                this.moveClickInBox(script, false, 255, 294, 396, 409);
                this.waitTime(800);
            }
        }
    }

}
