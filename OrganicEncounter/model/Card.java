package model;

/* 
    Purpose: 
    * holds card attributes: situation, icon, choices, effects
    
    Concepts used:
    * Abstraction - represents gameplay concept as a simple Java object
    * Encapsulation
    * Polymorphism
    * Inheritance
*/

// parent class for Cards
public abstract class Card {
    private String situation;
    private String title;
    private String icon;
    private CardChoice leftChoice;
    private CardChoice rightChoice;
    
    public Card(String situation, String title, String icon, CardChoice left, CardChoice right) {
        this.situation = situation;
        this.title = title;
        this.icon = icon;
        this.leftChoice = left;
        this.rightChoice = right;
    }

    // ----------- GETTERS --------------

    public String getSituation() { return situation; }
    public String getTitle() { return title; }
    public String getIcon() { return icon; }
    public CardChoice getLeftChoice() { return leftChoice; }
    public CardChoice getRightChoice() { return rightChoice; }
}


// subclasses: IntroCard, RandomCard, PlotCard, EndingCard
class RandomCard extends Card {
    public RandomCard(String situation, String title, String icon, CardChoice left, CardChoice right) {
        super(situation, title, icon, left, right);
    }
}

class PlotCard extends Card {
    public PlotCard(String situation, String title, String icon, CardChoice left, CardChoice right) {
        super(situation, title, icon, left, right);
    }
}

class IntroCard extends Card {
    public IntroCard(String situation, String title, String icon, CardChoice left, CardChoice right) {
        super(situation, title, icon, left, right);
    }
}

class EndingCard extends Card {
    public EndingCard(String situation, String title, String icon, CardChoice left, CardChoice right) {
        super(situation, null, icon, null, null);
    }
}