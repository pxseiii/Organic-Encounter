package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import model.*;

public class GamePanel  {
    private JPanel mainPanel;

    private JLabel health, rep, money;

    private JLayeredPane factorLayeredPane;
    private CardPanel cardPanel;

    private JTextPane situationLabel;
    private JLabel characLabel;
    private JLabel dayLabel;

    private JLabel healthIconLabel;
    private JLabel repIconLabel;
    private JLabel moneyIconLabel;
    
    private JProgressBar healthBar;
    private JProgressBar repBar;
    private JProgressBar moneyBar;
    
    private Timer animationTimer;
    private int animHealth, animRep, animMoney;
    private int animTargetHealth, animTargetRep, animTargetMoney;
    
    private boolean firstUpdate = true;
    private int day = 1;                    

    public GamePanel(){   
        mainPanel = new JPanel(new BorderLayout());  
        buildUI();
    }
    
    private void buildUI(){                                   
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBackground(Color.WHITE);

        factorLayeredPane = new JLayeredPane();
        factorLayeredPane.setLayout(null);
        factorLayeredPane.setBackground(new Color(0x4e6134));
        factorLayeredPane.setPreferredSize(new Dimension(500, 140));
        factorLayeredPane.setMaximumSize(new Dimension(500, 140));
        factorLayeredPane.setOpaque(true);

        int iconSize = 75;
        int spacing = 100;
        int totalWidth = (3 * iconSize) + (2 * spacing);
        int leftMargin = (500 - totalWidth) / 2;

        healthBar = createVerticalBar();
        healthBar.setBounds(leftMargin, 20, iconSize, iconSize);

        repBar = createVerticalBar();
        repBar.setBounds(leftMargin + iconSize + spacing, 20, iconSize, iconSize);

        moneyBar = createVerticalBar();
        moneyBar.setBounds(leftMargin + 2*iconSize + 2*spacing, 20, iconSize, iconSize);
        
        factorLayeredPane.add(healthBar, Integer.valueOf(1));
        factorLayeredPane.add(repBar, Integer.valueOf(1));
        factorLayeredPane.add(moneyBar, Integer.valueOf(1));
        
        ImageIcon healthIcon = new ImageIcon("images/lp.png");
        Image scaledHealth = healthIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);  
        healthIconLabel = new JLabel(new ImageIcon(scaledHealth));
        healthIconLabel.setBounds(leftMargin, 20, iconSize, iconSize);
        healthIconLabel.setOpaque(false);

        ImageIcon repIcon = new ImageIcon("images/rep.png");
        Image scaledRep = repIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH); 
        repIconLabel = new JLabel(new ImageIcon(scaledRep));
        repIconLabel.setBounds(leftMargin + iconSize + spacing, 20, iconSize, iconSize);
        repIconLabel.setOpaque(false);
        
        ImageIcon moneyIcon = new ImageIcon("images/finance.png");
        Image scaledMoney = moneyIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);  
        moneyIconLabel = new JLabel(new ImageIcon(scaledMoney));
        moneyIconLabel.setBounds(leftMargin + 2*iconSize + 2*spacing, 20, iconSize, iconSize);
        moneyIconLabel.setOpaque(false);
        
        factorLayeredPane.add(healthIconLabel, Integer.valueOf(2));
        factorLayeredPane.add(repIconLabel, Integer.valueOf(2));
        factorLayeredPane.add(moneyIconLabel, Integer.valueOf(2));

        JPanel factorWrapper = new JPanel(new BorderLayout());
        factorWrapper.setBackground(new Color(0x4e6134));
        factorWrapper.add(factorLayeredPane, BorderLayout.CENTER);
        
        northPanel.add(factorWrapper);

        situationLabel = new JTextPane();
        situationLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        situationLabel.setForeground(Color.BLACK);
        situationLabel.setBackground(new Color(0xe3e9d8));
        situationLabel.setOpaque(false);
        situationLabel.setEditable(false);
        situationLabel.setHighlighter(null);
        situationLabel.setSize(430, 120);

        JPanel situationPanel = new JPanel(new BorderLayout());
        situationPanel.setBackground(new Color(0xe3e9d8));
        situationPanel.setBorder(BorderFactory.createEmptyBorder(20,35,20,35));
        Dimension situationSize = new Dimension(500, 120);
        situationPanel.setPreferredSize(situationSize);
        situationPanel.setMinimumSize(situationSize);
        situationPanel.setMaximumSize(situationSize);
        situationPanel.add(situationLabel, BorderLayout.CENTER);
        
        northPanel.add(situationPanel);
        
        mainPanel.add(northPanel, BorderLayout.NORTH);

        cardPanel = new CardPanel();
        cardPanel.setPreferredSize(new Dimension(490, 375));
        cardPanel.setMinimumSize(new Dimension(490, 375));
        cardPanel.setMaximumSize(new Dimension(490, 375));

        JPanel fixedWrapper = new JPanel(new BorderLayout());
        fixedWrapper.setPreferredSize(new Dimension(490, 375));
        fixedWrapper.add(cardPanel, BorderLayout.CENTER);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(0xe3e9d8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(fixedWrapper, gbc);

        mainPanel.add(centerWrapper, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBackground(new Color(0xe3e9d8));

        characLabel = new JLabel("Character Name | Title", SwingConstants.CENTER);
        characLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
        characLabel.setForeground(Color.BLACK);
        characLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel characPanel = new JPanel(new BorderLayout());
        characPanel.setBackground(new Color(0xe3e9d8));
        characPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        characPanel.add(characLabel, BorderLayout.CENTER);

        dayLabel = new JLabel("DAY 1", SwingConstants.CENTER);
        dayLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        dayLabel.setForeground(Color.WHITE);
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.setBackground(new Color(0x4e6134));
        dayPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        dayPanel.add(dayLabel, BorderLayout.CENTER);

        southPanel.add(characPanel);
        southPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        southPanel.add(dayPanel);

        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    private JProgressBar createVerticalBar() {
        JProgressBar bar = new JProgressBar(SwingConstants.VERTICAL);
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(50);
        bar.setForeground(Color.BLACK);
        bar.setBackground(new Color(0xddffdd));
        bar.setBorderPainted(false);
        bar.setStringPainted(false);
        bar.setOpaque(true);
        return bar;
    }

    public void update(Card card, Stats stats) {
        if (card == null) {
            situationLabel.setText("No more cards.");
            return;
        }

        situationLabel.setText(card.getSituation());
        
        StyledDocument doc = situationLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        characLabel.setText(card.getTitle());
        dayLabel.setText("DAY " + day++);
        
        cardPanel.displayCard(card);

        int targetHealth = stats.getHealth();
        int targetRep = stats.getRep();
        int targetMoney = stats.getMoney();

        if (firstUpdate) {
            healthBar.setValue(targetHealth);
            repBar.setValue(targetRep);
            moneyBar.setValue(targetMoney);
            firstUpdate = false;
        } else {
            animHealth = healthBar.getValue();
            animRep = repBar.getValue();
            animMoney = moneyBar.getValue();
            animTargetHealth = targetHealth;
            animTargetRep = targetRep;
            animTargetMoney = targetMoney;
            
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();
            }
            
            animationTimer = new Timer(67, e -> {
                boolean stillAnimating = false;
                
                if (animHealth < animTargetHealth) {
                    animHealth++;
                    healthBar.setValue(animHealth);
                    stillAnimating = true;
                } else if (animHealth > animTargetHealth) { 
                    animHealth--;
                    healthBar.setValue(animHealth);
                    stillAnimating = true;
                }
                
                if (animRep < animTargetRep) {
                    animRep++;
                    repBar.setValue(animRep);
                    stillAnimating = true;
                } else if (animRep > animTargetRep) {
                    animRep--;
                    repBar.setValue(animRep);
                    stillAnimating = true;
                }
                
                if (animMoney < animTargetMoney) {
                    animMoney++;
                    moneyBar.setValue(animMoney);
                    stillAnimating = true;
                } else if (animMoney > animTargetMoney) {
                    animMoney--;
                    moneyBar.setValue(animMoney);
                    stillAnimating = true;
                }
                
                if (!stillAnimating) {
                    ((Timer)e.getSource()).stop();
                }
            });
            
            animationTimer.start();
        }
    }

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
