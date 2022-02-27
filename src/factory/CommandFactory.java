package factory;

import controller.commands.Command;
import controller.commands.CommandDescriptor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory extends Factory {
    public CommandFactory() throws IOException, NullPointerException {
        super();
    }

    @Override
    protected String getFileName() {
        return "commands.properties";
    }

    @Override
    public Command createObject(Object desc) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {

        Class<?> productClass;
        Command object;
        var descriptor = (CommandDescriptor)desc;

        try {
            productClass = Class.forName("controller.commands." + names.get(descriptor.name()));
            object = (Command)productClass.getConstructor(descriptor.getClass()).newInstance(descriptor);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException e) {
            System.err.println("Undefined class\n");
            throw e;
        }

        return object;
    }

}