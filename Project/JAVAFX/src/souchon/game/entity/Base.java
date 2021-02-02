package souchon.game.entity;

/**
 * Entity class to represent an Base in the game
 */
public class Base {

    private int actualHp;
    private final int maxHp;

    public Base(int ActualHp) {
        this.actualHp = ActualHp;
        this.maxHp = ActualHp;
    }

    public int getActualHp() {
        return actualHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Method to remove health point from the base
     *
     * @param e {@link Enemy} damaging to the base
     */
    public void getDamaged(Enemy e) {
        this.actualHp = this.actualHp - e.getAttackPower();
        if (actualHp < 0)
            this.actualHp = 0;
    }

}
