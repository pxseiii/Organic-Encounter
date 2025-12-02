package model;

/* 
    Purpose: 
    * gets text / consequences of a decision
    
    Concepts used:
    * Encapsulation
    * SRP
*/

public class CardChoice {
    private String text;
    private int HealthEffect;
    private int RepEffect;
    private int MoneyEffect;

    public CardChoice(String text, int HealthEffect, int RepEffect, int MoneyEffect) {
        this.text = text;
        this.HealthEffect = HealthEffect;
        this.RepEffect = RepEffect;
        this.MoneyEffect = MoneyEffect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHealthEffect() {
        return HealthEffect;
    }

    public int getRepEffect() {
        return RepEffect;
    }

    public int getMoneyEffect() { 
        return MoneyEffect; 
    }
}

