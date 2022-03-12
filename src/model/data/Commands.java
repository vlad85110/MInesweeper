package model.data;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    public static HashMap<String, String> commands = new HashMap<>() {
        {
            put("1", "Beginner");
            put("2", "Intermediate");
            put("3", "Expert");
        }
    };
}
