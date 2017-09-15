import interfaces.IValve;

import java.util.Scanner;

public class App {
    static Barrel barrel_one;
    static Barrel barrel_two;
    static Pump inputPump;

    public static void main(String[] args) throws Exception{
        init();

        while (!barrel_one.getEmergencySwitch().getState() &&
                !barrel_two.getEmergencySwitch().getState()) {

            barrel_one.getState();
            barrel_two.getState();

            //systemStateCode = getSystemState();
            if(!barrel_one.getTopSwitch().getSimpleState()
                && !barrel_one.getBottomSwitch().getSimpleState()){
                barrel_one.setIsActive(false);
                barrel_two.setIsActive(true);
                inputPump.act(barrel_two.getInputValve());
            }

            System.out.println("\n\n\n");
            Thread.sleep(3000);
        }
    }

    public static void init(){
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
                        consumerValve.open();
                        consumeWater();
                        break;
                }
            } while (var != -1);
        }
    }

    public static void consumeWater(){
        /*Определяем активную бочку и иммитируем поэтапную откачку воды
        * Сначала бочка получает статус "расходуется", а затем "пуста"
        */
        if(barrel_one.isActive()) {
            if(barrel_one.getTopSwitch().getSimpleState()
                    && barrel_one.getBottomSwitch().getSimpleState()){
                barrel_one.getTopSwitch().down();
            } else if (!barrel_one.getTopSwitch().getSimpleState()
                    && barrel_one.getBottomSwitch().getSimpleState()){
                barrel_one.getBottomSwitch().down();
            }
        } else if(barrel_two.isActive()){
            if(barrel_two.getTopSwitch().getSimpleState()
                    && barrel_two.getBottomSwitch().getSimpleState()){
                barrel_two.getTopSwitch().down();
            } else if(!barrel_two.getTopSwitch().getSimpleState()
                    && barrel_two.getBottomSwitch().getSimpleState()){
                barrel_two.getBottomSwitch().down();
            }
        }
    }
}
