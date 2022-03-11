package factory;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public abstract class Factory {
    protected final HashMap<String, String> names;

    protected Factory() throws IOException, NullPointerException {
        Properties properties = new Properties();
        var reader = new FileReader(getFileName());
        properties.load(reader);

        names = new HashMap<>();
        for (var i : properties.stringPropertyNames()) {
            names.put(i, properties.getProperty(i));
        }
    }

    protected abstract String getFileName();

    public abstract Object createObject(Object desc) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException;

}
