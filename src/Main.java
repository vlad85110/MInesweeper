import model.Executor;
import model.Field;
import model.GameStarter;
import view.console.Printer;

public class Main {

    public static void main(String[] args) {
        Executor executor = new Executor(9, 10);
        executor.execute();
    }
}
