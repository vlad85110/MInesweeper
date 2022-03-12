package view.graphics;

import model.data.Commands;
import view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsViewer implements Viewer {
    private final JFrame frame;
    private String action;
    private static long startTime;
    private JPanel greetScreen;
    private JPanel levels;
    //private final ActionListener listener;

    public GraphicsViewer() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700,700));
        frame.setVisible(true);
        createGreetScreen();
        createLevels();

    }

    @Override
    public String waitAction() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (action != null) {
                break;
            }
        }
        return action;
    }

    @Override
    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void getUpdate(Character[][] userView, long time) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void askUser(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showWarningMessage(String message) {

    }

    @Override
    public void showGreetScreen() {
        //Todo clear
        showPanel(greetScreen);
    }

    @Override
    public void showWinMessage() {

    }

    @Override
    public void showLoseMessage() {

    }

    @Override
    public void showLevelChoosing() {
        greetScreen.setVisible(false);
        showPanel(levels);
    }

    @Override
    public void showTime(long time) {

    }

    private void createGreetScreen() {
        greetScreen = new JPanel();
        greetScreen.add(createMenuButton("New game"));
        greetScreen.add(createMenuButton("High scores"));
        greetScreen.add(createMenuButton("About"));
        greetScreen.add(createMenuButton("Exit"));
        greetScreen.setLayout(new BoxLayout(greetScreen, BoxLayout.Y_AXIS));
    }

    private void createLevels() {
        levels = new JPanel();
        levels.add(createMenuButton("Beginner"));
        levels.add(createMenuButton("Intermediate"));
        levels.add(createMenuButton("Expert"));
        levels.setLayout(new BoxLayout(levels, BoxLayout.Y_AXIS));
    }

    private JButton createMenuButton(String caption) {
        var button = new JButton(caption);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(Commands.commands.get(caption));
        var listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = e.getActionCommand();
            }
        };
        button.addActionListener(listener);
        return button;
    }

    private void showPanel(JPanel panel) {
        frame.setContentPane(panel);
        panel.setVisible(true);
    }
}
