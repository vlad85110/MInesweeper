package controller;

import model.Point;

public interface Command {
    boolean run();

    Point getPoint();
}
