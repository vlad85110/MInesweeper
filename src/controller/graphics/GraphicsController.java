package controller.graphics;

import controller.AbstractController;
import controller.commands.Command;
import exeptions.MakeCommandException;
import factory.CommandFactory;
import model.data.ControllerDescriptor;
import view.graphics.GraphicsViewer;

import java.io.IOException;

public class GraphicsController extends AbstractController {
    private final GraphicsViewer viewer;

    public GraphicsController(ControllerDescriptor desc) {
        viewer = (GraphicsViewer) desc.viewer();
        field = null;

        try {
            factory = new CommandFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String waitLevel() {
        return viewer.waitAction().toLowerCase();
    }

    @Override
    public Command waitCommand() throws MakeCommandException {
        String cmdStr;
        cmdStr = viewer.waitAction();

        if (cmdStr == null) {
            return null;
        }
        return makeCommand(cmdStr);
    }

    @Override
    public String waitAnswer() {
        return viewer.waitAction();
    }

    @Override
    public void interrupt() {
        viewer.setInterruptFlag(true);
    }
}
