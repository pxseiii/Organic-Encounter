package model;

/* 
    Purpose: 
    * gets and modifies stats of player's factors
    * stores current values (what the player has now)
    
    Concepts used:
    * Encapsulation 
    * SRP 
*/

public class Stats {
    private double health;
    private double rep;
    private double money;

    public Stats(double health, double rep, double money) {
        this.health = health;
        this.rep = rep;
        this.money = money;
    }

    // getters 
    public double getHealth() { return health; }
    public double getRep() { return rep; }
    public double getMoney() { return money; }

    // modifies the stats to store
    public void modifyHealth(double amount) { health += amount; }
    public void modifyRep(double amount) { rep += amount; }
    public void modifyMoney(double amount) { money += amount; }

}
