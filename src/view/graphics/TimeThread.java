package view.graphics;

import javax.swing.*;
import java.awt.*;

public class TimeThread extends Thread {
    private final long time;

    public TimeThread(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("");
        long tm = 1;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        var list = new JList<>(listModel);
        frame.setSize(new Dimension(200,200));
        frame.add(list);
        frame.setVisible(true);

        long startTime = System.currentTimeMillis();
        while (tm != 0) {
            tm = ((time + startTime) - System.currentTimeMillis());
            String output = String.format("%tM:%tS", tm, tm);
            listModel.addElement(output);
            try {
                Thread.sleep(999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listModel.removeElementAt(0);
        }
    }
}
