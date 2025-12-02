package model;

/* 
    Purpose: 
    * holds card attributes: situation, icon, choices, effects
    
    Concepts used:
    * Abstraction - represents gameplay concept as a simple Java object
    * Encapsulation
*/

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

    public String getSituation() {
        return situation;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public CardChoice getLeftChoice() {
        return leftChoice;
    }

    public CardChoice getRightChoice() {
        return rightChoice;
    }

    // applies consequences to player's stats
    public abstract void applyEffect(Stats stat, CardChoice choice);
}

class GenCard extends Card {
    public GenCard(String situation, String title, String icon, CardChoice left, CardChoice right) {
        super(situation, title, icon, left, right);
    }

    @Override
    public void applyEffect(Stats stat, CardChoice choice) {
        stat.modifyHealth(choice.getHealthEffect());
        stat.modifyRep(choice.getRepEffect());
        stat.modifyMoney(choice.getMoneyEffect());
    }
}

/*
????
class RepCard extends Card {
    public RepCard(String situation, String icon, CardChoice left, CardChoice right) {
        super(situation, icon, left, right);
    }

    @Override
    public void applyEffect(Player player, CardChoice choice) {
        player.modifyCrop(choice.getHealthEffect());
        player.modifyRep(choice.getRepEffect());
        player.modifyMoney(choice.getMoneyEffect());
    }
}

class MoneyCard extends Card {
    public MoneyCard(String situation, String icon, CardChoice left, CardChoice right) {
        super(situation, icon, left, right);
    }

    @Override
    public void applyEffect(Player player, CardChoice choice) {
        player.modifyCrop(choice.getHealthEffect());
        player.modifyRep(choice.getRepEffect());
        player.modifyMoney(choice.getMoneyEffect());
}
*/
