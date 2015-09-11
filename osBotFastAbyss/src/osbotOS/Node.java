package osbotOS;

import org.osbot.rs07.script.Script;

import Data.Constants;
import Methods.BankingMethods;
import Methods.Core;
import Methods.PositionMethods;
import Methods.PouchMethods;

public abstract class Node {

    public Script script;
    public Constants c = new Constants();
    public BankingMethods bankMethods = new BankingMethods();
    public PositionMethods positionMethods = new PositionMethods();
    public PouchMethods pouchMethods = new PouchMethods();
    public Core coreMethods = new Core();

    public Node(Script script) {

        this.script = script;

    }

    public abstract void execute() throws InterruptedException;

    public abstract boolean validate();

}