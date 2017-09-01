import interfaces.IFloatingSwitch;
import interfaces.IPump;
import interfaces.IValve;

public class Pump implements IPump{
    String name;
    boolean working;

    public Pump(String name){
        this.name = name;
    }

    public void act(IValve valve) {
        if(valve.state()){
            System.out.println("Давление упало. Насос " + name + " включён.");
            working = true;
        } else {
            System.out.println("Давление выросло. Насос " + name + " отключён.");
            working = false;
        }
    }

    public boolean getState() {
        return working;
    }
}
