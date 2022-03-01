package controller.commands;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;

import java.io.IOException;

public class Exit implements Command {
    public Exit(CommandDescriptor descriptor) {
    }

    @Override
    public Tags run() {
        return Tags.Exit;
    }

    @Override
    public Object getArg() {
        return null;
    }
}
