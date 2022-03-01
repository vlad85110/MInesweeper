package controller.commands;

import java.io.IOException;

public interface Command {
    Tags run() throws IOException;

    Object getArg();
}
