package model;

import java.util.List;
// import java.util.Random;
/* 
    Purpose: 
    * Manages the list of cards 
    * Returns next card
    * Applies left/right choice effects to stats
*/

public class CardManager {

    private List<Card> deck;
    private int currentIndex = 0;

    public CardManager() {
        deck = CardData.getCards();
    }

    public Card getNextCard() {
        if (deck.isEmpty()) return null;
        Card next = deck.get(currentIndex);
        currentIndex += 1;
        return next;
    }
}

