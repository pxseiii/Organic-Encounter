package model;

/* 
    Purpose: 
    * gets and modifies stats of player's factors
    
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

    // Getters and setters
    public int getHealth() { 
        return health; 
    }

    public void modifyHealth(int amount) { 
        health += amount; 
    }

    public int getRep() { 
        return rep; 
    }

    public void modifyRep(int amount) { 
        rep += amount; 
    }

    public int getMoney() { 
        return money; 
    }

    public void modifyMoney(int amount) { 
        money += amount; 
    }

}
