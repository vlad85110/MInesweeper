package model;

import controller.Command;
import controller.Controller;
import controller.Open;
import view.console.Printer;

public class Executor {
    private final Field field;
    private final int bombs;
    private final int size;

    public Executor(int size, int bombs) {
        field = new Field(size, bombs);
        this.bombs = bombs;
        this.size = size;
    }

    public void execute() {
        //TODO maybe
        var starter = new GameStarter(size, bombs, field, 2);
        var updater = new Printer();
        var controller = new Controller(field);
        boolean check = false;
        Command cmd;

        while (!field.isStart()) {
            updater.getUpdate(field.getUserView());
            cmd = controller.waitCommand();
            if (cmd instanceof Open) {
                starter.initField(cmd.getPoint());
                field.setStart();
            }
            check = cmd.run();
        }

        if (!check) System.out.println("lose");

        while (check || !field.isWin()) {
            updater.getUpdate(field.getUserView());
            cmd = controller.waitCommand();
            check = cmd.run();
            updater.getUpdate(field.getUserView());
        }
    }
}
