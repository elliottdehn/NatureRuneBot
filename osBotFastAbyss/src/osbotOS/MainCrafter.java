package osbotOS;

import java.util.ArrayList;
import java.util.Collections;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import Data.Constants;
import Methods.Core;

//imports omitted
@ScriptManifest(author = "Elliott", info = "shitty fire crafter", name = "Freaky Fast Abyss", version = 1.0, logo = "")
public class MainCrafter extends Script {

    public ArrayList<Node> nodes = new ArrayList<>();

    public Core coreMethods = new Core();
    public Constants c = new Constants();

    @Override
    public void onStart() {

        this.log(this.nodes.size());
        Collections.addAll(this.nodes, new ActivityInBank(this),
                new ActivityOutOfBank(this), new ActivityGetToZanaris(this),
                new ActivityGetToTai(this), new ActivityGetToAltar(this),
                new ActivityInAltarStuff(this), new ActivityGetBackToEdgeTab(
                        this), new ActivityGetBackToEdgeGlory(this),
                new ActivityUseMountedGlory(this));
        this.log(this.nodes.size());
    }

    @Override
    public int onLoop() throws InterruptedException {
        for (Node node : this.nodes) { //loop through the nodes
            if (node.validate()) { //validate each node
                node.execute(); //execute
                this.coreMethods.waitTime(200); //prevents us from going through the logic as fast as we possibly can
            }
        }
        return this.coreMethods.rand(50, 100);
    }
}