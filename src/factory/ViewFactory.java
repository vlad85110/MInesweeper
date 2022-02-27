package factory;

import view.Updater;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ViewFactory extends Factory {
    public ViewFactory() throws IOException, NullPointerException {
        super();
    }
}
