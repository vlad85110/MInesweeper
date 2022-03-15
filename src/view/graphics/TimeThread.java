package view.graphics;

import javax.swing.*;
import java.awt.*;

public class TimeThread extends Thread {
    private final long time;

    public TimeThread(long time) {
        this.time = time;
    }

    @Override
    public synchronized void run() {
        JFrame frame = new JFrame("");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        var list = new JList<>(listModel);
        frame.setSize(new Dimension(200,200));
        frame.add(list);
        frame.setVisible(true);

        long startTime = System.currentTimeMillis();
        long prevTime = startTime;
        long tm = 1;

        String output = String.format("%tM:%tS", time, time);
        listModel.addElement(output);

        while (tm != 0) {
            if (((System.currentTimeMillis() - prevTime)) / 1000 >= 1) {
                prevTime = System.currentTimeMillis();
                tm = ((time + startTime) - System.currentTimeMillis());

                listModel.removeAllElements();
                output = String.format("%tM:%tS", tm, tm);
                listModel.addElement(output);
            }

        }
    }
}
