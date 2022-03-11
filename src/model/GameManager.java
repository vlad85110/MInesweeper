package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Tags;
import factory.ControllerFactory;
import factory.ViewFactory;
import view.Viewer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GameManager {
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

        Command cmd;
        Tags res = Tags.False;

        while (res == Tags.False) {
            //TODO filenames in config
            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (IOException e) {
                    viewer.showErrorMessage("wrong input, return");
                }
            }

            try {
                res = cmd.run();
                if (res == Tags.Write) {
                    //TODO format
                    for (var i : (ArrayList<?>)cmd.getArg()) {
                        viewer.showMessage((String)i);
                    }
                    res = Tags.False;
                }
            } catch (IOException e) {
                viewer.showWarningMessage("Can't run command");
            }
        }

        if (res == Tags.Exit) {
            return;
        }

        Executor executor = new Executor(controller, viewer);
        executor.run();
    }
}
