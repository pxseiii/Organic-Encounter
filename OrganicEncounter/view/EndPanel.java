package view;

import annotation.ClassInfo;
import java.awt.*;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "Miks",
    className = "EndPanel",
    pillarsUsed = {"Abstraction"},
    solidUsed = {"SRP"}
)

public class EndPanel {
    private JPanel mainPanel;

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

    public JPanel getPanel(){
        return mainPanel;
    }
}
