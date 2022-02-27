package model;

import controller.Controller;
import controller.commands.Command;
import controller.commands.Open;
import model.data.Field;
import view.Viewer;

import java.io.IOException;

public record Executor(Controller controller, Field field, GameStarter starter, Viewer viewer) {

    public void run() {
        boolean notLose = false;
        Command cmd;

        while (!field.isStart()) {
            viewer.getUpdate(field.getUserView());
            cmd = controller.waitCommand();
            if (cmd instanceof Open) {
                starter.initField(cmd.getPoint());
                field.setStart();
            }
            try {
                notLose = cmd.run();
            } catch (IOException e) {
                viewer.showWarningMessage("incorrect point");
                notLose = true;
            }
        }

        while (notLose && !field.isWin()) {
            viewer.getUpdate(field.getUserView());
            cmd = controller.waitCommand();
            try {
                notLose = cmd.run();
            } catch (IOException e) {
                viewer.showWarningMessage("incorrect point");
            }
        }

        if (field().isWin()) {
            viewer.showWinMessage();
        } else {
            viewer.getUpdate(field.getLoseMap());
            viewer.showLoseMessage();
        }
    }
}
