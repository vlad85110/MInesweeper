package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

import java.io.IOException;

public class Menu implements Command {
    public Menu(CommandDescriptor descriptor) {
    }

    @Override
    public Tags run() throws IOException {
        return Tags.Menu;
    }

    @Override
    public Object getArg() {
        return null;
    }
}