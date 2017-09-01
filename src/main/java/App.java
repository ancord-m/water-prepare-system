import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class App {
    static Barrel barrel_one;
    static Barrel barrel_two;
    static Pump inputPump;

    public static void main(String[] args) throws Exception{
        init();
        int i = 0;
        while (!barrel_one.getEmergencySwitch().getState() &&
                !barrel_two.getEmergencySwitch().getState()) {
            barrel_one.getTopSwitch().getState();
            barrel_one.getBottomSwitch().getState();
            barrel_two.getTopSwitch().getState();
            barrel_two.getBottomSwitch().getState();
            System.out.println("\n\n\n");
            Thread.sleep(5000);
        }
    }

    public static void init(){
        barrel_one = new Barrel("первая");
        barrel_two = new Barrel("вторая");

        barrel_one.getEmergencySwitch().down();
        barrel_one.getTopSwitch().up();
        barrel_one.getBottomSwitch().up();
        barrel_one.getInputValve().close();
        barrel_one.getOutputValve().close();

        barrel_two.getEmergencySwitch().down();
        barrel_two.getTopSwitch().up();
        barrel_two.getBottomSwitch().up();
        barrel_two.getInputValve().close();
        barrel_two.getOutputValve().close();

        inputPump = new Pump("вводной");

        WaterConsumer waterConsumer = new WaterConsumer();
        waterConsumer.start();
    }

    static class WaterConsumer extends Thread{
        public void run() {
            Scanner sc = new Scanner(System.in);
            int var;
            do {
                System.err.println("Выбери действие");
                var = sc.nextInt();
                switch (var) {
                    case 1:
                        barrel_one.getInputValve().open();
                        break;
                }
            } while (var != -1);
        }
    }
}
