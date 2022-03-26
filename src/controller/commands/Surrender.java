package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;
import model.data.Field;
import model.data.Point;

import java.io.IOException;

public class Surrender implements Command{
    private final Field field;
    public Surrender(CommandDescriptor descriptor) throws MakeCommandException {
        this.field = descriptor.field;
    }

    @Override
    public Tags run() throws RunCommandException, IOException {
        field.setLose();
        return Tags.False;
    }

    @Override
    public Point getArg() {
        return null;
    }
}
