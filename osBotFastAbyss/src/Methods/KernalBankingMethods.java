package Methods;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class KernalBankingMethods extends Core {
    public void openBank(Script script, int id, String option) {
        assert !script.bank.isOpen() == false : "Bank was open!";
        RS2Object bank = script.objects.closest(id);
        assert script.objects.closest(id) != null : "Bank was null";

        while (!script.bank.isOpen()) {
            if (!bank.isVisible()) {
                script.camera.toEntity(bank);
            }

            bank.interact(option);
            while (!script.bank.isOpen()) {
                bank.interact(option);
                this.waitTime(100);
            }
        }
    }

    public void closeBank(Script script) {
        assert script.bank.isOpen() == true : "Bank was not open";

        while (script.bank.isOpen()) {
            script.bank.close();
        }
    }

}
