import org.junit.Assert;
import org.junit.Test;

public class TopFloatingSwitchTest {
    @Test
    public void HasWater_TopFloatingSwitchDown_False(){
        TopFloatingSwitch topFloatingSwitch = new TopFloatingSwitch("ПВ1");

        System.out.println("Откачиваем воду...");
        topFloatingSwitch.down();

        Assert.assertFalse(topFloatingSwitch.getState());
    }

    @Test
    public void HasWater_TopFloatingSwitchUp_True(){
        TopFloatingSwitch topFloatingSwitch = new TopFloatingSwitch("ПВ1");

        System.out.println("Закачиваем воду...");
        topFloatingSwitch.up();

        Assert.assertTrue(topFloatingSwitch.getState());
    }
}
