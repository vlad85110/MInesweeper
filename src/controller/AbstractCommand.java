package controller;

import model.Field;
import model.Point;

public abstract class AbstractCommand implements Command {
    protected CommandDescriptor descriptor;
    protected Field field;


    protected AbstractCommand(CommandDescriptor descriptor) {
        this.descriptor = descriptor;
        field = descriptor.field();
    }

    @Override
    public Point getPoint() {
        return descriptor.arg();
    }
}
