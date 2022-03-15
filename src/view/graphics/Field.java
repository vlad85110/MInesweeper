package view.graphics;

import view.Viewer;

import javax.swing.*;

public class Field extends JPanel {
    private final GraphicsViewer viewer;

    public Field(Character[][] userView, GraphicsViewer viewer) {
        this.viewer = viewer;

        GameListener listener = new GameListener(viewer);
        GameMouseListener mListener = new GameMouseListener(viewer);

        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                var main = viewer.getMain();
                var button = new FieldButton(main.getHeight() / userView.length - 10, listener, i, j);
                button.setActionCommand(button.getPos());

                viewer.getButtons().put(button.getPos(), button);
                this.add(button);
                button.addMouseListener(mListener);

                button.setVisible(true);
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
