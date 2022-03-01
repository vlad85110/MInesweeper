package model;

import controller.Controller;
import factory.ControllerFactory;
import factory.ViewFactory;
import model.data.Field;
import model.data.GameDescriptor;
import view.Viewer;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class GameManager {
    private GameDescriptor makeDescriptor(String level) throws IOException, NullPointerException {
        Properties properties = new Properties();
        var reader = new FileReader(level + ".config");
        properties.load(reader);

        int bombs = Integer.parseInt(properties.getProperty("bombs"));
        int size = Integer.parseInt(properties.getProperty("size"));
        int safetyRad = Integer.parseInt(properties.getProperty("safetyRad"));
        return new GameDescriptor(bombs, size, safetyRad);
    }

    public void start() {
        ViewFactory viewFactory;
        ControllerFactory controllerFactory;
        try {
            viewFactory = new ViewFactory();
            controllerFactory = new ControllerFactory();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Viewer viewer = null;
        Controller controller = null;
        try {
            viewer = viewFactory.createObject(null);
            controller = controllerFactory.createObject(null);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        assert viewer != null;
        assert controller != null;

        viewer.showGreetScreen();


        var level = controller.waitLevel();
        GameDescriptor descriptor;
        try {
            descriptor = makeDescriptor(level);
        } catch (IOException e) {
            viewer.showErrorMessage("can't rum game");
            return;
        }

        var field = new Field(descriptor);
        var starter = new MapCreator(descriptor, field);
        controller.setField(field);


        Executor executor = new Executor(controller, field, starter, viewer);
        executor.run();
    }
}
