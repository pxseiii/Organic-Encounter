package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import model.*;

/* 
    Purpose: 
    * Main JFrame window
    * involves all UI components
 */

public class GamePanel  {
    private JPanel mainPanel;

    private JLabel health, rep, money;

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
    public GamePanel(){   
        mainPanel = new JPanel(new BorderLayout());  
        buildUI();
    }
    private void buildUI(){                                   
        // ---------- NORTH BORDER LAYOUT ---------- 
        // effect indicator, factor icons, situation text

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));  // vertical stack
        northPanel.setBackground(new Color(100, 90, 80));

        // --------- effect indicator panel TBA
        JPanel effectPanel = new JPanel(new FlowLayout());
        health = new JLabel("Health: 50");
        rep = new JLabel("Rep: 50");
        money = new JLabel("Money: 50");
        
        effectPanel.add(health);
        effectPanel.add(rep);
        effectPanel.add(money);

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

        JPanel situationPanel = new JPanel(new BorderLayout());
        situationPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        situationPanel.add(situationLabel, BorderLayout.CENTER);
        
        northPanel.add(effectPanel);
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
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    // update ui if there's still cards
    public void update(Card card, Stats stats) {
        if (card == null) {
            situationLabel.setText("No more cards.");
            return;
        }

        // updates situation, character, and counter here since it's not part of the card
        health.setText("health: " + stats.getHealth());
        rep.setText("rep: " + stats.getRep());
        money.setText("money: " + stats.getMoney());
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

    // show ending card
    public void showEndingCard(Card endingCard){
        if (endingCard == null) return;
        
        characLabel.setText("");
        situationLabel.setText(endingCard.getSituation()); 
        
        StyledDocument doc = situationLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        cardPanel.displayCard(endingCard);
    }

    public CardPanel getCardPanel(){
        return cardPanel;
    }
}
