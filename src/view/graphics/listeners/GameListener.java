package view.graphics.listeners;

import view.graphics.GraphicsViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record GameListener(GraphicsViewer viewer) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        viewer.setAction("open " + e.getActionCommand());
    }
}
