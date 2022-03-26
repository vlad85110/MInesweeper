package view.graphics.panels.field;

import model.data.UpdateData;
import view.graphics.listeners.MenuListener;
import view.graphics.GraphicsViewer;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private final Field field;
    private final TextPanel textPanel;
    private final GraphicsViewer viewer;

    public FieldPanel(UpdateData data, GraphicsViewer viewer) {
        this.viewer = viewer;

        JMenuBar menuBar = new JMenuBar();
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

        textPanel = new TextPanel();
        textPanel.getFlagLabel().setText("flags" + data.flags().toString());
        field = new Field(data.view(), viewer);

        this.add(textPanel, BorderLayout.LINE_START);
        this.add(field,BorderLayout.CENTER);
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public Field getField() {
        return field;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (!aFlag) {
            viewer.setMenuBar(null);
        }
    }

    public void updateMap(UpdateData data) {
        field.updateMap(data.view());
        textPanel.getFlagLabel().setText("flags" + data.flags().toString());
    }
}
