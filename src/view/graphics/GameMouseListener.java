package view.graphics;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public record GameMouseListener(GraphicsViewer viewer) implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            var button = (FieldButton) e.getComponent();
            if (!button.isEnabled()) return;

            if (button.isFlag()) {
                viewer.setAction("remove " + button.getPos());
                button.setFlag(false);
            } else {
                viewer.setAction("set " + button.getPos());
                button.setFlag(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
