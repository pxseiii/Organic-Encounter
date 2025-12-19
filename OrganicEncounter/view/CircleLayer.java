package view;

import annotation.ClassInfo;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "CircleLayer",
    pillarsUsed = {"Encapsulation, Inheritance, Polymorphism"},
    solidUsed = {"SRP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * paints circle indicators
*/

// ----------- OUTER CLASS --------------
public class CircleLayer extends JPanel{
    // ----------- FIELD --------------
    private final List <Circle> circles = new ArrayList<>();


    // ----------- INNER CLASS --------------
    private static class Circle {
        int x, y, radius;
        Color color = Color.WHITE;
        
        // ----------- INNER CLASS CONSTRUCTOR --------------
        Circle(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    // ----------- OUTER CLASS CONSTRUCTOR --------------
    public CircleLayer(){
        setOpaque(false);
        setLayout(null);
    }

    // adds a circle to be displayed in the list
    public void addCircle(int x, int y, int change){
        int radius;
        int absNum = Math.abs(change);
        switch (absNum){
            case 10: radius = 3; break;
            case 20: radius = 7; break;
            default: radius = 0;
        }
        if (radius > 0){
            circles.add(new Circle(x, y, radius));
            repaint();
        }
    }

    // removes all circles in the list
    public void clearCircles(){
        circles.clear();
        repaint();
    }

    // paints all circles in the list
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Circle c : circles){
            g.setColor(Color.WHITE);
            g.fillOval(c.x - c.radius, c.y - c.radius, c.radius * 2, c.radius *2);
        }
    }

}
