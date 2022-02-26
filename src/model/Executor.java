package model;

import view.console.Printer;

public class Executor {
    private final Field field;

    public Executor(int size, int mines) {
        field = new Field(size);
        GameStarter starter = new GameStarter(size, mines, field);
        starter.initField();
    }

    public void execute() {
        Printer.print(field.getUserView(), field.getSize());
    }
}
