import interfaces.IFloatingSwitch;

public class BottomFloatingSwitch implements IFloatingSwitch {
    boolean hasWater;
    String name;

    public BottomFloatingSwitch(String name){
        this.name = name;
    }

    public void down(){
        hasWater = false;
    }

    public void up(){
        hasWater = true;
    }

    public boolean getState(){
        System.out.println("Датчик уровня " + name + (hasWater ? ": есть вода" : ": вода закончилась"));
        return hasWater;
    }
}
