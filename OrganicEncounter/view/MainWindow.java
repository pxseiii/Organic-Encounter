package view;

import java.awt.*;
import javax.swing.*;

/* 
    Purpose: 
    * for switching
 */

public class MainWindow  {
    private JFrame frame;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    // screens
    private TitlePanel titlePanel;
    private GamePanel gamePanel;
    private EndPanel endPanel;

    // constructor
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

    public void showTitleScreen() { cardLayout.show(mainPanel, "TITLE"); }
    public void showGameScreen() { cardLayout.show(mainPanel, "GAME"); }
    public void showEndingScreen() { cardLayout.show(mainPanel, "END"); }

    public TitlePanel getTitlePanel() { return titlePanel; };
    public GamePanel getGamePanel() { return gamePanel; };
    public EndPanel getEndPanel() { return endPanel; };

    public void fadeToEndScreen() {
        PanelFader fader = new PanelFader(mainPanel);
        fader.setFromTo("GAME", "END");
        fader.actionPerformed(null);  // trigger fade
    }

    public JPanel getMainPanel() {
    return mainPanel;
}


}