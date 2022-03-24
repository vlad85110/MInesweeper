package controller;

import controller.commands.Command;
import exeptions.MakeCommandException;
import model.data.Field;

import java.io.IOException;

public interface Controller {
    void setField(Field field);

    String waitLevel();

    Command waitCommand() throws MakeCommandException;

    String waitAnswer();

    void interrupt();
}
