package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;

import java.io.IOException;

public class RemoveFlag extends AbstractGameCommand {
    public RemoveFlag(CommandDescriptor descriptor) throws MakeCommandException {
        super(descriptor);
    }

    @Override
    public Tags run() throws IOException {
        if (field.outOf(point)) {
            throw new IOException();
        }
        if (field.isFlag(point)) {
            field.removeFlag(point);
        }
        return Tags.True;
    }
}