package controller.graphics;

import controller.AbstractController;
import controller.commands.Command;
import factory.CommandFactory;
import model.data.ControllerDescriptor;
import view.Viewer;

import java.io.IOException;

public class GraphicsController extends AbstractController {
    private final Viewer viewer;

    public GraphicsController(ControllerDescriptor desc) {
        viewer = desc.viewer();
        field = null;

        try {
            factory = new CommandFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String waitLevel() {
        return null;
    }

    @Override
    public Command waitCommand() throws IOException {
        String cmdStr = null;
        try {
            cmdStr = viewer.waitAction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return makeCommand(cmdStr);
    }

    @Override
    public String waitAnswer() {
        return null;
    }
}
