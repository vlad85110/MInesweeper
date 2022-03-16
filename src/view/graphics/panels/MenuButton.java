package view.graphics.panels;

import model.data.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {
    public MenuButton(String text, ActionListener listener) {
        super(text);
        this.setFocusPainted(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setActionCommand(Commands.commands.get(text));
        this.addActionListener(listener);
    }
}
