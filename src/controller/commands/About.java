package controller.commands;

import controller.commands.Command;

import java.io.IOException;

public class About implements Command {
    @Override
    public Tags run() {
        //TODO
        return Tags.False;
    }

    @Override
    public Object getArg() {
        return null;
    }
}
