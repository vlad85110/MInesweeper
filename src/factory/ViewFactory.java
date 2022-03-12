package factory;

import controller.commands.Command;
import model.data.Field;
import view.Viewer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class ViewFactory extends Factory {
    public ViewFactory() throws IOException, NullPointerException {
        super();
    }

    @Override
    public Viewer createObject(Object desc) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> productClass;
        Viewer object;

        try {
            productClass = Class.forName("view." + names.get("interface").toLowerCase() +
                    "." + names.get("interface") + "Viewer");
            object = (Viewer)productClass.getConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException e) {
            System.err.println("Undefined class\n");
            throw e;
        }

        return object;
    }

    @Override
    protected String getFileName() {
        return "settings.config";
    }
}
