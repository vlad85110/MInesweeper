package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;

import java.io.IOException;

public class RemoveFlag extends AbstractGameCommand {
    public RemoveFlag(CommandDescriptor descriptor) throws MakeCommandException {
        super(descriptor);
    }

    @Override
    public Tags run() throws RunCommandException {
        if (field.outOf(point)) {
            throw new RunCommandException();
        }
        if (field.isFlag(point)) {
            field.removeFlag(point);
        }
        return Tags.True;
    }
}