package view.graphics.panels.field;

import javax.swing.*;

public class TextPanel extends JPanel {
    private final JLabel timeLabel;
    private final JLabel flagLabel;

    public TextPanel() {
        timeLabel = new JLabel();
        flagLabel = new JLabel();
        this.add(timeLabel);
        this.add(flagLabel);
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JLabel getFlagLabel() {
        return flagLabel;
    }
}
