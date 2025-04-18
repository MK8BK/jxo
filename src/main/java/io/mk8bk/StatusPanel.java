package io.mk8bk;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private final JLabel textLabel;
    public StatusPanel(){
        textLabel = new JLabel("Turn of X");
        textLabel.setFont(new Font(null, Font.BOLD,20));
        add(textLabel);
    }
    public void updateLabel(String text) {
        textLabel.setText(text);
        repaint();
    }
}
