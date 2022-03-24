package view.graphics.panels.field;

import view.graphics.Location;
import view.graphics.listeners.MenuListener;
import view.graphics.GraphicsViewer;
import view.graphics.panels.field.time.TimePanel;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private final Field field;
    private final TimePanel timePanel;
    private final JMenuBar menuBar;

    public FieldPanel(Character[][] userView, GraphicsViewer viewer) {
        Location.centreWindow(this);

        menuBar = new JMenuBar();
        var options = new JMenu("options");
        var restart = new JMenuItem("restart");
        var exit = new JMenuItem("exit");
        var main = new JMenuItem("menu");

        restart.setActionCommand(getName());
        restart.addActionListener(new MenuListener(viewer));

        exit.setActionCommand(getName());
        exit.addActionListener(new MenuListener(viewer));

        main.setActionCommand(getName());
        main.addActionListener(new MenuListener(viewer));

        options.add(restart);
        options.add(main);
        options.add(exit);

        menuBar.add(options);
        viewer.setMenuBar(menuBar);

        timePanel = new TimePanel();
        field = new Field(userView, viewer);

        this.add(timePanel, BorderLayout.LINE_START);
        this.add(field,BorderLayout.CENTER);
    }

    public void setTime (String time) {
        timePanel.setText(time);
    }

    public TimePanel getTimePanel() {
        return timePanel;
    }

    public Field getField() {
        return field;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        menuBar.setVisible(aFlag);
    }
}
