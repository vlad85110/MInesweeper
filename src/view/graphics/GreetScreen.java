package view.graphics;

import javax.swing.*;

public class GreetScreen extends AbstractMenuPanel {
    public GreetScreen(GraphicsViewer viewer) {
        super(viewer);
        this.addButton("New game");
        this.addButton("High scores");
        this.addButton("About");
        this.addButton("Exit");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

}
