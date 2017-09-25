import interfaces.IValve;

import java.util.Scanner;

public class App {
    static int[] systemState = new int[4];
    static int decSystemState;

    public static void main(String[] args) throws Exception{
        Barrel barrel_one = new Barrel("Бочка 1");
        Barrel barrel_two = new Barrel("Бочка 2");
        Pump inputPump = new Pump("IN-PUMP");

        appInit(barrel_one, barrel_two);

        switchOnInit(barrel_one, barrel_two);

        while (!barrel_one.getEmergencySwitch().getState() &&
                !barrel_two.getEmergencySwitch().getState()) {

            int code = getSystemState(barrel_one, barrel_two);
            switch (code) {
                case 0:
                    doseNaOCl();
                    fillBarrel(barrel_one);
                    Thread.sleep(10000);
                    doseNaOCl();
                    fillBarrel(barrel_two);
                    barrel_one.getOutputValve().open();
                    barrel_one.setIsActive(true);
                    break;
                case 3:
                    switchBarrels(barrel_two, barrel_one);
                    doseNaOCl();
                    fillBarrel(barrel_one);
                    break;
                case 12:
                    switchBarrels(barrel_one, barrel_two);
                    doseNaOCl();
                    fillBarrel(barrel_two);
                    break;
                default:
                   // System.out.println(getSystemState());
            }

            printSystemState(barrel_one, barrel_two, code);

            Thread.sleep(3000);
        }
    }

    static void appInit(Barrel barrel_one, Barrel barrel_two){
        barrel_one.getEmergencySwitch().down();
        barrel_one.getTopSwitch().up();
        barrel_one.getBottomSwitch().up();
        barrel_one.getInputValve().close();
        barrel_one.getOutputValve().close();
        barrel_one.setIsActive(false);

        barrel_two.getEmergencySwitch().down();
        barrel_two.getTopSwitch().up();
        barrel_two.getBottomSwitch().up();
        barrel_two.getInputValve().close();
        barrel_two.getOutputValve().close();
        barrel_two.setIsActive(false);

        WaterConsumer waterConsumer = new WaterConsumer(barrel_one, barrel_two);
        waterConsumer.start();
    }

    static void switchOnInit(Barrel barrel_one, Barrel barrel_two){
        //TODO восстанавливать состояния бочек (расходуется/наполняется) из EEPROM
        barrel_one.getTopSwitch().down();
        barrel_one.getBottomSwitch().down();

        barrel_two.getTopSwitch().down();
        barrel_two.getBottomSwitch().down();

        printSystemState(barrel_one, barrel_two, getSystemState(barrel_one, barrel_two));
    }

    static void switchBarrels(Barrel toBeActivated, Barrel toBeDisabled) throws Exception{
        System.out.println("--- Переключение бочек ---");
        System.out.print("Активация " + toBeActivated.getName() + ": ");
        toBeActivated.getOutputValve().open();
        toBeActivated.getOutputValve().state();
        toBeActivated.setIsActive(true);

        System.out.print("Деактивация " + toBeDisabled.getName() + ": ");
        toBeDisabled.getOutputValve().close();
        toBeDisabled.getOutputValve().state();
        toBeDisabled.setIsActive(false);
    }

    static void fillBarrel(Barrel barrel) {
        System.out.println("--- Заполнение " + barrel.getName() + " ---");
        new Thread(new Runnable () {
            @Override
            public void run(){
                barrel.getState();
                barrel.getInputValve().open();
                barrel.getInputValve().state();

                barrel.getBottomSwitch().up();
                barrel.getState();
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                barrel.getTopSwitch().up();
                barrel.getState();
                barrel.getInputValve().close();
                barrel.getInputValve().state();
            }
        }).start();
}

    static void doseNaOCl(){
        DosingPump.injectNaClO();
    }

    static void ColumnWash(){

    }

    /**
     * Состояние системы будет описываться младшими битами одного байта
     * @return десятичное число от 0 до F, на основании которого выбирается действие
     */
    static int getSystemState(Barrel barrel_one, Barrel barrel_two){
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

    static void printSystemState(Barrel barrel_one, Barrel barrel_two, int code){
        System.out.println(">>> Состояние системы <<<");
        barrel_one.getState();
        barrel_two.getState();
        System.out.print("Код состояния: DEC " + code + " BIN ");
        for(int i = 0; i < systemState.length; i++){
            System.out.print(systemState[i]);
        }
        System.out.println();
        System.out.println(">>> Конец <<<");
        System.out.println();
    }

    static class WaterConsumer extends Thread{
        Barrel barrel_one;
        Barrel barrel_two;

        public WaterConsumer(Barrel barrel_one, Barrel barrel_two){
            this.barrel_one = barrel_one;
            this.barrel_two = barrel_two;
        }

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
