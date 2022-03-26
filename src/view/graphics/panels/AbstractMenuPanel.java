package view.graphics.panels;

import view.graphics.GraphicsViewer;
import view.graphics.listeners.MenuListener;

import javax.swing.*;

public abstract class AbstractMenuPanel extends JPanel {
    private final GraphicsViewer viewer;

    protected AbstractMenuPanel(GraphicsViewer viewer) {
        this.viewer = viewer;
    }

    protected void addButton(String name) {
        MenuListener listener = new MenuListener(viewer);
        this.add(new MenuButton(name, listener));
    }
}
