package view;

import annotation.ClassInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "CardPanel",
    pillarsUsed = {"Encapsulation, Inheritance, Polymorphism"},
    solidUsed = {"SRP, OCP, ISP, DIP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * all about the card's UI and motion
    * detects L/R actions and sends event to Presenter (Game)
    * notifies GamePanel of card's motion for circle indicators
*/

public class CardPanel extends JPanel{
    // ----------- FIELDS --------------
    private JPanel card;
    private Card currentCard;
    private JLabel leftLabel, rightLabel, iconLabel;

    private int origX;                                  // original X position of card
    private int swipeThreshold = 80;                    // how far before considered a swipe
    private int pressX;                                 // the X position when mouse was pressed

    private GamePanel gamePanel;                        // gamePanel reference for circle indicators
    private SwipeListener listener;

    // set up swipe listener
    public void setSwipeListener(SwipeListener listener) {
        this.listener = listener;
    }
    
    // ----------- CONSTRUCTOR --------------
    public CardPanel(GamePanel gamePanel){
        setLayout(new BorderLayout());
        setBackground(new Color(230, 230, 230));
        this.gamePanel = gamePanel;

        // ---------- CARD CONTAINER ----------
        card = new JPanel(new BorderLayout(10,10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2, true),
            BorderFactory.createEmptyBorder(20,20,0,20)    // adds invisible padding 
        ));
        add(card, BorderLayout.CENTER);

        origX = card.getX();                              // store initial position


        // ---------- CHOICES PANEL ----------
        JPanel textPanel = new JPanel(new GridLayout(1,2));
        textPanel.setOpaque(false);

        leftLabel = new JLabel("<html>Left choice...</html>");
        leftLabel.setFont(new Font("Space Mono", Font.PLAIN, 14));
    
        rightLabel = new JLabel("<html>Right choice...</html>");
        rightLabel.setFont(new Font("Space Mono", Font.PLAIN, 14));
        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        textPanel.add(leftLabel);
        textPanel.add(rightLabel);
        card.add(textPanel, BorderLayout.NORTH);


        // ---------- ICON ----------
        iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(250, 250));
        card.add(iconLabel, BorderLayout.CENTER);

        // set up motion of the card
        setupMotion();
    }


    // ---------- METHODS ----------
    
    // displays card
    public void displayCard(Card card) {    
        if (card == null) return;

        // store
        currentCard = card;

        // ---------- LOAD ICON ----------
        // for icon check
        String iconPath = card.getIcon();
        
        // to check if card has icon in the first place
        if (iconPath == null || iconPath.trim().isEmpty()) {
            iconLabel.setIcon(null);
        } else {
            try {
                ImageIcon icon = new ImageIcon("OrganicEncounter/images/" + iconPath);
                
                if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                    throw new RuntimeException("Image failed to load");
                }
                Image scaled = icon.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
                iconLabel.setIcon(new ImageIcon(scaled));
                
            } catch (Exception e) {
                try {
                    ImageIcon errorIcon = new ImageIcon("C:OrganicEncounter/images/error.png");
                    Image scaled = errorIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    iconLabel.setIcon(new ImageIcon(scaled));
                } catch (Exception ex) {
                    // clears icon if even error fails :(
                    iconLabel.setIcon(null);
                }
            }
        }
        
        // ---------- UPDATE CHOICES ----------
        if (card.getLeftChoice() != null && card.getRightChoice() != null){           // since endingCard also uses this but doesnt have left/right choice
            leftLabel.setText("<html>" + card.getLeftChoice().getText() + "</html>");
            rightLabel.setText("<html><div style='text-align:right;'>" + card.getRightChoice().getText() + "</div></html>");
            leftLabel.setVisible(true);
            rightLabel.setVisible(true);
        } else {
            // ending card; no choices
            leftLabel.setText("");
            rightLabel.setText("");
            leftLabel.setVisible(false);
            rightLabel.setVisible(false);
        }
    }

    
    // sets up motion and swipe listener to detect user input
    private void setupMotion(){

        // when user presses the mouse and release
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressX = e.getX();                          // record inital mouse X
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int offset = card.getX() - origX;

                if(listener != null){
                    if (offset < -swipeThreshold){          // if card moved 80 px left 
                        listener.onSwipeLeft();
                    } else if (offset > swipeThreshold){    // if card moved 80 px right 
                        listener.onSwipeRight();
                    }
                }
                
                // reset UI
                card.setLocation(origX, card.getY());
                gamePanel.drawCircleIndicator(null);
            }
        });

        // when user drags the card L/R
        card.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // only notifies and does nothing visually when mouse button is down and moving; hence why its overriden           
                int newX = card.getX() + (e.getX() - pressX);   // updates everytime the current position of mouse is tracked
                card.setLocation(newX, card.getY());            // swing repaints it at new loc

                // show preview circles
                int offset = newX - origX;

                if (offset < -10) {                             // dragged left
                    CardChoice choice = currentCard.getLeftChoice();
                    gamePanel.drawCircleIndicator(
                        choice != null ? choice.getEffect() : null);
                } else if (offset > 10) {
                    CardChoice choice = currentCard.getRightChoice();
                    gamePanel.drawCircleIndicator(
                        choice != null ? choice.getEffect() : null);
                } else {                                        // back to center so no indicator
                    gamePanel.drawCircleIndicator(null); 
                }
            }
        });
    }
}
