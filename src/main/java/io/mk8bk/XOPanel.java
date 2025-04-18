package io.mk8bk;

import javax.swing.*;
import java.awt.*;

public class XOPanel extends JPanel {

    /* gridCellWidth **/
    private final int gcw = 200;

    private int[][] state;
    protected int currentPlayer = 1; // 1:X 2:O
    protected int winner = 0; // 1: X 2:O

    public XOPanel() {
        state = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        setPreferredSize(new Dimension(3 * gcw, 3 * gcw));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.drawLine(gcw, 0, gcw, 3 * gcw);
        g2.drawLine(2 * gcw, 0, 2 * gcw, 3 * gcw);
        g2.drawLine(0, gcw, 3 * gcw, gcw);
        g2.drawLine(0, 2 * gcw, 3 * gcw, 2 * gcw);

        BasicStroke largeStroke = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,10.0f);
        BasicStroke smallStroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,10.0f);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) continue;
                if (state[i][j] == 1) { // X player
                    g2.setColor(Color.BLUE);
                    g2.setStroke(largeStroke);
                    g2.drawLine(gcw * i + gcw / 4, gcw * j + gcw / 4, gcw * (i + 1) - gcw / 4, gcw * (j + 1) - gcw / 4);
                    g2.drawLine(gcw * (i + 1) - gcw / 4, gcw * j + gcw / 4, gcw * i + gcw / 4, gcw * (j + 1) - gcw / 4);
                } else if (state[i][j] == 2) { // O player
                    g2.setColor(Color.GREEN);
                    g2.setStroke(largeStroke);
                    g2.drawOval(i * gcw + gcw / 8, j * gcw + gcw / 8, gcw * 3 / 4, gcw * 3 / 4);
                }else
                    System.out.println("bruh");
            }
        }
        g2.setColor(Color.RED);
        g2.setStroke(smallStroke);
        for (int i = 0; i < 3; i++) {
            if (state[0][i] != 0 && state[0][i] == state[1][i] && state[0][i] == state[2][i]) {
                g2.drawLine(0, (int) ((i + 0.5) * gcw), gcw * 3, (int) ((i + 0.5) * gcw));
                break;
            }
            if (state[i][0] != 0 && state[i][0] == state[i][1] && state[i][0] == state[i][2]) {
                g2.drawLine((int) ((i + 0.5) * gcw), 0, (int) ((i + 0.5) * gcw), gcw * 3);
                break;
            }
        }
        if (state[0][0] != 0 && state[0][0] == state[1][1] && state[0][0] == state[2][2]) {
            g2.drawLine(0, 0, 3 * gcw, 3 * gcw);
        }
        else if (state[0][2] != 0 && state[0][2] == state[1][1] && state[0][2] == state[2][0]) {
            g2.drawLine(0, 3 * gcw, 3 * gcw, 0);
        }
    }

    public void handleClick(int x, int y) {
        if (winner != 0) return;
        x /= 200;
        y /= 200;
        if (x > 2 || x < 0 || y > 2 || y < 0) {
            System.out.println("invalid click location");
            return;
        }
        if (state[x][y] != 0) // do nothing
            return;
        state[x][y] = currentPlayer;
        currentPlayer = 3 - currentPlayer;

        for (int i = 0; i < 3; i++) {
            if (state[0][i] != 0 && state[0][i] == state[1][i] && state[0][i] == state[2][i]) {
                //g.drawLine(0, (int) ((i + 0.5) * gcw), gcw * 3, (int) ((i + 0.5) * gcw));
                winner = state[0][i];
                break;
            }
            if (state[i][0] != 0 && state[i][0] == state[i][1] && state[i][0] == state[i][2]) {
                //g.drawLine((int) ((i + 0.5) * gcw), 0, (int) ((i + 0.5) * gcw), gcw * 3);
                winner = state[i][0];
                break;
            }
        }
        if (state[0][0] != 0 && state[0][0] == state[1][1] && state[0][0] == state[2][2]) {
            //g.drawLine(0, 0, 3 * gcw, 3 * gcw);
            winner = state[0][0];
        }
        else if (state[0][2] != 0 && state[0][2] == state[1][1] && state[0][2] == state[2][0]) {
            //g.drawLine(0, 3 * gcw, 3 * gcw, 0);
            winner = state[0][0];
        }
        repaint();
    }
}
