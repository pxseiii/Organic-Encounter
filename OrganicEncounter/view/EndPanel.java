package view;

import java.awt.*;
import javax.swing.*;

public class EndPanel {
    private JPanel mainPanel;

    public EndPanel(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}
