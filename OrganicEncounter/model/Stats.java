package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "Stats",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * represents the stats of a player's factors
    * stores current values (what the player has now)
    * provides a method to modify the values

*/

public class Stats {
    // ----------- FIELDS --------------
    private int health;
    private int rep;
    private int money;

    // ----------- CONSTRUCTOR --------------
    public Stats(int health, int rep, int money) {
        this.health = health;
        this.rep = rep;
        this.money = money;
    }

    // ----------- GETTERS --------------
    // used in CardManager
    public int getHealth() { return health; }
    public int getRep() { return rep; }
    public int getMoney() { return money; }

    // ----------- METHODS --------------
    // used in StatsChange; to modify the value of stats 
    public void modifyHealth(int amount) {
        int newValue = this.health + amount;
        this.health = Math.max(0, Math.min(100, newValue));
    }

    public void modifyRep(int amount) {
        int newValue = this.rep + amount;
        this.rep = Math.max(0, Math.min(100, newValue));
    }

    public void modifyMoney(int amount) {
        int newValue = this.money + amount;
        this.money = Math.max(0, Math.min(100, newValue));
    }
}
