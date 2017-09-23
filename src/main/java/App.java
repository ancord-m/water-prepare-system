import interfaces.IValve;

import java.util.Scanner;

public class App {
    static Barrel barrel_one;
    static Barrel barrel_two;
    static Pump inputPump;
    static int[] systemState = new int[4];
    static int decSystemState;

    public static void main(String[] args) throws Exception{
        appInit();

        switchOnInit();

        while (!barrel_one.getEmergencySwitch().getState() &&
                !barrel_two.getEmergencySwitch().getState()) {

            switch (getSystemState()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
            }



            //System.out.println(decSystemState);
            //printSystemState();

            Thread.sleep(3000);
        }
    }

    static void appInit(){
        barrel_one = new Barrel("1");
        barrel_two = new Barrel("2");

        barrel_one.getEmergencySwitch().down();
        barrel_one.getTopSwitch().up();
        barrel_one.getBottomSwitch().up();
        barrel_one.getInputValve().close();
        barrel_one.getOutputValve().open();
        barrel_one.setIsActive(true);

        barrel_two.getEmergencySwitch().down();
        barrel_two.getTopSwitch().up();
        barrel_two.getBottomSwitch().up();
        barrel_two.getInputValve().close();
        barrel_two.getOutputValve().close();
        barrel_two.setIsActive(false);

        inputPump = new Pump("Н1");

        WaterConsumer waterConsumer = new WaterConsumer();
        waterConsumer.start();
    }

    static void switchOnInit(){

    }

    static void BarrelSwitching(){

    }

    static void fillBarrel(){

    }

    static void doseNaOCl(){

    }

    static void ColumnWash(){

    }

    /**
     * Состояние системы будет описываться младшими битами одного байта
     * @return десятичное число от 0 до F, на основании которого выбирается действие
     */
    static int getSystemState(){
        Double result;

        systemState[0] = barrel_one.getBottomSwitch().getSimpleState();
        systemState[1] = barrel_one.getTopSwitch().getSimpleState();
        systemState[2] = barrel_two.getBottomSwitch().getSimpleState();
        systemState[3] = barrel_two.getTopSwitch().getSimpleState();

        result =
                systemState[0] * Math.pow(2, 3) +
                systemState[1] * Math.pow(2, 2) +
                systemState[2] * Math.pow(2, 1) +
                systemState[3] * Math.pow(2, 0);

        return result.intValue();
    }

    static void printSystemState(){
        for(int i = 0; i < systemState.length; i++){
            System.out.print(systemState[i]);
        }
        System.out.println();
    }

    static class WaterConsumer extends Thread{
        public void run() {
            IValve consumerValve = new ConsumerValve();
            Scanner sc = new Scanner(System.in);
            int var;
            do {
                System.err.println("Выбери действие");
                var = sc.nextInt();
                switch (var) {
                    case 1:
                        barrel_one.getEmergencySwitch().up();
                        break;
                    case 2:
                        barrel_one.getBottomSwitch().down();
                        break;
                    case 3:
                        barrel_one.getTopSwitch().down();
                        break;
                    case 4:
                        barrel_two.getBottomSwitch().down();
                        break;
                    case 5:
                        barrel_two.getTopSwitch().down();
                        break;
                }
            } while (var != -1);
        }
    }

}
