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
    public void modifyHealth(int amount) { health += amount; }
    public void modifyRep(int amount) { rep += amount; }
    public void modifyMoney(int amount) { money += amount; }
}
