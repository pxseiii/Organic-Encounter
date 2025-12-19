package model;

/* 
    Purpose: 
    * encapsulates a choice on a card
    * contains the text & consequences (stat change) of a choice
    
    Concepts used:
    * Encapsulation
    * SRP
*/

public class CardChoice {
    private String text;
    private StatsChange effect;
    private Card nextCard;              // for optional branching

    // overloading constructors
    // for intro cards with branching
    public CardChoice(String text, StatsChange effect) {
        this(text, effect, null);
    }

    // for normal cards; ones w/o branching
    public CardChoice(String text, StatsChange effect, Card nextCard) {
        this.text = text;
        this.effect = effect;
        this.nextCard = nextCard;
    }

    public String getText() { return text; }
    public Card getNextCard(){ return nextCard; }
    public StatsChange getEffect(){ return effect; }

    public boolean hasNextCard(){ return nextCard != null; }

    // helper method to apply effect to Stats
    public void applyEffect(Stats stats){
        if (effect != null){
            effect.applyTo(stats);
        }
    }
}

