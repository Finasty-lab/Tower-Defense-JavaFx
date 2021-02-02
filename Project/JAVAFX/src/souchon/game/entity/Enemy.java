package souchon.game.entity;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Entity class to represent an Enemy present in the game
 */
public class Enemy {

    private ArrayList<String> enemyOrders = new ArrayList<>();
    private boolean isStart = false;
    private final int attackPower;
    private final double maxHp;
    private final int vectorX;
    private final int vectorY;
    private final String img;
    private double ySpawn;
    private final int id;
    private double x;
    private double y;
    private int lvl;
    private int hp;

    /**
     * Constructor of enemy
     *
     * @param x       X spawn
     * @param y       Y spawn
     * @param img     Image of the enemy
     * @param vectorX Unit of movement
     * @param hp      Health point
     */
    public Enemy(double x, double y, String img, int vectorX, int hp,int lvl) {
        this.id = (int) (Math.random() * 10001);
        this.lvl=lvl;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.img = img;
        this.vectorX = vectorX;
        this.maxHp = hp;
        this.attackPower = 10;
        this.vectorY = vectorX;
    }

    public int getVectorX() {
        return vectorX;
    }

    public int getVectorY() {
        return vectorY;
    }

    public String getImage() {
        return img;
    }

    public int getHp() {
        return hp;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLvl() { return lvl; }

    public double getMaxHp() {
        return maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setySpawn(double ySpawn) {
        this.ySpawn = ySpawn;
    }

    public void setEnemyOrders(ArrayList<String> eo) {
        this.enemyOrders = eo;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    /**
     * Method to move {@link Enemy} according the orders
     */
    public void moveEnemy() {
        //System.out.println("Y:" + this.getY());
        //System.out.println("X:" + this.getX());
        if (enemyOrders.get(0).equals("Forward")) {
            this.updateX(true);
            this.decrementFirstValue(this.getVectorX());
            double value = Integer.parseInt(enemyOrders.get(1));
            if (value <= 0) {
                this.removeFirstWaveOrder();
            }
        }
        if (enemyOrders.get(0).equals("Backward")) {
            this.updateX(false);
            this.decrementFirstValue(this.getVectorX());
            double value = Integer.parseInt(enemyOrders.get(1));
            if (value <= 0) {
                this.removeFirstWaveOrder();
            }
        }
        if (enemyOrders.get(0).equals("Up")) {
            this.updateY(false);
            this.decrementFirstValue(this.getVectorY());
            double value = Integer.parseInt(enemyOrders.get(1));
            if (value <= 0) {
                this.removeFirstWaveOrder();
            }
        }
        if (enemyOrders.get(0).equals("Down")) {
            this.updateY(true);
            this.decrementFirstValue(this.getVectorY());
            double value = Integer.parseInt(enemyOrders.get(1));
            if (value <= 0) {
                this.removeFirstWaveOrder();
            }
        }
    }

    /**
     * Display all the order within the enemy
     */
    public void displayWaveOrders() {
        for (int i = 0; i < enemyOrders.size(); i++) {
            System.out.println(enemyOrders.get(i));
        }
    }

    /**
     * Method to decrement the actual value moving the enemy
     *
     * @param decrementValue
     */
    public void decrementFirstValue(double decrementValue) {
        String v = enemyOrders.get(1);
        int vTemp = (int) (Integer.parseInt(v) - decrementValue);
        enemyOrders.set(1, String.valueOf(vTemp));
    }

    /**
     * Method to remove the order and his value following
     */
    public void removeFirstWaveOrder() {
        enemyOrders.remove(0);
        enemyOrders.remove(0);
    }

    /**
     * Update X position of the {@link Enemy}
     *
     * @param b Boolean : true to move forward by one unit, false to move backward by one unit
     */
    public void updateX(boolean b) {
        if (b) {
            this.x += vectorX;
        } else this.x -= vectorX;
    }

    /**
     * Update Y position of the {@link Enemy}
     *
     * @param b Boolean : true to move up by one unit, false to move down by one unit
     */
    public void updateY(boolean b) {
        if (b) {
            this.y += vectorY;
        } else this.y -= vectorY;
    }

    /**
     * Method to update de life point of Enemy hit.
     *
     * @param t {@link Tower} who shoot on the {@link Enemy}
     */
    public void getDamageHp(Tower t) {
        this.hp = hp - t.getAttackPower();
        if (hp < 0)
            hp = 0;
    }

    /**
     * Method to know is the Enemy is dead
     *
     * @return true the enemy is dead, false if is not
     */
    public boolean isDead() {
        if (hp == 0) {
            return true;
        }
        if (hp < 0) {
            System.out.println("ERROR PT DE VIE ENEMY");
            return true;
        }
        return false;
    }

    /**
     * Method to compare 2 object {@link Enemy}
     *
     * @param o {@link Tower}
     * @return true if the 2 Object are equals, false else.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy e = (Enemy) o;
        return Double.compare(e.x, x) == 0 &&
                Double.compare(e.y, y) == 0 &&
                hp == e.hp &&
                Objects.equals(img, e.img);
    }

    /**
     * Method to identify the instance of the object
     *
     * @return int. Identifier of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, img, hp);
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "id=" + id +
                '}';
    }
}
