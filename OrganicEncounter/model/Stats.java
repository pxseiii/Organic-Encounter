package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "",
    className = "Stats",
    pillarsUsed = {},
    solidUsed = {}
)

/* 
    Purpose: 
    * gets and modifies stats of player's factors
    * stores current values (what the player has now)
    
    Concepts used:
    * Encapsulation 
    * SRP 
*/

public class Stats {
    private int health;
    private int rep;
    private int money;

    public Stats(int health, int rep, int money) {
        this.health = health;
        this.rep = rep;
        this.money = money;
    }

    // getters 
    public int getHealth() { return health; }
    public int getRep() { return rep; }
    public int getMoney() { return money; }

    // modifies the stats to store
    public void modifyHealth(int amount) { health += amount; }
    public void modifyRep(int amount) { rep += amount; }
    public void modifyMoney(int amount) { money += amount; }

}
