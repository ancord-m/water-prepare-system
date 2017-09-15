import interfaces.IValve;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ConsumerValveTest {
    @After
    public void insertDelimiter(){
        System.out.println();
    }

    @Test
    public void isOpened_ValveOpened_True(){
        IValve valve = new ConsumerValve();

        System.out.println("Открыть кран");
        valve.open();

        Assert.assertTrue(valve.state());
    }

    @Test
    public void isOpened_ValveClosed_False(){
        IValve valve = new ConsumerValve();

        System.out.println("Закрыть кран");
        valve.close();

        Assert.assertFalse(valve.state());
    }
}
