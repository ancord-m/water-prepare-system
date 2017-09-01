import org.junit.Assert;
import org.junit.Test;

public class BottomFloatingSwitchTest {
    @Test
    public void HasWater_BottomFloatingSwitcherDown_False(){
        BottomFloatingSwitch bfs = new BottomFloatingSwitch("ДУ1");

        System.out.println("Откачиваем воду...");
        bfs.down();

        Assert.assertFalse(bfs.getState());
    }

    @Test
    public void HasWater_BottomFloatingSwitcherUp_True(){
        BottomFloatingSwitch bfs = new BottomFloatingSwitch("ДУ1");

        System.out.println("Закачиваем воду...");
        bfs.up();

        Assert.assertTrue(bfs.getState());
    }
}
