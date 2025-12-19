package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "Card",
    pillarsUsed = {"Encapsulation","Inheritance", "Abstraction", "Polymorphism"},
    solidUsed = {"SRP, OCP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * represents an event card 
    * holds core attributes of a card: situation text, character title, icon, and two possible choices (left/right)
*/

// ----------- ABSTRACT PARENT CLASS --------------
public abstract class Card {
    // ----------- FIELDS --------------
    private String situation;
    private String title;
    private String icon;
    private CardChoice leftChoice;
    private CardChoice rightChoice;
    
    // ----------- CONSTRUCTOR --------------
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

    // ----------- HELPER METHOD --------------
    public boolean updatesDay() { return true; }
}


// ----------- CARD SUBCLASSES --------------
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

    @Override
    public boolean updatesDay() { return false; }
}

class EndingCard extends Card {
    public EndingCard(String situation, String icon) {
        super(situation, null, icon, null, null);
    }
}
