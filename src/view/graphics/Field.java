package view.graphics;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    public static final int BUTTON_SIZE = 50;//67

    private final GraphicsViewer viewer;
    private final JMenuBar menuBar;

    public Field(Character[][] userView, GraphicsViewer viewer) {
        Location.centreWindow(this);

        this.viewer = viewer;

        GameListener listener = new GameListener(viewer);
        GameMouseListener mListener = new GameMouseListener(viewer);

        menuBar = new JMenuBar();
        var menu = new JMenu("menu");
        var restart = new JMenuItem("restart");
        var exit = new JMenuItem("exit");

        restart.setActionCommand(getName());
        restart.addActionListener(new MenuListener(viewer));

        exit.setActionCommand(getName());
        exit.addActionListener(new MenuListener(viewer));

        menu.add(restart);
        menu.add(exit);
        menuBar.add(menu);
        viewer.setMenuBar(menuBar);

        var frameSize = BUTTON_SIZE * userView.length;
        viewer.getMain().setSize(frameSize,  frameSize + 30);
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

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        menuBar.setVisible(aFlag);
    }
}
