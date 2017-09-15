import org.junit.Test;

public class BarrelTest {
    @Test
    public void BarrelStateTest(){
        Barrel barrel = new Barrel("Большая бочка");

        barrel.getBottomSwitch().up();

        barrel.getState();
    }
}
