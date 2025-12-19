package view;

import annotation.ClassInfo;
import java.awt.*;
import javax.swing.*;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "MainWindow",
    pillarsUsed = {"Encapsulation, Inheritance"},
    solidUsed = {"SRP, OCP"}
)
/* 
    Description / Author Comments

    Purpose: 
    * switching panels
*/

public class MainWindow  {
    // ----------- FIELDS --------------
    private JFrame frame;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    // screens
    private TitlePanel titlePanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;

    // ----------- CONSTRUCTOR --------------
    public MainWindow(){                                        
        // ---------- MAIN WINDOW ----------
        frame = new JFrame("Organic Encounter");
        frame.setSize(500, 770);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        titlePanel = new TitlePanel();
        gamePanel = new GamePanel();
        endPanel = new EndPanel();

        mainPanel.add(titlePanel.getPanel(), "TITLE");
        mainPanel.add(gamePanel.getPanel(), "GAME");
        mainPanel.add(endPanel.getPanel(), "END");
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // ---------- METHODS ----------
    public void showTitleScreen() { cardLayout.show(mainPanel, "TITLE"); }
    public void showGameScreen() { cardLayout.show(mainPanel, "GAME"); }
    public void showEndingScreen() { cardLayout.show(mainPanel, "END"); }

    // ---------- GETTERS ----------
    public TitlePanel getTitlePanel() { return titlePanel; };
    public GamePanel getGamePanel() { return gamePanel; };
    public EndPanel getEndPanel() { return endPanel; };

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
