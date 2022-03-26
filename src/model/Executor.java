package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Open;
import controller.commands.Surrender;
import controller.commands.Tags;
import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;
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
    private Command cmd;
    private boolean gameEnd;
    private MapCreator creator;
    private String level;

    public Executor(Controller controller, Viewer viewer) {
        this.controller = controller;
        this.viewer = viewer;
    }

    private int createField() {
        viewer.showLevelChoosing();

        level = null;
        while (level == null) {
            try {
                level = controller.waitLevel();
            } catch (NullPointerException e) {
                viewer.showWarning("Command is null");

            }
        }

        GameDescriptor descriptor;
        try {
            descriptor = makeDescriptor();
        } catch (IOException e) {
            viewer.showWarning("Can't create field");
            return -1;
        }

        field = new Field(descriptor);
        creator = new MapCreator(descriptor, field);
        controller.setField(field);
        return 0;
    }

    public Tags run() {
        gameEnd = false;
        Tags tag = Tags.False;
        viewer.setAlreadyCreated(false);

        if (createField() == -1) return Tags.Menu;

            while (!field.isStart()) {
            viewer.getUpdate(field.getView(), time);

            cmd = null;
            while (cmd == null) {
                try {
                    cmd = controller.waitCommand();
                } catch (MakeCommandException e) {
                    viewer.showWarning("wrong input, return");
                    e.printStackTrace();
                }

                if (cmd instanceof Open) {
                    try {
                        creator.initField((Point) cmd.getArg());
                    } catch (IOException e) {
                        viewer.showList(e.getMessage());
                        cmd = null;
                    }
                    field.setStart();
                }
            }

            try {
                tag = cmd.run();
            } catch (RunCommandException | IOException e) {
                viewer.showWarning("incorrect point");
                tag = Tags.True;
            }

            if (tag == Tags.Exit || tag == Tags.Restart || tag == Tags.Menu)
                return tag;
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!gameEnd) {
                    viewer.getUpdate(field.getView(), time);
                }
                timeIsOff();
            }
        };

        timer.schedule(task, time);
        viewer.startGame();
        long startTime = System.currentTimeMillis();

        while (tag == Tags.True && !field.isWin()) {
            viewer.getUpdate(field.getView(), time);
            tag = runCommand();
        }

        timer.cancel();
        viewer.endGame();
        gameEnd = true;

        if (field.isWin()) {
            viewer.getUpdate(field.getView(), time);
            viewer.showWarning("You win!");
            try {
                readScores();
                writeScores(System.currentTimeMillis() - startTime);
            } catch (IOException e) {
                //
            }
            while (tag == Tags.True || tag == Tags.False) {
                tag = waitExit();
            }
            return tag;

        } else if (tag == Tags.Exit || tag == Tags.Restart || tag == Tags.Menu) {
            return tag;

        } else {
            viewer.getUpdate(field.getView(), time);
            viewer.showWarning("You lose");
            while (tag == Tags.True || tag == Tags.False) {
                tag = waitExit();
            }
            return tag;
        }
    }

    private Tags runCommand() {
        Tags tag = null;
        CmdThread cmdThread = new CmdThread(controller, viewer, this);
        cmdThread.start();

        cmd = null;
        while (cmd == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cmdThread.kill();

        try {
            cmdThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            tag = cmd.run();
        } catch (IOException | RunCommandException e) {
            viewer.showWarning("incorrect point");
        }
        return tag;
    }

    public void setCmd(Command cmd) {
        if (this.cmd == null)
            this.cmd = cmd;
    }

    private Tags waitExit() {
        Tags tag;
        cmd = null;

        while (cmd == null) {
            try {
                cmd = controller.waitCommand();
            } catch (MakeCommandException e) {
                viewer.showList("wrong input, return");
                e.printStackTrace();
            }
        }

        try {
            tag = cmd.run();
        } catch (RunCommandException | IOException e) {
            viewer.showList("incorrect point");
            tag = Tags.True;
        }

        return tag;
    }

    private GameDescriptor makeDescriptor() throws IOException {
        Properties properties = new Properties();
        var reader = new FileReader("cfg/" + level + ".config");
        properties.load(reader);

        int bombs = Integer.parseInt(properties.getProperty("bombs"));
        int size = Integer.parseInt(properties.getProperty("size"));
        int safetyRad = Integer.parseInt(properties.getProperty("safetyRad"));
        int labyrinth = Integer.parseInt(properties.getProperty("labyrinth"));
        time = (long)Integer.parseInt(properties.getProperty("time")) * 60 * 1000;

        return new GameDescriptor(bombs, size, safetyRad, labyrinth);
    }

    private void readScores() throws IOException {
        File file = new File( "data/" + level + ".txt");
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
        File output = new File("data/" + level + ".txt");
        FileWriter fileWriter = new FileWriter(output);

        viewer.askUser("Who are you?");
        var name = controller.waitAnswer();
        scores.put((int)time, name);

        for (var i : scores.entrySet()) {
            fileWriter.write(i.getKey() + " " + i.getValue() + "\n");
        }

        fileWriter.close();
    }

    private void timeIsOff() {
        try {
            cmd = new Surrender(new CommandDescriptor("surrender".split(" "), field));
        } catch (MakeCommandException e) {
            e.printStackTrace();
        }
    }
}