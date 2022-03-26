package view.graphics.panels.field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FieldButton extends JButton {
    private final String pos;
    private boolean flag;

    public FieldButton(int size, ActionListener listener, int i, int j) {
        this.setPreferredSize(new Dimension(size,size));
        this.addActionListener(listener);

        this.setFocusPainted(false);
        this.pos = i + " " + j;
        this.flag = false;
    }

    public String getPos() {
        return pos;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void update(Character sym) {
        switch (sym) {
            case '0' -> {
                this.setEnabled(false);
                this.setIcon(null);
                this.setText("");
            }
            case 'x' -> this.setIcon(null);
            case 'f' -> {
                Icon img = new ImageIcon("icn/flag1.png");
                this.setIcon(img);
            }
            case 'b' -> {
                Icon img = new ImageIcon("icn/bomb.png");
                this.setIcon(img);
            }
            default -> {
                this.setEnabled(false);
                this.setIcon(null);
                this.setText(sym.toString());
            }
        }
    }
}
