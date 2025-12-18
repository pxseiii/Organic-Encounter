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

    // game fields
    private JPanel effectPanel;
    private JLabel health;
    private JLabel rep;
    private JLabel money;

    private JPanel factorPanel;
    private CardPanel cardPanel;            // swipable card panel

    private JTextPane situationLabel;
    private JLabel characLabel;
    private JLabel dayLabel;

    private JLabel healthIconLabel;
    private JLabel repIconLabel;
    private JLabel moneyIconLabel;

    private int day = 1;                    

    // constructor
    public MainWindow(){                                        
        // ---------- MAIN WINDOW ----------
        frame = new JFrame("Organic Encounter");
        frame.setSize(500, 750);
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
}
