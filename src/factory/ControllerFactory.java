package factory;

import controller.Controller;
import model.data.ControllerDescriptor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ControllerFactory extends Factory {
    public ControllerFactory() throws IOException, NullPointerException {
        super();

    }

    @Override
    public Controller createObject(Object desc) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> productClass;
        Controller object;

        var descriptor = (ControllerDescriptor)desc;

        try {
            productClass = Class.forName("controller." + names.get("interface").toLowerCase() +
                    "." + names.get("interface") + "Controller");
            object = (Controller)productClass.getConstructor(descriptor.getClass()).newInstance(descriptor);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException e) {
            System.err.println("Undefined class\n");
            throw e;
        }

        return object;
    }

    @Override
    protected String getFileName() {
        return "cfg/settings.config";
    }
}
