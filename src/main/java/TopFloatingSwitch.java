import interfaces.IFloatingSwitch;

public class TopFloatingSwitch implements IFloatingSwitch {
    boolean hasWater;
    String name;

    public TopFloatingSwitch(String name){
        this.name = name;
    }

    public void down(){
        hasWater = false;
    }

    public void up(){
        hasWater = true;
    }

    public boolean getState(){
        System.out.println("Поплавковый выключатель " + name + (hasWater ? ": есть вода" : ": вода закончилась"));
        return hasWater;
    }
}
