package factory;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;
import model.data.Field;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CommandFactory extends Factory {
    public CommandFactory() throws IOException, NullPointerException {
        super();
    }

    @Override
    protected String getFileName() {
        return "cfg/commands.properties";
    }

    @Override
    public Command createObject(Object desc) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {

        Class<?> productClass;
        Command object;
        var descriptor = (CommandDescriptor)desc;

        productClass = Class.forName("controller.commands." + names.get(descriptor.args[0]));
        object = (Command)productClass.getConstructor(descriptor.getClass()).newInstance(descriptor);

        return object;
    }

}