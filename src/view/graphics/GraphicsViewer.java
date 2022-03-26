package view.graphics;

import model.data.UpdateData;
import view.Viewer;
import view.graphics.panels.ScoreList;
import view.graphics.panels.field.FieldPanel;
import view.graphics.panels.Levels;
import view.graphics.panels.GreetScreen;
import view.graphics.panels.field.Field;
import view.graphics.panels.field.time.TimeThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicsViewer implements Viewer {
    private String action;
    private boolean alreadyCreated;
    private boolean interruptFlag;

    private final JFrame main;

    private final JPanel greetScreen;
    private final JPanel levels;
    private FieldPanel fieldPanel;

    private TimeThread timeThread;

    public GraphicsViewer() {
        main = new JFrame("Minesweeper");
        alreadyCreated = false;
        interruptFlag = false;
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());

        main.setSize(new Dimension(300,300));
        Location.centreWindow(main);

        greetScreen = new GreetScreen(this);
        levels = new Levels(this);
    }

    @Override
    public String waitAction() {
        interruptFlag = false;
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (action == null && !interruptFlag);

        var tmp = action;
        action = null;
        return tmp;
    }

    @Override
    public void startGame() {
        timeThread.start();
    }

    @Override
    public void endGame() {
        if (timeThread != null) {
            timeThread.kill();
        }
    }

    @Override
    public void setAlreadyCreated(boolean alreadyCreated) {
        this.alreadyCreated = alreadyCreated;

    }

    @Override
    public void getUpdate(UpdateData data, long time) {
        if (!alreadyCreated) {
            levels.setVisible(false);
            fieldPanel = new FieldPanel(data, this);

            showPanel(fieldPanel);
            Location.centreWindow(main);

            timeThread = new TimeThread(time, fieldPanel.getTextPanel().getTimeLabel());

            alreadyCreated = true;
        } else {
            fieldPanel.updateMap(data);
        }
    }

    @Override
    public void showWarning(String message) {
        JDialog dialog = new JDialog();
        JOptionPane.showMessageDialog(dialog, message, "message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showList(String message) {
        ScoreList panel = new ScoreList(message, this);
        showPanel(panel);
    }

    @Override
    public void showText(String message) {
        JButton button = new JButton("back");
        JPanel panel = new JPanel();
        button.addActionListener(e -> {
            panel.setVisible(false);
            main.setVisible(true);
            showPanel(greetScreen);
        });

        var label = new JLabel();
        label.setText(message);
        panel.add(label, BorderLayout.CENTER);
        label.setVisible(true);

        panel.add(button);
        showPanel(panel);
    }

    @Override
    public void askUser(String message) {
        JDialog dialog = new JDialog();
        action = JOptionPane.showInputDialog(dialog, message, "message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showGreetScreen() {
        if (fieldPanel != null) {
            fieldPanel.setVisible(false);
        }
        showPanel(greetScreen);
    }

    @Override
    public void showLevelChoosing() {
        if (fieldPanel != null) {
            fieldPanel.setVisible(false);
        }
        showPanel(levels);
    }

    private void showPanel(JPanel panel) {
        main.setContentPane(panel);
        panel.setVisible(true);
        main.setVisible(true);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setInterruptFlag(boolean interruptFlag) {
        this.interruptFlag = interruptFlag;
    }

    public void setSize(Dimension size) {
        main.setSize(size);
    }

    public void setMenuBar(JMenuBar menuBar) {
        main.setJMenuBar(menuBar);
    }
}