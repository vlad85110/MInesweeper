package controller.console;

import controller.AbstractController;
import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;
import factory.CommandFactory;

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

    public ConsoleController(Object desc) {
        field = null;

        try {
            factory = new CommandFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner = new Scanner(System.in);
    }

    @Override
    public String waitLevel() throws NullPointerException {
        return difficulties.get(scanner.nextLine().trim()).toLowerCase();
    }

    @Override
    public String waitAnswer() {
        return scanner.nextLine().trim();
    }

    @Override
    public Command waitCommand() throws IOException {
        var cmdStr = scanner.nextLine().trim();
        return makeCommand(cmdStr);
    }
}
