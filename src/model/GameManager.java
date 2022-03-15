package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Tags;
import factory.ControllerFactory;
import factory.ViewFactory;
import model.data.ControllerDescriptor;
import view.Viewer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameManager {
    private Viewer viewer;
    private Controller controller;
    private Executor executor;

    public GameManager() {
        ViewFactory viewFactory;
        ControllerFactory controllerFactory;
        try {
            viewFactory = new ViewFactory();
            controllerFactory = new ControllerFactory();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            viewer = viewFactory.createObject(null);
            controller = controllerFactory.createObject(new ControllerDescriptor(viewer));
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        assert viewer != null;
        assert controller != null;
        executor = new Executor(controller, viewer);
    }

    public Tags start() {
        viewer.showGreetScreen();

        Command cmd;
        Tags res = Tags.False;

        while (res == Tags.False) {
            //TODO filenames in config
            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (IOException e) {
                    viewer.showMessage("wrong input, return");
                }
            }

            try {
                res = cmd.run();
                if (res == Tags.Write) {
                    //TODO format
                    StringBuilder message = new StringBuilder();
                    for (var i : (ArrayList<?>)cmd.getArg()) {
                        message.append((String) i);
                        message.append("\n");
                    }
                    viewer.showMessage(message.toString());
                    res = Tags.False;
                }
            } catch (IOException e) {
                viewer.showMessage("Can't run command");
            }
        }

        if (res == Tags.Exit) {
            System.exit(0);
        }

        Tags tag = null;
        while (tag != Tags.Exit && tag != Tags.Menu) {
            tag = executor.run();
        }

        return tag;
    }
}
