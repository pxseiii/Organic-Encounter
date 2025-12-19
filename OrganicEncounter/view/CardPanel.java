package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

/* 
    Purpose: 
    * all about the card's UI and motion
    * detects L/R actions and sends event to Presenter (Game)
*/

public class CardPanel extends JPanel{
    private JPanel card;
    private Card currentCard;

    private JLabel leftLabel, rightLabel, iconLabel;

    private int origX;                          // since horizontal motion only
    private int swipeThreshold = 80;            // how far before considered a swipe
    private int pressX;

    private GamePanel gamePanel;

    private SwipeListener listener;
    public void setSwipeListener(SwipeListener listener) {
        this.listener = listener;
    }
    
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

        origX = card.getX();                    // store initial position


        // --------- choices panel
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


        // --------- icon
        iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(250, 250));

        card.add(iconLabel, BorderLayout.CENTER);

        setupMotion();
    }

    public void displayCard(Card card) {
        if (card == null) return;

        // store
        currentCard = card;

        ImageIcon icon = new ImageIcon("OrganicEncounter/images/" + card.getIcon());
        Image scaled = icon.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaled));
        
        if (card.getLeftChoice() != null && card.getRightChoice() != null){           // since endingCard also uses this but doesnt have left/right choice
            leftLabel.setText("<html>" + card.getLeftChoice().getText() + "</div></html>");
            rightLabel.setText("<html>" + card.getRightChoice().getText() + "</div></html>");
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

    

    private void setupMotion(){
        // --------- DRAG LISTENER ---------
        // to detects user input

        // when user presses the mouse down
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressX = e.getX();
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
            public void mouseDragged(MouseEvent e) {            // only notifies and does nothing visually when mouse button is down and moving; hence why its overriden
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