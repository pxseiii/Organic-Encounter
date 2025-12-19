package model;

import annotation.ClassInfo;

@ClassInfo(
    mainAuthor = "",
    className = "StatsChange",
    pillarsUsed = {},
    solidUsed = {}
)

/* 
    Purpose: 
    * Represents a change to player's stats
    * Can be applied to a Stats object
    * Supports “no effect” choices
*/

public class StatsChange {
    public static final StatsChange NONE = new StatsChange(0, 0, 0);

    private final int healthChange;
    private final int repChange;
    private final int moneyChange;

    public StatsChange(int healthChange, int repChange, int moneyChange) {
        this.healthChange = healthChange;
        this.repChange = repChange;
        this.moneyChange = moneyChange;
    }

    public int getHealthChange() { return healthChange; }
    public int getRepChange() { return repChange; }
    public int getMoneyChange() { return moneyChange; }

    // apply effect to stats
    public void applyTo(Stats stats) {
        stats.modifyHealth(healthChange);
        stats.modifyRep(repChange);
        stats.modifyMoney(moneyChange);
    }
}
