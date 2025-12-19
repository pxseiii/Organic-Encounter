package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// radius 5 for +10 or -10 
// radius 7 for +20 or -20 

public class CircleLayer extends JPanel{
    private static class Circle {
        int x, y, radius;
        Color color = Color.WHITE;

        Circle(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    private final List <Circle> circles = new ArrayList<>();

    public CircleLayer(){
        setOpaque(false);
        setLayout(null);
    }


    // adds a circle to be displayed
    public void addCircle(int x, int y, int change){
        int radius;
        int absNum = Math.abs(change);
        switch (absNum){
            case 10: radius = 5; break;
            case 20: radius = 7; break;
            default: radius = 0;
        }
        if (radius > 0){
            circles.add(new Circle(x, y, radius));
            repaint();
        }
    }


    // removes all circles
    public void clearCircles(){
        circles.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Circle c : circles){
            g.setColor(Color.RED);
            g.fillOval(c.x - c.radius, c.y - c.radius, c.radius * 2, c.radius *2);
        }
    }

}
