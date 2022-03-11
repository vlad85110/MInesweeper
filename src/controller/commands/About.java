package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

public class About extends AbstractWriteCommand {
    public About(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected String getFileName() {
        return "about.txt";
    }
}
