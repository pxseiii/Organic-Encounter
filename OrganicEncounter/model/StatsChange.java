package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "Kaindoy",
    className = "StatsChange",
    pillarsUsed = {"Encapsulation"},
    solidUsed = {"SRP, OCP"}
)

/* 
    Description / Author Comments

    Purpose: 
    * represents a change to player's stats (health, rep, money)
    * a card could have 0, 1, or 2 StatsChange object
    * supports “no effect” choices
*/

public class StatsChange {
    // ----------- FIELDS --------------
    private final int healthChange;
    private final int repChange;
    private final int moneyChange;

    // ----------- CONSTRUCTOR --------------
    public StatsChange(int healthChange, int repChange, int moneyChange) {
        this.healthChange = healthChange;
        this.repChange = repChange;
        this.moneyChange = moneyChange;
    }

    // ----------- GETTERS --------------
    // used in GamePanel to read stats change data to draw circle indicators 
    public int getHealthChange() { return healthChange; }
    public int getRepChange() { return repChange; }
    public int getMoneyChange() { return moneyChange; }

    // ----------- METHODS --------------
    // used in CardChoice to apply stat changes to player's stats
    public void applyTo(Stats stats) {
        stats.modifyHealth(healthChange);
        stats.modifyRep(repChange);
        stats.modifyMoney(moneyChange);
    }
}
