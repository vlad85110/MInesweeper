package view.graphics;

import javax.swing.*;

public class FieldButton extends JButton {
    private final String pos;
    private boolean flag;

    public FieldButton(String text) {
        super();
        this.setFocusPainted(false);
        this.pos = text;
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
            case 'f', 'b' -> {
                Icon img = new ImageIcon("flag1.png");
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
