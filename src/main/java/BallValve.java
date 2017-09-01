import interfaces.IValve;

public class BallValve implements IValve {
    boolean opened;
    String name;

    public BallValve(String name){
        this.name = name;
    }

    public void close(){
        opened = false;
    }

    public void open(){
        opened = true;
    }

    public boolean state(){
        System.out.println("Кран " + name + (opened ? " открыт" : " закрыт"));
        return opened;
    }
}
