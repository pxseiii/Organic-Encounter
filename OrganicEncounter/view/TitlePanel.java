package view;

import annotation.ClassInfo;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "De Guzman",
    className = "TitlePanel",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * displays title screen with start button
    * encapsulates the panel so it'll be accessed by the MainWindow
*/

public class TitlePanel{
    // ----------- FIELDS --------------
    private JPanel mainPanel;
    private JButton startButton;

    // ----------- CONSTRUCTOR --------------
    public TitlePanel(){
        mainPanel = new JPanel(null){
            private Image background = new ImageIcon("OrganicEncounter/images/bg_flat_v1_colored.png").getImage();
        
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        int width=220;
        int height = 56;

        ImageIcon buttonDefault = new ImageIcon("OrganicEncounter/images/normal.png");
        Image scaledButt = buttonDefault.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonPressed = new ImageIcon("OrganicEncounter/images/pressed.png");
        Image scaledButtP = buttonPressed.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonHover = new ImageIcon("OrganicEncounter/images/hovered.png");
        Image scaledButtH = buttonHover.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        startButton = new JButton(new ImageIcon(scaledButt));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);

        startButton.setRolloverIcon(new ImageIcon(scaledButtP));
        startButton.setPressedIcon(new ImageIcon(scaledButtH));

        int iconSize = 295;

        ImageIcon icon = new ImageIcon("OrganicEncounter/images/logo.png");
        Image scaledIcon = icon.getImage().getScaledInstance(iconSize,iconSize+15, Image.SCALE_SMOOTH);
        
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));
        iconLabel.setBounds(95, 50,iconSize ,iconSize);


        startButton.setBounds(135, 360, width, height);
        //icon.setBounds(125, 0, 250,250);

        mainPanel.add(startButton);
        mainPanel.add(iconLabel);
        mainPanel.setBackground(new Color(0xe3e9d8));
    }

    // ----------- GETTER --------------
    public JPanel getPanel(){
        return mainPanel;
    }

    // set up listener
    public void setStartListener(ActionListener listener){
        startButton.addActionListener(listener);
    }
}
