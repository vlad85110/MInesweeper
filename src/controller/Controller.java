package controller;

import controller.commands.Command;
import model.data.Field;

import java.io.IOException;

public interface Controller {
    public void setField(Field field);

    public String waitLevel();

    public Command waitCommand() throws IOException;

}
