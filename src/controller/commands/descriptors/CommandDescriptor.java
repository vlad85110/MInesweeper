package controller.commands.descriptors;

import model.data.Field;

public class CommandDescriptor {
    public String [] args;
    public Field field;

    public CommandDescriptor(String [] args, Field field) {
        this.args = args;
        this.field = field;
    }
}
