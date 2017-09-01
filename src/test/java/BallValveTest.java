import org.junit.Assert;
import org.junit.Test;

public class BallValveTest {
    @Test
    public void ValveOpened_Open_True(){
        BallValve bv = new BallValve("Краник");
        System.out.println("Открыть кран!");

        bv.open();

        Assert.assertTrue(bv.state());
    }

    @Test
    public void ValveOpened_Close_False(){
        BallValve bv = new BallValve("Краникк");
        System.out.println("Закрыть кран!");

        bv.close();

        Assert.assertFalse(bv.state());
    }
}
