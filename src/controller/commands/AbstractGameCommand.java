package controller.commands;

import controller.commands.Command;
import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import model.data.Field;
import model.data.Point;

import java.io.IOException;

public abstract class AbstractGameCommand implements Command {
    protected Point point;
    protected Field field;


    protected AbstractGameCommand(CommandDescriptor descriptor) throws MakeCommandException {
        this.field = descriptor.field;

        try {
            point = new Point(Integer.parseInt(descriptor.args[1]), Integer.parseInt(descriptor.args[2]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new MakeCommandException(e);
        }
    }

    @Override
    public Point getArg() {
        return point;
    }
}
