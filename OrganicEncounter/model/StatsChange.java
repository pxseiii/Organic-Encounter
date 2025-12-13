package model;

/* 
    Purpose: 
    * Represents a change to player's stats
    * Can be applied to a Stats object
    * Supports “no effect” choices
*/

public class StatsChange {
    public static final StatsChange NONE = new StatsChange(0, 0, 0);

    private final double healthChange;
    private final double repChange;
    private final double moneyChange;

    public StatsChange(double healthChange, double repChange, double moneyChange) {
        this.healthChange = healthChange;
        this.repChange = repChange;
        this.moneyChange = moneyChange;
    }

    public double getHealthChange() { return healthChange; }
    public double getRepChange() { return repChange; }
    public double getMoneyChange() { return moneyChange; }

    // apply effect to stats
    public void applyTo(Stats stats) {
        stats.modifyHealth(healthChange);
        stats.modifyRep(repChange);
        stats.modifyMoney(moneyChange);
    }
}
