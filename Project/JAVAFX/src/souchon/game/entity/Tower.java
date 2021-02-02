package souchon.game.entity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class of entity to represent a Tower.
 * It is represented by an x,y,radius,img,readyToShoot,attackPower and TimerShoot.
 */
public class Tower {

    private final String img;
    private boolean readyToShoot;
    private final int attackPower;
    private final Timer timerShoot;
    private final int x, y, radius;

    /**
     * Constructor of {@link Tower}
     *
     * @param x      X position of the tower.
     * @param y      Y position of the tower.
     * @param radius Radius effect around.
     * @param img    Path to the image.
     * @param damage Damage deal by the tower towars {@link Enemy}
     */
    public Tower(int x, int y, int radius, String img, int damage) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.img = img;
        this.readyToShoot = true;
        this.attackPower = damage;
        timerShoot = new Timer();

    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getRadius() {
        return radius;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getImg() {
        return img;
    }

    public boolean isReadyToShoot() {
        return readyToShoot;
    }

    /**
     * Method to start the cooldown of the turret
     */
    public void startCooldown() {
        readyToShoot = false;
        timerShoot.schedule(new setReadyToShoot(), 2000);
    }

    /**
     * Class entity representing the task done in a timer
     */
    class setReadyToShoot extends TimerTask {
        @Override
        public void run() {
            readyToShoot = true;
        }
    }

    @Override
    public String toString() {
        return "Tower{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", img='" + img + '\'' +
                '}';
    }

    /**
     * Method to know if an {@link Enemy} is in range of the {@link Tower}
     *
     * @param e {@link Enemy} tested in the range
     * @return true if it is in the range, false else.
     */
    public boolean isInRange(Enemy e) {
        double dist = Math.sqrt(Math.pow(e.getX() - x * 40, 2) + Math.pow(e.getY() - y * 40, 2));
        return dist <= radius;
    }

    /**
     * Method to compare 2 object {@link Tower}
     *
     * @param o {@link Tower}
     * @return true if the 2 Object are equals, false else.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tower tower = (Tower) o;
        return Double.compare(tower.x, x) == 0 &&
                Double.compare(tower.y, y) == 0 &&
                Double.compare(tower.radius, radius) == 0 &&
                Objects.equals(img, tower.img);
    }

    /**
     * Method to idenfy the instance of the object
     *
     * @return int. Identifier of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, radius, img);
    }
}
