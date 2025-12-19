package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Kaindoys",
    className = "CardChoice",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP"}
)

/* 
    Purpose: 
    * encapsulates a choice on a card
    * contains the text & consequences (stat change) of one possible player choice
    
    Concepts used:
    * Encapsulation
    * SRP, KISS, YAGNI, DRY
*/

public class CardChoice {
    private String text;
    private StatsChange effect;
    private Card nextCard;              // for optional branching

    // -------------- Overloading Constructors --------------

    // for normal cards (no branching); null nextCard means linear flow
    public CardChoice(String text, StatsChange effect) {
        this(text, effect, null);
    }

    // for cards with branching
    public CardChoice(String text, StatsChange effect, Card nextCard) {
        this.text = text;
        this.effect = effect;
        this.nextCard = nextCard;
    }

    // -------------- Getters --------------
    public String getText() { return text; }
    public Card getNextCard(){ return nextCard; }       // for ones with branching
    public StatsChange getEffect(){ return effect; }


    // -------------- Helper Methods --------------
    // for checking if a branching card has a next card
    public boolean hasNextCard(){ return nextCard != null; }

    // for applying effects of a card choice to player's stats
    public void applyEffect(Stats stats){
        if (effect != null){
            effect.applyTo(stats);
        }
    }
}

