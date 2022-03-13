package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

import java.io.IOException;

public class RemoveFlag extends AbstractGameCommand {
    public RemoveFlag(CommandDescriptor descriptor) throws IOException {
        super(descriptor);
    }

    @Override
    public Tags run() throws IOException {
        if (field.outOf(point)) {
            throw new IOException();
        }
        field.removeFlag(point);
        return Tags.True;
    }
}
