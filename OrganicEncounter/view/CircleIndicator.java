package view;

import java.awt.*;
import javax.swing.*;

public class CircleIndicator extends JComponent {
    private int radius = 15;

    public CircleIndicator() {
        setVisible(false);
        setOpaque(false);
        setSize(radius * 2, radius * 2);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        setSize(radius * 2, radius * 2);
        repaint();
    }

    public void moveTo(int x, int y) {
        setBounds(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 0, 0, 180));
        g.fillOval(0, 0, radius * 2, radius * 2);
    }
}
