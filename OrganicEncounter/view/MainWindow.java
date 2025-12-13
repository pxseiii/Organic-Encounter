package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.*;

/* 
    Purpose: 
    * Main JFrame window
    * involves all UI components
 */

public class MainWindow  {
    private JFrame frame;
    private JPanel mainPanel;

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

        mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);

        // ---------- NORTH BORDER LAYOUT ---------- 
        // effect indicator, factor icons, situation text

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));  // vertical stack
        northPanel.setBackground(new Color(100, 90, 80));

        // --------- effect indicator panel TBA


        // --------- factor panel
        factorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        factorPanel.setBackground(new Color(100, 90, 80));

        // health factor
        ImageIcon healthIcon = new ImageIcon("images/health.png");
        Image scaledHealth = healthIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        healthIconLabel = new JLabel(new ImageIcon(scaledHealth));

        // rep factor
        ImageIcon repIcon = new ImageIcon("images/rep.png");
        Image scaledRep = repIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        repIconLabel = new JLabel(new ImageIcon(scaledRep));
        
        // money factor
        ImageIcon moneyIcon = new ImageIcon("images/money.png");
        Image scaledMoney = moneyIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        moneyIconLabel = new JLabel(new ImageIcon(scaledMoney));

        factorPanel.add(healthIconLabel);
        factorPanel.add(repIconLabel);
        factorPanel.add(moneyIconLabel);

    
        // --------- situation panel
        situationLabel = new JTextPane();
        situationLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        situationLabel.setForeground(Color.BLACK);
        situationLabel.setOpaque(false);
        situationLabel.setEditable(false);
        situationLabel.setFocusable(false);

        JPanel situationPanel = new JPanel(new BorderLayout());
        situationPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        situationPanel.add(situationLabel, BorderLayout.CENTER);
        
        northPanel.add(factorPanel);
        northPanel.add(Box.createRigidArea(new Dimension(0, 5)));       // small vertical gap
        northPanel.add(situationPanel);
        
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // ---------- CENTER BORDER LAYOUT ---------- 
        // card (choices, icon)

        cardPanel = new CardPanel();
        mainPanel.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST); // Left padding
        mainPanel.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST); // Right padding
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        
        

        // ---------- SOUTH BORDER LAYOUT ---------- 
        // charac name & title, day counter

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBackground(new Color(100, 90, 80));


        // --------- charac name & title panel
        characLabel = new JLabel("Character Name | Title", SwingConstants.CENTER);
        characLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        characLabel.setForeground(Color.BLACK);
        characLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel characPanel = new JPanel(new BorderLayout());
        characPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        characPanel.add(characLabel, BorderLayout.CENTER);


        // --------- day counter panel
        dayLabel = new JLabel("DAY 1", SwingConstants.CENTER);
        dayLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.setBackground(new Color(100, 90, 80));
        dayPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        dayPanel.add(dayLabel, BorderLayout.CENTER);

        southPanel.add(characPanel);
        southPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        southPanel.add(dayPanel);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // update ui
    public void update(Card card, Stats stats) {
        if (card == null) {
            situationLabel.setText("No more cards.");
            return;
        }

        // updates situation, character, and counter here since it's not part of the card
        situationLabel.setText(card.getSituation()); // <html><div style='width:360px; text-align:center;'>" + card.getSituation() + "</div></html>"

        // sets the situation text in center
        StyledDocument doc = situationLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        characLabel.setText(card.getTitle());
        dayLabel.setText("DAY " + day++);
        cardPanel.displayCard(card);

        // update factor icons ?
    }

    public void showEnding(Card endingCard){
        situationLabel.setText(endingCard.getSituation());
        cardPanel.displayCard(endingCard);
    }

    public CardPanel getCardPanel(){
        return cardPanel;
    }
}
