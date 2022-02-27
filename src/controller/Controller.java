package controller;

import factory.CommandFactory;
import model.Field;
import model.Point;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Controller {
    //TODO without scanner
    private final Scanner scanner;
    private CommandFactory factory;
    private final Field field;

    public Controller(Field field) {
        this.field = field;
        try {
            factory = new CommandFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);
    }

    public Command waitCommand() {
        var cmdStr = scanner.nextLine();
        var descriptor = parseStr(cmdStr);

        Command command = null;
        try {
            command = factory.createObject(descriptor);
        } catch (ClassNotFoundException | InvocationTargetException |
                IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }

        assert command != null;
        return command;
    }

    private CommandDescriptor parseStr(String str) {
        var parts = str.split( " ");
        var point = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        return new CommandDescriptor(parts[0], point, field);
    }
}
