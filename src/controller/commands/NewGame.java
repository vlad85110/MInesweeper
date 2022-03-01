package controller.commands;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;
import model.data.GameDescriptor;

import java.io.IOException;

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
