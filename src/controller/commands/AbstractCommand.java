package controller.commands;

import model.data.Field;
import model.data.Point;

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
