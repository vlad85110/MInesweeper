package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;
import model.data.Point;

import java.io.IOException;

public class Surrender extends AbstractGameCommand {
    public Surrender(CommandDescriptor descriptor) throws MakeCommandException {
        super(descriptor);
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
