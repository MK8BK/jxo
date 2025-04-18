package io.mk8bk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XOFrame extends JFrame {
    private final XOPanel xopanel;
    private final StatusPanel statusPanel;
    class XOMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(mouseEvent.getButton()==MouseEvent.BUTTON1){
                xopanel.handleClick(mouseEvent.getX(), mouseEvent.getY());
                if(xopanel.winner!=0) {
                    statusPanel.updateLabel("Winner: " + ((xopanel.winner == 1) ? "X" : "O"));
                    return;
                }
                if(xopanel.currentPlayer==1)
                    statusPanel.updateLabel("Turn of X");
                else if(xopanel.currentPlayer==2)
                    statusPanel.updateLabel("Turn of O");
            }
        }
    }
    public XOFrame(){
        XOMouseListener xoMouseListener = new XOMouseListener();
        statusPanel = new StatusPanel();
        xopanel = new XOPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(statusPanel, BorderLayout.NORTH);
        add(xopanel, BorderLayout.CENTER);
        xopanel.addMouseListener(xoMouseListener);
        statusPanel.addMouseListener(xoMouseListener);
        pack();
        setResizable(false);
    }

}
