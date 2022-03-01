package controller.console;

import controller.AbstractController;
import controller.commands.Command;
import controller.commands.CommandDescriptor;
import factory.CommandFactory;
import model.data.Point;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleController extends AbstractController {
    private final Scanner scanner;


    private static final HashMap<String, String> difficulties = new HashMap<>() {
       {
            put("1", "Beginner");
            put("2", "Intermediate");
            put("3", "Expert");
        }
    };

    public ConsoleController() {
        field = null;
        try {
            factory = new CommandFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);
    }

    @Override
    public String waitLevel() {
        return difficulties.get(scanner.nextLine().trim()).toLowerCase();
    }

    @Override
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
