package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Tags;
import factory.ControllerFactory;
import factory.ViewFactory;
import view.Viewer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

        cmd = null;
        while (res == Tags.False) {
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (IOException e) {
                    viewer.showErrorMessage("wrong input, return");
                }
            }

            try {
                res = cmd.run();
            } catch (IOException e) {
                viewer.showWarningMessage("incorrect point");
            }
        }

        if (res == Tags.Exit) {
            return;
        }

        Executor executor = new Executor(controller, viewer);
        executor.run();
    }
}
