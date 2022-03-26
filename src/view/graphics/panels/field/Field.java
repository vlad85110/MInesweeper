package view.graphics.panels.field;

import view.graphics.GraphicsViewer;
import view.graphics.Location;
import view.graphics.listeners.GameListener;
import view.graphics.listeners.GameMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Field extends JPanel {
    public static final int BUTTON_SIZE = 45;
    private final HashMap<String, FieldButton> buttons;

    public Field(Character[][] userView, GraphicsViewer viewer) {
        Location.centreWindow(this);

        buttons = new HashMap<>();

        GameListener listener = new GameListener(viewer);
        GameMouseListener mListener = new GameMouseListener(viewer);

        var frameSize = BUTTON_SIZE * userView.length;
        viewer.setSize(new Dimension(frameSize,  frameSize + 90));
        GridLayout layout = new GridLayout(userView.length, userView.length);
        this.setLayout(layout);

        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                var button = new FieldButton(BUTTON_SIZE, listener, i, j);
                button.setActionCommand(button.getPos());

                buttons.put(button.getPos(), button);
                this.add(button);
                button.addMouseListener(mListener);
            }
        }
    }

    public void updateMap(Character[][] userView) {
        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                buttons.get(j + " " + i).update(userView[j][i]);
            }
        }
    }
}
