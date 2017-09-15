import interfaces.IFloatingSwitch;

public class Barrel {
    private boolean active;

    private String name;
    private IFloatingSwitch topSwitch;
    private IFloatingSwitch bottomSwitch;
    private IFloatingSwitch emergencySwitch;
    private BallValve inputValve;
    private BallValve outputValve;

    public Barrel(String name){
        this.name = name;
        active = false;
        topSwitch = new TopFloatingSwitch("TOP");
        bottomSwitch = new BottomFloatingSwitch("BOTTOM");
        emergencySwitch = new EmergencyFloatingSwitch();
        inputValve = new BallValve("IN");
        outputValve = new BallValve("OUT");
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
                new StringBuilder("Бочка " + name +  (active ? " активна и " : " не активна и "));

        if(topSwitch.getSimpleState() && bottomSwitch.getSimpleState()){
            result.append("полная");
        }

        if(!topSwitch.getSimpleState() && bottomSwitch.getSimpleState()){
            result.append("расходуется");
        }

        if(!topSwitch.getSimpleState() && !bottomSwitch.getSimpleState()) {
            result.append("пустая");
        }

        System.out.println(result);
    }
}
