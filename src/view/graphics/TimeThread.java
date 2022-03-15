package view.graphics;

import javax.swing.*;

public class TimeThread extends Thread {
    private final long time;
    private boolean flag;
    private final JLabel label;
    private String output;

    public TimeThread(long time, JLabel label) {
        this.time = time;
        this.label = label;

        output = String.format("%tM:%tS", time, time);
        label.setText(output);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long prevTime = startTime;
        long tm = 1;


        flag = false;
        while (tm != 0 && !flag) {
            if (((System.currentTimeMillis() - prevTime)) / 1000 >= 1) {
                prevTime = System.currentTimeMillis();
                tm = ((time + startTime) - System.currentTimeMillis());
                output = String.format("%tM:%tS", tm, tm);
                label.setText(output);
            }
        }
    }

    public void kill() {
        flag = true;
    }
}
