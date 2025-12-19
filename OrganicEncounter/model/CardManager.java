package model;

import annotation.ClassInfo;
import java.util.List;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "MainWindow",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * manages different categories of cards
    * returns card depending on game progress
*/

public class CardManager {
    // ----------- FIELDS --------------
    private List<Card> introCards;
    private List<Card> randomCards;
    private List<Card> plotCards;
    private List<Card> endingCards;

    private int introIndex = 0;
    private int plotIndex = 0;
    private int MIN = 0;
    private int MAX = 100;

    // ----------- CONSTRUCTOR --------------
    public CardManager() {
        introCards = CardData.getIntroDeck();
        randomCards = CardData.getRandomDeck();
        plotCards = CardData.getPlotDeck();
        endingCards = CardData.getEndingDeck();
    }

    // ----------- HELPER METHODS --------------
    // returns random card from random deck
    private Card getRandomCard(){
        if (randomCards.isEmpty()) return null;
        int index = (int) (Math.random() * randomCards.size());
        return randomCards.get(index);
    }

    // returns next card based on game progress
    public Card getNextCard() {
        // show all intro cards first
        if (introIndex < introCards.size()){
            return introCards.get(introIndex++);
        }

        // show plot cards + fillers from random card
        if (plotIndex < plotCards.size()){
            if (!randomCards.isEmpty() && Math.random() < 0.5){
                return getRandomCard();
            } else {
                return plotCards.get(plotIndex++);
            }
        }
        return null;
    }

    // returns factor-based ending if any stat reaches its limit (MIN or MAX)
    public Card getFactorEnding(Stats stats){
        if (stats.getHealth() >= MAX){ return endingCards.get(0); }
        else if (stats.getHealth() <= MIN){ return endingCards.get(1); }
        else if (stats.getRep() >= MAX){ return endingCards.get(2); }
        else if (stats.getRep() <= MIN){ return endingCards.get(3); }
        else if (stats.getMoney() >= MAX){ return endingCards.get(4); }
        else if (stats.getMoney() <= MIN){ return endingCards.get(5); }
        return null;
    }

    // returns final ending based on the average of all stats
    public Card getEnding(Stats stats){
        double average = ((stats.getHealth() + stats.getRep() + stats.getMoney()) / 3);

        if (average >= 50.0){ return endingCards.get(6); } 
        else if (average < 50.0) { return endingCards.get(7); }
        else return null;
    }
}

