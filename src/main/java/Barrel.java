import interfaces.IFloatingSwitch;

public class Barrel {
    private boolean active;

    private String name;
    private IFloatingSwitch topSwitch;
    private IFloatingSwitch bottomSwitch;
    private IFloatingSwitch emergencySwitch;
    private BallValve inputValve;
    private BallValve outputValve;
    private boolean filling;

    public Barrel(String name){
        this.name = name;
        active = false;
        topSwitch = new TopFloatingSwitch("TOP");
        bottomSwitch = new BottomFloatingSwitch("BOTTOM");
        emergencySwitch = new EmergencyFloatingSwitch();
        inputValve = new BallValve("IN");
        outputValve = new BallValve("OUT");
        filling = false;
    }

    public IFloatingSwitch getEmergencySwitch() {
        return emergencySwitch;
    }

    public String getName() {
        return name;
    }

    public IFloatingSwitch getTopSwitch() {
        return topSwitch;
    }

    public IFloatingSwitch getBottomSwitch() {
        return bottomSwitch;
    }

    public BallValve getInputValve() {
        return inputValve;
    }

    public BallValve getOutputValve() {
        return outputValve;
    }

    public void setFilling(boolean f) {
        this.filling = f;
    }

    public boolean isFilling(){
        return filling;
    }

    public boolean isActive(){
        return active;
    }

    public void setIsActive(boolean active){
        this.active = active;
        if(this.active) {
            outputValve.open();
        } else {
            outputValve.close();
        }
    }

    public void getState(){
        StringBuilder result =
                new StringBuilder(name +  (active ? " активна и " : " не активна и "));

        if(topSwitch.getSimpleState() == 1 && bottomSwitch.getSimpleState() == 1){
            result.append("полная");
        }

        if(topSwitch.getSimpleState() == 0 && bottomSwitch.getSimpleState() == 1){
            result.append("расходуется или заполняется");
        }

        if(topSwitch.getSimpleState() == 0 && bottomSwitch.getSimpleState() == 0) {
            result.append("пустая");
        }

        System.out.println(result);
    }
}
