import model.GameManager;
import view.Viewer;
import view.graphics.GraphicsViewer;

public class Main {

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.start();
        Viewer viewer = new GraphicsViewer();
        viewer.showGreetScreen();
        //System.exit(0);
    }
}
