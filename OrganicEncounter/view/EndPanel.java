package view;

import java.awt.*;
import javax.swing.*;

public class EndPanel {
    private JPanel mainPanel;

    public EndPanel(){
        //timer
        

        mainPanel = new JPanel(null){
            private Image background = new ImageIcon("images/credits.png").getImage();
        
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
