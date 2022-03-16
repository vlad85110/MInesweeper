import controller.commands.Tags;
import model.GameManager;

public class Main {

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        Tags tag = null;

        while (tag != Tags.Exit) {
            tag = manager.start();
        }

        System.exit(0);
    }
}
