/**
 *
 */
/**
 * @author Elliott
 *
 */
package Data;

import org.osbot.rs07.api.map.Position;

public class Constants {
    public int BANK_ID = 11744;
    public int UNNOTE_ESS = 7936;
    public int NOTE_ESS = 7937;
    public String BANK_OPTION = "Bank";

    public int eatAt = 60;
    public boolean[] pouchesInUse = new boolean[] { true, true, true, false };
    public int foodID = 7946; //monk
    public int tabAmount = 1;
    public int foodHeals = 16;
    public boolean usingGlory = false;
    public boolean usingPrayer = false;
    public boolean usingStam = false;
    public boolean usingTabs = true;

    public Position[] pathToRing = { new Position(3107, 3504, 0),
            new Position(3119, 3504, 0), new Position(3129, 3497, 0) };

    public Position[] pathToAltar = { new Position(2813, 2998, 0),
            new Position(2822, 3010, 0), new Position(2827, 3014, 0),
            new Position(2828, 3012, 0), new Position(2838, 3024, 0),
            new Position(2851, 3026, 0), new Position(2867, 3020, 0) };
    public Position[] pathToBank = { new Position(3096, 3494, 0) };
}