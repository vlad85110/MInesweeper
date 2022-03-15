package view.graphics;

import javax.swing.*;

public class TimePanel extends JPanel {
    private final JLabel timeLabel;

    public TimePanel() {
        timeLabel = new JLabel();
        this.add(timeLabel);
    }

    public void setText(String text) {
        timeLabel.setText(text);
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }
}
