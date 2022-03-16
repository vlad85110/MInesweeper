package view.graphics.panels;

import view.graphics.GraphicsViewer;

import javax.swing.*;

public class Levels extends AbstractMenuPanel {
    public Levels(GraphicsViewer viewer) {
        super(viewer);
        this.addButton("Beginner");
        this.addButton("Intermediate");
        this.addButton("Expert");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
