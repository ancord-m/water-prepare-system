import interfaces.IFloatingSwitch;

public class Barrel {
    private boolean full;
    private boolean empty;

    private String name;
    private IFloatingSwitch topSwitch;
    private IFloatingSwitch bottomSwitch;
    private IFloatingSwitch emergencySwitch;
    private BallValve inputValve;
    private BallValve outputValve;

    public Barrel(String name){
        this.name = name;
        topSwitch = new TopFloatingSwitch("верхний");
        bottomSwitch = new BottomFloatingSwitch("нижний");
        emergencySwitch = new EmergencyFloatingSwitch();
        inputValve = new BallValve("входной");
        outputValve = new BallValve("выходной");
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

    public void getState(){
        StringBuilder result = new StringBuilder("Бочка " + name + " ");

        if(topSwitch.getState() && bottomSwitch.getState()){
            result.append("полная");
        }

        if(!topSwitch.getState() && bottomSwitch.getState()){
            result.append("расходуется");
        }

        if(!topSwitch.getState() && !bottomSwitch.getState()) {
            result.append("пустая");
        }

        System.out.println(result);
    }
}
