package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import model.data.Field;

import java.io.IOException;

public class SetFlag extends AbstractGameCommand {
    public SetFlag(CommandDescriptor descriptor) throws IOException {
        super(descriptor);
    }

    @Override
    public Tags run() {
         field.setFlag(point);
         return Tags.True;
    }
}
