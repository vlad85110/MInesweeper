package controller.commands;

import controller.commands.AbstractCommand;
import controller.commands.CommandDescriptor;

public class SetFlag extends AbstractCommand {
    public SetFlag(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public boolean run() {
         field.setFlag(descriptor.arg());
         return true;
    }
}
