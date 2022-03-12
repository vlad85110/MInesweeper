package model.data;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    public static HashMap<String, String> commands = new HashMap<>() {
        {
            put("New game", "1");
            put("High scores", "2");
            put("About", "3");
            put("Exit", "4");
            put("Beginner", "Beginner");
            put("Intermediate", "Intermediate");
            put("Expert", "Expert");
        }
    };
}
