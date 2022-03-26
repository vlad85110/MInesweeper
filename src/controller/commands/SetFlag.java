package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;

public class SetFlag extends AbstractGameCommand {
    public SetFlag(CommandDescriptor descriptor) throws MakeCommandException {
        super(descriptor);
    }

    @Override
    public Tags run() throws RunCommandException {
        if (field.outOf(point)) {
            throw new RunCommandException();
        }
        field.setFlag(point);
        return Tags.True;
    }
}