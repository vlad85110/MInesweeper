package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

public class HighScores extends AbstractWriteCommand {
    public HighScores(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected String getFileName() {
        return "data/" + arg + ".txt";
    }
}