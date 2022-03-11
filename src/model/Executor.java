package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Open;
import controller.commands.Tags;
import model.data.Field;
import model.data.GameDescriptor;
import model.data.Point;
import view.Viewer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Executor {
    private final Controller controller;
    private final Viewer viewer;

    private Field field;
    private long time;
    private MapCreator creator;

    public Executor(Controller controller, Viewer viewer) {
        this.controller = controller;
        this.viewer = viewer;
    }

    private void createField() {
        viewer.showLevelChoosing();

        String level = null;
        while (level == null) {
            try {
                level = controller.waitLevel();
            } catch (NullPointerException e) {
                viewer.showErrorMessage("wrong input. return");
            }
        }

        GameDescriptor descriptor;
        try {
            descriptor = makeDescriptor(level);
        } catch (IOException e) {
            viewer.showErrorMessage("can't rum game");
            return;
        }

        field = new Field(descriptor);
        creator = new MapCreator(descriptor, field);
        controller.setField(field);
    }

    public void run() {
        createField();

        Tags notLose = Tags.False;
        Command cmd;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                viewer.showLoseMessage();
                System.exit(0);
            }
        };

        timer.schedule(task, time);
        viewer.startGame();
        while (!field.isStart() && notLose != Tags.Exit) {
            cmd = makeCommand(time);

            if (cmd instanceof Open) {
                creator.initField((Point)cmd.getArg());
                field.setStart();
            }

            try {
                notLose = cmd.run();
            } catch (IOException e) {
                viewer.showWarningMessage("incorrect point");
                notLose = Tags.True;
            }
        }

        while (notLose == Tags.True && !field.isWin()) {
            cmd = makeCommand(time);

            try {
                notLose = cmd.run();
            } catch (IOException e) {
                viewer.showWarningMessage("incorrect point");
            }
        }

        if (field.isWin()) {
            viewer.showWinMessage();
        } else if (notLose == Tags.Exit) {} else {
            viewer.getUpdate(field.getLoseMap(), time);
            viewer.showLoseMessage();
        }
    }

    private Command makeCommand(long time) {
        Command cmd;
        viewer.getUpdate(field.getUserView(), time);

        cmd = null;
        while (cmd == null) {
            try {
                cmd = controller.waitCommand();
            } catch (IOException e) {
                viewer.showErrorMessage("wrong input, return");
            }
        }
        return cmd;
    }

    private GameDescriptor makeDescriptor(String level) throws IOException, NullPointerException {
        Properties properties = new Properties();
        var reader = new FileReader(level + ".config");
        properties.load(reader);

        int bombs = Integer.parseInt(properties.getProperty("bombs"));
        int size = Integer.parseInt(properties.getProperty("size"));
        int safetyRad = Integer.parseInt(properties.getProperty("safetyRad"));
        int labyrinth = Integer.parseInt(properties.getProperty("labyrinth"));
        time = (long)Integer.parseInt(properties.getProperty("time")) * 60 * 1000;

        return new GameDescriptor(bombs, size, safetyRad, labyrinth);
    }
}
