package view;

import annotation.ClassInfo;
import java.awt.*;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "De Guzman",
    className = "EndPanel",
    pillarsUsed = {"Abstraction"},
    solidUsed = {"SRP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * displays end credits screen
    * encapsulates the panel so it'll be accessed by the MainWindow
*/

public class EndPanel {
    // ----------- FIELD --------------
    private JPanel mainPanel;

    // ----------- CONSTRUCTOR --------------
    public EndPanel(){
        mainPanel = new JPanel(null){
            private Image background = new ImageIcon("OrganicEncounter/images/credits.png").getImage();
        
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }

    // ----------- GETTER --------------
    public JPanel getPanel(){
        return mainPanel;
    }
}
