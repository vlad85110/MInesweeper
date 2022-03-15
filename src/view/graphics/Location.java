package view.graphics;

import java.awt.*;

public class Location {
    public static void centreWindow(Component frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(dimension);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
