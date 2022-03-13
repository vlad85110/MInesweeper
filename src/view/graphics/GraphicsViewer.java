package view.graphics;

import model.data.Commands;
import view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GraphicsViewer implements Viewer {
    private String action;
    private static long startTime;
    private boolean flag;

    private final JFrame frame;
    private JPanel greetScreen;
    private JPanel levels;
    private JPanel field;
    private final HashMap<String, FieldButton> buttons;

    public GraphicsViewer() {
        frame = new JFrame("Minesweeper");
        buttons = new HashMap<>();
        flag = false;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700,700));
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
        var tmp = action;
        action = null;
        return tmp;
    }

    @Override
    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void getUpdate(Character[][] userView, long time) {
        if (!flag) {
            levels.setVisible(false);
            createField(userView);
            showPanel(field);
            showTime(time);
            flag = true;
        } else {
            for (int i = 0; i < userView.length; i++) {
                for (int j = 0; j < userView.length; j++) {
                    buttons.get(j + " " + i).update(userView[j][i]);
                }
            }
        }
    }

    @Override
    public void showMessage(String message) {
        JFrame frame = new JFrame("");
        var list = message.split("\n");
        var text = new JList<>(list);

        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(text);
        frame.setSize(new Dimension(200,200));
        frame.setVisible(true);
    }

    @Override
    public void askUser(String message) {

    }

    @Override
    public void showGreetScreen() {
        //field.setVisible(false);
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

    private void createField(Character[][] userView) {
        field = new JPanel();
        var listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               action = "open " + e.getActionCommand();
            }
        };
        var mListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    var button = (FieldButton)e.getComponent();
                    if (button.isFlag()) {
                        action = "remove " + button.getPos();
                    } else {
                        action = "set " + button.getPos();
                        button.setFlag(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        for (int i = 0; i < userView.length; i++) {
            for (int j = 0; j < userView.length; j++) {
                var button = createFieldButton(frame.getHeight()/userView.length - 10, listener, i, j);
                field.add(button);
                button.setVisible(true);
                button.addMouseListener(mListener);
            }
        }
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

    private FieldButton createFieldButton(int size, ActionListener listener, Integer i, Integer j) {
        var button = new FieldButton(i.toString() + " " + j.toString());
        button.setPreferredSize(new Dimension(size,size));
        button.setActionCommand(button.getPos());
        button.addActionListener(listener);
        buttons.put(button.getPos(), button);
        return button;
    }

    private void showPanel(JPanel panel) {
        frame.setContentPane(panel);
        panel.setVisible(true);
        frame.setVisible(true);
    }
}
