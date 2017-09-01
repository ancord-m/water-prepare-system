import interfaces.IFloatingSwitch;
import interfaces.IPump;
import interfaces.IValve;
import org.junit.*;

public class PumpTest {
    static IPump pump;
    static IValve valve;

    @BeforeClass
    public static void arrange(){
        pump = new Pump("Главный");
        valve = new BallValve("Входной");
    }

    @After
    public void separate(){
        System.out.println();
    }

    @Test
    public void PumpOn_HighPressure_False(){
        valve.close();
        pump.act(valve);

        System.out.println("Бочка полная");

        Assert.assertFalse(pump.getState());
    }

    @Test
    public void PumpOn_LowPressure_True(){
        valve.open();
        pump.act(valve);

        System.out.println("Бочка заполняется...");

        Assert.assertTrue(pump.getState());
    }
}
