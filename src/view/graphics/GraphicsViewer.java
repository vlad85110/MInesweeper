package view.graphics;

import view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphicsViewer implements Viewer {
    private String action;
    private static long startTime;
    private boolean flag;

    private final JFrame main;
    private final JPanel greetScreen;
    private final JPanel levels;
    private Field field;
    private final HashMap<String, FieldButton> buttons;

    public GraphicsViewer() {
        main = new JFrame("Minesweeper");
        buttons = new HashMap<>();
        flag = false;
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setSize(new Dimension(700,700));
        main.setLocation(100, 100);

        greetScreen = new GreetScreen(this);
        levels = new Levels(this);
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
        var tmp = action;
        action = null;
        return tmp;
    }

    @Override
    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void getUpdate(Character[][] userView, long time) {
        if (!flag) {
            levels.setVisible(false);
            field = new Field(userView, this);
            showPanel(field);
            //showTime(time);
            flag = true;
        } else {
            field.updateMap(userView);
        }
    }

    @Override
    public void showMessage(String message) {
        JDialog dialog = new JDialog();

        //dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });*/
        //var list = message.split("\n");
        //var text = new JList<>(list);
        JOptionPane.showMessageDialog(dialog, message, "message", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void askUser(String message) {

    }

    @Override
    public void showGreetScreen() {
        showPanel(greetScreen);
    }

    @Override
    public void showLevelChoosing() {
        greetScreen.setVisible(false);
        showPanel(levels);
    }

    @Override
    public void showTime(long time) {
        Thread sub = new TimeThread(time);
        sub.start();
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
}
