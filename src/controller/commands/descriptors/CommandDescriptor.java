package controller.commands.descriptors;

import model.data.Field;
import view.Viewer;

public class CommandDescriptor {
    public String [] args;
    public Field field;

    public CommandDescriptor(String [] args, Field field) {
        this.args = args;
        this.field = field;
    }
}
