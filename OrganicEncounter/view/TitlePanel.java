package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TitlePanel{
    private JPanel mainPanel;
    private JButton startButton;

    public TitlePanel(){
        mainPanel = new JPanel(null){
            private Image background = new ImageIcon("OrganicEncounter/images/bg_flat_v1_colored.png").getImage();
        
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        ImageIcon buttonDefault = new ImageIcon("OrganicEncounter/images/1.png");
        ImageIcon buttonPressed = new ImageIcon("OrganicEncounter/images/2.png");
        // ImageIcon buttonHover = new ImageIcon("");

        startButton = new JButton(buttonDefault);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        // startButton.setRolloverIcon(buttonHover);
        startButton.setPressedIcon(buttonPressed);

        startButton.setBounds(150, 500, 200, 75);

        mainPanel.add(startButton);
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    // expose start action
    public void setStartListener(ActionListener listener){
        startButton.addActionListener(listener);
    }
}
