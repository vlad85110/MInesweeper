package view.graphics;

import javax.swing.*;

public abstract class AbstractMenuPanel extends JPanel {
    private final GraphicsViewer viewer;
    protected AbstractMenuPanel(GraphicsViewer viewer) {
        super();
        this.viewer = viewer;
    }

    protected void addButton(String name) {
        MenuListener listener = new MenuListener(viewer);
        this.add(new MenuButton(name, listener));
    }
}
