import interfaces.IValve;

public class ConsumerValve implements IValve{
    boolean opened;
    public void close() {
        opened = false;
        state();
    }

    public void open() {
        opened = true;
        state();
    }

    public boolean state() {
        System.out.println("Потребитель " + (opened ? "открыл" : "закрыл") + " кран");
        return opened;
    }
}
