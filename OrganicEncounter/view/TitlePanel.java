package view;

import annotation.ClassInfo;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "Mika",
    className = "TitlePanel",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP"}
)

public class TitlePanel{
    private JPanel mainPanel;
    private JButton startButton;

    public TitlePanel(){
        mainPanel = new JPanel(null){
            private Image background = new ImageIcon("images/bg_flat_v1_colored.png").getImage();
        
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };

        int width=220;
        int height = 56;

        ImageIcon buttonDefault = new ImageIcon("images/normal.png");
        Image scaledButt = buttonDefault.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonPressed = new ImageIcon("images/pressed.png");
        Image scaledButtP = buttonPressed.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonHover = new ImageIcon("images/hovered.png");
        Image scaledButtH = buttonHover.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        startButton = new JButton(new ImageIcon(scaledButt));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);

        startButton.setRolloverIcon(new ImageIcon(scaledButtP));
        startButton.setPressedIcon(new ImageIcon(scaledButtH));

        int iconSize = 295;

        ImageIcon icon = new ImageIcon("images/logo.png");
        Image scaledIcon = icon.getImage().getScaledInstance(iconSize,iconSize+15, Image.SCALE_SMOOTH);
        
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));
        iconLabel.setBounds(95, 50,iconSize ,iconSize);


        startButton.setBounds(135, 360, width, height);
        //icon.setBounds(125, 0, 250,250);

        mainPanel.add(startButton);
        mainPanel.add(iconLabel);
        mainPanel.setBackground(new Color(0xe3e9d8));
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    // expose start action
    public void setStartListener(ActionListener listener){
        startButton.addActionListener(listener);
    }
}
