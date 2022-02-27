package controller.commands;

import model.data.Point;

import java.io.IOException;

public interface Command {
    boolean run() throws IOException;

    Point getPoint();
}
