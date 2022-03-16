package view.graphics.panels.field.time;

import view.graphics.*;
import view.graphics.listeners.GameListener;
import view.graphics.listeners.GameMouseListener;
import view.graphics.listeners.MenuListener;
import view.graphics.panels.field.FieldButton;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    public static final int BUTTON_SIZE = 35;//67

    private final GraphicsViewer viewer;

    public Field(Character[][] userView, GraphicsViewer viewer) {
        Location.centreWindow(this);

        this.viewer = viewer;

        GameListener listener = new GameListener(viewer);
        GameMouseListener mListener = new GameMouseListener(viewer);

        var frameSize = BUTTON_SIZE * userView.length;
        viewer.getMain().setSize(frameSize,  frameSize + 90);
        GridLayout layout = new GridLayout(userView.length, userView.length);
        this.setLayout(layout);

        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                var main = viewer.getMain();
                var button = new FieldButton(BUTTON_SIZE, listener, i, j);
                button.setActionCommand(button.getPos());

                viewer.getButtons().put(button.getPos(), button);
                this.add(button);
                button.addMouseListener(mListener);
            }
        }
    }

    public void updateMap(Character[][] userView) {
        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                viewer.getButtons().get(j + " " + i).update(userView[j][i]);
            }
        }
    }
}
