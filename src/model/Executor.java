package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Open;
import controller.commands.Tags;
import model.data.Field;
import model.data.GameDescriptor;
import model.data.Point;
import view.Viewer;

import java.io.*;
import java.util.*;

public class Executor {
    private final Controller controller;
    private final Viewer viewer;

    private Field field;
    private Map<Integer, String> scores;
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
                viewer.showMessage("wrong input. return");
            }
        }

        GameDescriptor descriptor;
        try {
            descriptor = makeDescriptor(level);
        } catch (IOException e) {
            viewer.showMessage("can't rum game");
            return;
        }

        field = new Field(descriptor);
        creator = new MapCreator(descriptor, field);
        controller.setField(field);

        try {
            readScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        createField();

        Tags notLose = Tags.False;
        Command cmd;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                viewer.showMessage("You lose, time is off");
                System.exit(0);
            }
        };

        timer.schedule(task, time);
        viewer.startGame();


        while (!field.isStart()) {
            viewer.getUpdate(field.getUserView(), time);

            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (IOException e) {
                    viewer.showMessage("wrong input, return");
                }

                if (cmd instanceof Open) {
                    try {
                        creator.initField((Point) cmd.getArg());
                    } catch (IOException e) {
                        viewer.showMessage(e.getMessage());
                        cmd = null;
                    }
                    field.setStart();
                }
            }

            try {
                    notLose = cmd.run();
            } catch (IOException e) {
                viewer.showMessage("incorrect point");
                notLose = Tags.True;
            }

            if (notLose == Tags.Exit)
                return;
        }

        while (notLose == Tags.True && !field.isWin()) {
            viewer.getUpdate(field.getUserView(), time);

            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (IOException e) {
                    viewer.showMessage("wrong input, return");
                }
            }

            try {
                notLose = cmd.run();
            } catch (IOException e) {
                viewer.showMessage("incorrect point");
            }
        }

        if (field.isWin()) {
            viewer.showMessage("You win!");
            try {
                readScores();
                writeScores(time);
            } catch (IOException e) {}
        } else if (notLose == Tags.Exit) {} else {
            viewer.getUpdate(field.getLoseMap(), time);
            viewer.showMessage("You lose");
        }
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

    private void readScores() throws IOException {
        File file = new File("High scores.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        scores = new TreeMap<>();
        String line;
        String [] strArray;

        line = reader.readLine();
        while (line != null) {
            strArray = line.split(" ");
            scores.put(Integer.parseInt(strArray[0]), strArray[1]);
            line = reader.readLine();
        }

        fileReader.close();
        reader.close();
    }

    private void writeScores(long time) throws IOException {
        File output = new File("High scores.txt");
        FileWriter fileWriter = new FileWriter(output);

        viewer.askUser("Who are you?");
        var name = controller.waitAnswer();
        scores.put((int)time / 1000, name);

        int j = 1;
        for (var i : scores.entrySet()) {
            if (j >= 5) break;
            fileWriter.write(i.getKey() + " " + i.getValue() + "\n");
            j++;
        }
    }
}
