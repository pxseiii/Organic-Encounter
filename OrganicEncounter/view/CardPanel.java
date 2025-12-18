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
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel iconLabel;

    private int origX;                          // since horizontal motion only
    private int swipeThreshold = 80;            // how far before considered a swipe
    private int pressX;
    private SwipeListener listener;

    public void setSwipeListener(SwipeListener listener) {
        this.listener = listener;
    }
    
    public CardPanel(){

        setLayout(new BorderLayout());
        setBackground(new Color(230, 230, 230));

        // ---------- CARD CONTAINER ----------
        card = new JPanel(new BorderLayout(10,10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 5, true),
            BorderFactory.createEmptyBorder(20,20,20,20)    // adds invisible padding 
        ));
        add(card, BorderLayout.CENTER);

        origX = card.getX();                    // store initial position


        // --------- choices panel
        JPanel textPanel = new JPanel(new GridLayout(1,2));
        textPanel.setOpaque(false);

        leftLabel = new JLabel("<html>Left choice...</html>");
        leftLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
    
        rightLabel = new JLabel("<html>Right choice...</html>");
        rightLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
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

        ImageIcon icon = new ImageIcon("images/" + card.getIcon());
        Image scaled = icon.getImage().getScaledInstance(500, 450, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaled));
        
        if (card.getLeftChoice() != null && card.getRightChoice() != null){           // since endingCard also uses this but doesnt have left/right choice
            leftLabel.setText("<html>" //<div style='width:200px;'>
                + card.getLeftChoice().getText() + "</div></html>");
            rightLabel.setText("<html>" //<div style='width:200px; text-align: right;'>
                + card.getRightChoice().getText() + "</div></html>");
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
            }
        });

        // when user drags the card L/R
        card.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = card.getX() + (e.getX() - pressX);
                card.setLocation(newX, card.getY());
            }
        });
    }

}
