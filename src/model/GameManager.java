package model;

import controller.Controller;
import controller.commands.*;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;
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
            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (MakeCommandException e) {
                    viewer.showList("wrong input, return");
                    e.printStackTrace();
                }
            }

            if (cmd instanceof HighScores) {
                viewer.showLevelChoosing();
                var level = controller.waitLevel();
                ((HighScores) cmd).setArg(level);
            }

            try {
                res = cmd.run();
                if (res == Tags.Write) {
                    assert cmd instanceof AbstractWriteCommand;
                    write((AbstractWriteCommand) cmd);
                    res = Tags.False;
                }
            } catch (IOException | RunCommandException e) {
                viewer.showList("Can't run command");
                e.printStackTrace();
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


    boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    void write(AbstractWriteCommand command) {
        StringBuilder message = new StringBuilder();
        for (var i : (ArrayList<?>)command.getOutput()) {
            var arr = i.toString().split(" ");
            for (var j : arr) {
                if (isNumber(j)) {
                    long num = Integer.parseInt(j);
                    message.append(String.format("%tM:%tS ", num, num));
                } else {
                    message.append(j);
                }
            }
            message.append("\n");
        }

        if (command instanceof HighScores) {
            viewer.showList(message.toString());
        }

        if (command instanceof About) {
            viewer.showText(message.toString());
        }
    }
}
