package controller;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import factory.CommandFactory;
import model.data.Field;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractController implements Controller {
    protected CommandFactory factory;
    protected Field field;

    @Override
    public void setField(Field field) {
        this.field = field;
    }

    protected Command makeCommand(String cmdStr) throws MakeCommandException {
        var args = cmdStr.split( " ");

        Command command;
        try {
            command = factory.createObject(new CommandDescriptor(args, field));
        } catch (ClassNotFoundException | InvocationTargetException |
                IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new MakeCommandException(e);
        }

        assert command != null;
        return command;
    }
}
