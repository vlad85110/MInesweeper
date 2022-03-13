package view.graphics;

import javax.swing.*;

public class FieldButton extends JButton {
    private final String pos;

    public FieldButton(Icon icon, String pos) {
        super(icon);
        this.pos = pos;
    }

    public FieldButton(String text) {
        super();
        this.setFocusPainted(false);
        this.pos = text;
    }

    public String getPos() {
        return pos;
    }

    public void update(Character sym) {
        switch (sym) {
            case '0':
                this.setEnabled(false);
                this.setText("");
                break;
            case 'x':
                break;
            case  'f':
                this.setText(sym.toString());
                break;
            default:
                this.setEnabled(false);
                this.setText(sym.toString());
                break;
        }
    }
}
