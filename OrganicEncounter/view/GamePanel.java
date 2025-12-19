package view;

import java.awt.*;
import javax.swing.*;
import model.*;

public class GamePanel {

    private JPanel mainPanel;

    private JLabel health, rep, money;

    private JLayeredPane factorLayeredPane;
    private CardPanel cardPanel;

    private JTextArea situationLabel;
    private JLabel characLabel;
    private JLabel dayLabel;

    private JLabel healthIconLabel;
    private JLabel repIconLabel;
    private JLabel moneyIconLabel;

    private JProgressBar healthBar;
    private JProgressBar repBar;
    private JProgressBar moneyBar;

    private CircleLayer circleLayer;

    private Timer animationTimer;
    private int animHealth, animRep, animMoney;
    private int animTargetHealth, animTargetRep, animTargetMoney;

    private boolean firstUpdate = true;
    private int day = 1;

    public GamePanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xe3e9d8));

        Dimension mainSize = new Dimension(500, 755);
        mainPanel.setMinimumSize(mainSize);
        mainPanel.setPreferredSize(mainSize);
        mainPanel.setMaximumSize(mainSize);

        buildUI();
    }

    private void buildUI() {

        /* ===================== NORTH ===================== */
        JPanel northPanel = new JPanel();
        northPanel.setLayout(null); 
        northPanel.setBackground(new Color(0x5e7532));
        int northHeight = 260;
        northPanel.setPreferredSize(new Dimension(500, northHeight));

        // Factor Layered (bars + icons + circle layer)
        factorLayeredPane = new JLayeredPane();
        factorLayeredPane.setLayout(null);
        factorLayeredPane.setOpaque(true);
        factorLayeredPane.setBackground(new Color(0x5e7532));

        int factorHeight = 110; // desired height of factor panel

        factorLayeredPane.setBounds(0, 25, 500, factorHeight+10); 
        
        int iconSize = 55; //adjust icon size needed here
        int spacing = 100;
        int totalWidth = (3 * iconSize) + (2 * spacing);
        int leftMargin = (500 - totalWidth) / 2;

        // vertical center for icons/bars inside factorLayeredPane
        int yOffset = (factorHeight - iconSize) / 2;

        healthBar = createVerticalBar();
        healthBar.setBounds(leftMargin, yOffset, iconSize, iconSize);
        repBar = createVerticalBar();
        repBar.setBounds(leftMargin + iconSize + spacing, yOffset, iconSize, iconSize);
        moneyBar = createVerticalBar();
        moneyBar.setBounds(leftMargin + 2 * iconSize + 2 * spacing, yOffset, iconSize, iconSize);

        factorLayeredPane.add(healthBar, Integer.valueOf(1));
        factorLayeredPane.add(repBar, Integer.valueOf(1));
        factorLayeredPane.add(moneyBar, Integer.valueOf(1));

        healthIconLabel = createIcon("OrganicEncounter/images/lp.png", iconSize);
        healthIconLabel.setBounds(leftMargin, yOffset, iconSize, iconSize);
        repIconLabel = createIcon("OrganicEncounter/images/rep.png", iconSize);
        repIconLabel.setBounds(leftMargin + iconSize + spacing, yOffset, iconSize, iconSize);
        moneyIconLabel = createIcon("OrganicEncounter/images/finance.png", iconSize);
        moneyIconLabel.setBounds(leftMargin + 2 * iconSize + 2 * spacing, yOffset, iconSize, iconSize);

        factorLayeredPane.add(healthIconLabel, Integer.valueOf(2));
        factorLayeredPane.add(repIconLabel, Integer.valueOf(2));
        factorLayeredPane.add(moneyIconLabel, Integer.valueOf(2));

        // circle layer
        circleLayer = new CircleLayer();
        circleLayer.setBounds(0, 0, 500, factorHeight);
        factorLayeredPane.add(circleLayer, Integer.valueOf(3));

        northPanel.add(factorLayeredPane);


        // for situation wrapper
        int topMargin = 20;
        int remainingHeight = northHeight - (topMargin + factorHeight);

        // wrapper takes remaining space not taken by factor
        JPanel situationWrapper = new JPanel(new GridBagLayout());
        situationWrapper.setBounds(0, topMargin + factorHeight, 500, remainingHeight);
        situationWrapper.setBackground(new Color(0xe3e9d8));
        situationWrapper.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 35));

        situationLabel = new JTextArea();
        situationLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 14));
        situationLabel.setLineWrap(true);
        situationLabel.setWrapStyleWord(true);
        situationLabel.setEditable(false);
        situationLabel.setOpaque(false);
        situationLabel.setHighlighter(null);
        situationLabel.setFocusable(false);

        // forces text area expand horizontally
        situationLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // this shit vertically
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // fill horizontally
        gbc.anchor = GridBagConstraints.CENTER;  // center vertically

        situationWrapper.add(situationLabel, gbc);
        northPanel.add(situationWrapper);

        mainPanel.add(northPanel);


        /* ===================== CENTER ===================== */
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(0xe3e9d8));

        Dimension centerSize = new Dimension(500, 375);
        centerPanel.setMinimumSize(centerSize);
        centerPanel.setPreferredSize(centerSize);
        centerPanel.setMaximumSize(centerSize);
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel = new CardPanel(this);
        Dimension cardSize = new Dimension(490, 375);
        cardPanel.setMinimumSize(cardSize);
        cardPanel.setPreferredSize(cardSize);
        cardPanel.setMaximumSize(cardSize);
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(cardPanel);
        mainPanel.add(centerPanel);

        /* ===================== SOUTH ===================== */
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBackground(new Color(0xe3e9d8));

        characLabel = new JLabel("Character Name | Title", SwingConstants.CENTER);
        characLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        characLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel characPanel = new JPanel(new BorderLayout());
        characPanel.setBackground(new Color(0xe3e9d8));
        characPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        characPanel.add(characLabel, BorderLayout.CENTER);

        Dimension characSize = new Dimension(500, 50);
        characPanel.setMinimumSize(characSize);
        characPanel.setPreferredSize(characSize);
        characPanel.setMaximumSize(characSize);
        characPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dayLabel = new JLabel("DAY 1", SwingConstants.CENTER);
        dayLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        dayLabel.setForeground(Color.WHITE);

        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.setBackground(new Color(0x5e7532));
        dayPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        dayPanel.add(dayLabel, BorderLayout.CENTER);

        Dimension daySize = new Dimension(500, 50);
        dayPanel.setMinimumSize(daySize);
        dayPanel.setPreferredSize(daySize);
        dayPanel.setMaximumSize(daySize);
        dayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        southPanel.add(characPanel);
        southPanel.add(dayPanel);

        mainPanel.add(southPanel);
    }

    private JLabel createIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setOpaque(false);
        return label;
    }

    private JProgressBar createVerticalBar() {
        JProgressBar bar = new JProgressBar(SwingConstants.VERTICAL);
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(50);
        bar.setBorderPainted(false);
        //bar.setStringPainted(true);
        bar.setOpaque(true);
        bar.setForeground(Color.WHITE);
        bar.setBackground(new Color(0xafb5af));

        // mac L
        // bar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());

        return bar;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public void update(Card card, Stats stats) {
        if (card == null) {
            situationLabel.setText("No more cards.");
            return;
        }

        situationLabel.setText(card.getSituation());

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
                    ((Timer) e.getSource()).stop();
                }
            });

            animationTimer.start();
        }
    }

    public void drawCircleIndicator(StatsChange change) {
        if (change == null) {
            circleLayer.clearCircles();
            return;
        }

        circleLayer.clearCircles();

        int gap = 15;

        if (change.getHealthChange() != 0) {
            circleLayer.addCircle(
                    healthIconLabel.getX() + healthIconLabel.getWidth() / 2,
                    healthIconLabel.getY() - gap,
                    change.getHealthChange());
        }

        if (change.getRepChange() != 0) {
            circleLayer.addCircle(
                    repIconLabel.getX() + repIconLabel.getWidth() / 2,
                    repIconLabel.getY() - gap,
                    change.getRepChange());
        }

        if (change.getMoneyChange() != 0) {
            circleLayer.addCircle(
                    moneyIconLabel.getX() + moneyIconLabel.getWidth() / 2,
                    moneyIconLabel.getY() - gap,
                    change.getMoneyChange());
        }
    }

    public void clearPreview() {
        circleLayer.clearCircles();
    }

    public void showEndingCard(Card endingCard) {
        if (endingCard == null) return;

        characLabel.setText("");
        situationLabel.setText(endingCard.getSituation());

        // StyledDocument doc = situationLabel.getStyledDocument();
        // SimpleAttributeSet center = new SimpleAttributeSet();
        // StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        // doc.setParagraphAttributes(0, doc.getLength(), center, false);

        cardPanel.displayCard(endingCard);
    }

    public CardPanel getCardPanel() {
        return cardPanel;
    }
}
