package controller.commands;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;

import java.io.IOException;

public class HighScores extends AbstractWriteCommand {
    public HighScores(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected String getFileName() {
        return "High scores.txt";
    }
}
