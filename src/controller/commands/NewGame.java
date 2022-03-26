package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

public class NewGame implements Command {
    public NewGame(CommandDescriptor descriptor) {
    }

    @Override
    public Tags run() {
        return Tags.True;
    }

    @Override
    public Object getArg() {
        return null;
    }
}