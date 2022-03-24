package view.graphics.panels;

import view.Viewer;
import view.graphics.GraphicsViewer;

import javax.swing.*;

public class ScoreList extends JPanel {
    public ScoreList(String message, GraphicsViewer viewer) {
        if (message.equals("")) {
            message = "no data";
        }

        JButton button = new JButton("back");
        JScrollPane pane = new JScrollPane();
        button.addActionListener(e -> {
            this.setVisible(false);
            viewer.showGreetScreen();
        });

        var list = message.split("\n");
        var label = new JList<>(list);
        pane.setViewportView(label);
        this.add(pane);

        this.add(button);
    }
}
