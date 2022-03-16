package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

import java.io.IOException;

public class Restart implements Command {
    public Restart(CommandDescriptor descriptor) {
    }

    @Override
    public Tags run() throws IOException {
        return Tags.Restart;
    }

    @Override
    public Object getArg() {
        return null;
    }
}
