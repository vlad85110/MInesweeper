package controller;

import model.Point;

public class SetFlag extends AbstractCommand {
    public SetFlag(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public boolean run() {
         field.setFlag(descriptor.arg());
         return true;
    }
}
