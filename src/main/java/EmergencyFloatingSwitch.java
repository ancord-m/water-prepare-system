import interfaces.IFloatingSwitch;

public class EmergencyFloatingSwitch implements IFloatingSwitch{
    boolean hasWater;

    public EmergencyFloatingSwitch(){
        this.hasWater = false;
    }

    public void down(){
        hasWater = false;
    }

    public void up(){
        hasWater = true;
    }

    public boolean getState(){
        if(hasWater) {
            System.out.println("Переполнение. Отключение насоса.");
        }
        return hasWater;
    }
}
