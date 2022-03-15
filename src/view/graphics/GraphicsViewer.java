package view.graphics;

import view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphicsViewer implements Viewer {
    private String action;
    private boolean alreadyCreated;

    private final JFrame main;

    private final JPanel greetScreen;
    private final JPanel levels;
    private FieldPanel fieldPanel;
    private Field field;

    private final HashMap<String, FieldButton> buttons;
    private TimeThread timeThread;

    public GraphicsViewer() {
        main = new JFrame("Minesweeper");
        buttons = new HashMap<>();
        alreadyCreated = false;
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLayout(new BorderLayout());

        main.setSize(new Dimension(300,300));
        Location.centreWindow(main);

        greetScreen = new GreetScreen(this);
        levels = new Levels(this);
    }

    @Override
    public String waitAction() {
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (action == null);

        var tmp = action;
        action = null;
        return tmp;
    }

    @Override
    public void startGame() {
        timeThread.start();
    }

    @Override
    public void setAlreadyCreated(boolean alreadyCreated) {
        this.alreadyCreated = alreadyCreated;
    }

    @Override
    public void getUpdate(Character[][] userView, long time) {
        if (!alreadyCreated) {
            levels.setVisible(false);
            fieldPanel = new FieldPanel(userView, this);
            field = fieldPanel.getField();

            showPanel(fieldPanel);
            timeThread = new TimeThread(time, fieldPanel.getTimePanel().getTimeLabel());

            alreadyCreated = true;
        } else {
            field.updateMap(userView);
        }
    }

    @Override
    public void showWarning(String message) {
        if (timeThread != null) {
            timeThread.kill();
        }

        JDialog dialog = new JDialog();
        JOptionPane.showMessageDialog(dialog, message, "message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showMessage(String message) {
        JButton button = new JButton("back");
        JPanel panel = new JPanel();
        button.addActionListener(e -> {
            panel.setVisible(false);
            main.setVisible(true);
            showPanel(greetScreen);
        });

        var list = message.split("\n");
        var label = new JList<>(list);
        panel.add(label);
        label.setVisible(true);
        panel.add(label, BorderLayout.CENTER);

        panel.add(button);
        showPanel(panel);
        timeThread.kill();
    }

    @Override
    public void askUser(String message) {
        JDialog dialog = new JDialog();
        action = JOptionPane.showInputDialog(dialog, message, "message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showGreetScreen() {
        main.setSize(new Dimension(300,300));

        if (timeThread != null) {
            timeThread.kill();
        }

        if (field != null) {
            field.setVisible(false);
        }
        showPanel(greetScreen);
    }

    @Override
    public void showLevelChoosing() {
        main.setSize(new Dimension(300,300));

        if (timeThread != null) {
            timeThread.kill();
        }

        if (field != null) {
            field.setVisible(false);
        }
        greetScreen.setVisible(false);
        showPanel(levels);
    }

    public HashMap<String, FieldButton> getButtons() {
        return buttons;
    }

    private void showPanel(JPanel panel) {
        main.setContentPane(panel);
        panel.setVisible(true);
        main.setVisible(true);
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JFrame getMain() {
        return main;
    }

    public void setMenuBar(JMenuBar menuBar) {
        main.setJMenuBar(menuBar);
        menuBar.setVisible(true);
    }
}
