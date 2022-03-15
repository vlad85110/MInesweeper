package view.graphics;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private final Field field;
    private final TimePanel timePanel;

    public FieldPanel(Character[][] userView, GraphicsViewer viewer) {
        field = new Field(userView, viewer);
        timePanel = new TimePanel();

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
}
