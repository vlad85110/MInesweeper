package controller.commands;

import exeptions.RunCommandException;

import java.io.IOException;

public interface Command {
    Tags run() throws RunCommandException, IOException;

    Object getArg();
}
