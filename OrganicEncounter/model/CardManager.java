package model;

import java.util.List;
// import java.util.Random;
/* 
    Purpose: 
    * Manages different categories of cards
    * Returns card depending on game progress
    * card flow
*/

public class CardManager {
    private List<Card> introCards;
    private List<Card> randomCards;
    private List<Card> plotCards;

    private int introIndex = 0;
    private int plotIndex = 0;
    private int MIN = 0;
    private int MAX = 100;

    public CardManager() {
        introCards = CardData.getIntroDeck();
        randomCards = CardData.getRandomDeck();
        plotCards = CardData.getPlotDeck();
    }

    // to get random card in random
    private Card getRandomCard(){
        if (randomCards.isEmpty()) return null;
        int index = (int) (Math.random() * randomCards.size());
        return randomCards.get(index);
    }

    // returns next card
    public Card getNextCard() {
        // show all intro cards first
        if (introIndex < introCards.size()){
            return introCards.get(introIndex++);
        }

        // show plot cards + random fillers
        if (plotIndex < plotCards.size()){
            if (!randomCards.isEmpty() && Math.random() < 0.5){
                return getRandomCard();
            } else {
                return plotCards.get(plotIndex++);
            }
        }
        return null;
    }

    // returns factor ending
    public Card getFactorEnding(Stats stats){
        if (stats.getHealth() >= MAX){ return CardData.getEndingDeck().get(0); }
        else if (stats.getHealth() <= MIN){ return CardData.getEndingDeck().get(1); }
        else if (stats.getRep() >= MAX){ return CardData.getEndingDeck().get(2); }
        else if (stats.getRep() <= MIN){ return CardData.getEndingDeck().get(3); }
        else if (stats.getMoney() >= MAX){ return CardData.getEndingDeck().get(4); }
        else if (stats.getMoney() <= MIN){ return CardData.getEndingDeck().get(5); }
        return null;
    }

    // returns final ending
    public Card getEnding(Stats stats){
        double average = (stats.getHealth() + stats.getRep() + stats.getMoney() / 3);

        if (average >= 50.0){
            return CardData.getEndingDeck().get(6);
        } else if (average < 50.0) {
            return CardData.getEndingDeck().get(7);
        }
        return null;
    }
    
    /*
    // reset game progress?
    public void reset(){
        introIndex = 0;
        plotIndex = 0;
    }
    */
}

