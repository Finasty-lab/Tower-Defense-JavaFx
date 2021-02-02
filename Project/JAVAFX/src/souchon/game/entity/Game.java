package souchon.game.entity;

import souchon.game.collisionner.Collisionner;
import java.util.ArrayList;

/**
 * Entity class to represent a Game.
 */
public class Game {

    private final ArrayList<Enemy> enemies, wait_enemies_delete, wait_enemies_add;
    private final Collisionner collision = new Collisionner();
    private final ArrayList<Laser> wait_lasers, lasers;
    private final ArrayList<Tower> wait_towers;
    private final ArrayList<Tower> towers;
    private boolean urlIsSet = false;
    private final WaveManager wm;
    private boolean validated;
    private final Base base;
    private String urlMap;
    private int idGame;



    /**
     * Constructor a {@link Game} without any @param.
     * To create a game without specifying the level.
     */
    public Game() {
        towers = new ArrayList<>();
        wait_towers = new ArrayList<>();
        enemies = new ArrayList<>();
        wait_enemies_delete = new ArrayList<>();
        wait_enemies_add = new ArrayList<>();
        wait_lasers = new ArrayList<>();
        lasers = new ArrayList<>();
        wm = new WaveManager();
        base = new Base(50);
    }

    /**
     * Constructor a {@link Game}.
     *
     * @param id     Represent the level of the game.
     * @param urlMap Represent the way to the image background of the level.
     */
    public Game(int id, String urlMap) {
        towers = new ArrayList<>();
        wait_towers = new ArrayList<>();
        enemies = new ArrayList<>();
        wait_enemies_delete = new ArrayList<>();
        wait_enemies_add = new ArrayList<>();
        wait_lasers = new ArrayList<>();
        lasers = new ArrayList<>();
        base = new Base(50);
        this.idGame = id;
        this.validated = false;
        this.urlMap = urlMap;
        this.urlIsSet = true;
        wm = new WaveManager(getIdGame());
        if (idGame == 2) {
            this.getWm().addWaveOrder("Up", 60);
            this.getWm().addWaveOrder("Forward", 325);
            this.getWm().addWaveOrder("Up", 180);
            this.getWm().addWaveOrder("Backward", 330);
        }
        if (idGame == 3) {
            this.getWm().addWaveOrder("Forward", 170);
            this.getWm().addWaveOrder("Down", 120);
            this.getWm().addWaveOrder("Forward", 160);
            this.getWm().addWaveOrder("Up", 240);
            this.getWm().addWaveOrder("Forward", 200);
            this.getWm().addWaveOrder("Down", 200);
        }
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public boolean isUrlIsSet() {
        return urlIsSet;
    }

    public boolean isValidated() {
        return validated;
    }

    public String getUrlMap() {
        return urlMap;
    }

    public WaveManager getWm() {
        return wm;
    }

    public int getIdGame() {
        return idGame;
    }

    public Base getBase() {
        return base;
    }

    public void setValidated() {
        validated = true;
    }


    /**
     * Method for moving all the {@link Enemy} present in the game.
     * If they are outside the window, then they are added to the deletion list to be deleted in due time.
     * This is to avoid memory overload.
     */
    public void moveEnemies() {
        if (wm.getWaveOrders().isEmpty()) {
            for (var e : enemies) {
                e.updateX(true);
                if (e.getX() > 520) {
                    wait_enemies_delete.add(e);
                    base.getDamaged(e);
                }
            }
        } else {
            for (Enemy e : enemies) {
                if (!e.isStart()) {
                    //System.out.println("ENEMI ORDER");
                    //System.out.println("----" + wm.getWaveOrders());
                    e.setStart(true);
                    e.setEnemyOrders(wm.getCopyWaveOrders());
                }
                if (getIdGame() == 2) {
                    if (e.getX() < 110 && e.getY() == 52 || e.getX() < 110 && e.getY() == 60) {
                        wait_enemies_delete.add(e);
                        base.getDamaged(e);
                    }
                    e.moveEnemy();
                }
                if (getIdGame() == 3) {
                    if (e.getX() == 530 && e.getY() > 230 || e.getX() == 536 && e.getY() > 230 || e.getX() == 532 && e.getY() > 230) {
                        wait_enemies_delete.add(e);
                        base.getDamaged(e);
                    }
                    e.moveEnemy();
                }
            }
        }
    }


    /**
     * Method for creating a tower with coords (X, Y).
     *
     * @param towerName Tower type selected
     * @param x         Cell X of the {@link Tower}
     * @param y         Cell Y of the {@link Tower}
     * @param radius    circle radius of the {@link Tower}
     * @return true if the creation is OK else false
     */
    public boolean createTower(String towerName, int x, int y, int radius) {
        System.out.println("tower name : " + towerName);
        if (checkTowerCreation(x, y)) {
            switch (towerName) {
                case "lvl1" :
                    wait_towers.add(new Tower(x, y, radius, "/images/tower/tower1.png", 20));
                    break;
                case "lvl2" :
                    wait_towers.add(new Tower(x, y, radius + 10, "/images/tower/tower2.png", 30));
                    break;
                case "lvl3" :
                    wait_towers.add(new Tower(x, y, radius + 30, "/images/tower/tower3.png", 40));
                    break;
            }
        } else
            return false;
        return true;
    }

    /**
     * Method to create a {@link Enemy} if the spawning process is not finished
     */
    public void createEnemies() {
        if (!wm.spawnFinnish())
            wait_enemies_add.add(wm.spawnEnemy());
    }

    /**
     * Method of creating a {@link Tower} and putting it on standby for processing.
     */
    public void createTowers() {
        var iter = wait_towers.iterator();
        while (iter.hasNext()) {
            var t = iter.next();
            towers.add(t);
            iter.remove();
        }
    }

    /**
     * Method of creating a {@link Laser}.
     *
     * @param t {@link Tower} which is shooting
     * @param e {@link Enemy} who is being shoot
     * @return {@link Laser}
     */
    public Laser createLaser(Tower t, Enemy e) {
        double x_tower = t.getX() * 40 + 20;
        double y_tower = t.getY() * 40 + 20;
        double x_enemy = e.getX() + 20;
        double y_enemy = e.getY() + 20;
        return new Laser(x_tower, y_tower, x_enemy, y_enemy);
    }

    /**
     * Method for detecting and if necessary adding an enemy, present within the radius of a {@link Tower}, to the deletion list while awaiting its treatment.
     */
    public void updateGame() {
        for (var t : towers) {
            for (var e : enemies) {
                if (collision.inRadiusTower(e,t) && !wait_enemies_delete.contains(e)) {
                    wait_lasers.add(createLaser(t, e));
                    t.startCooldown();
                    e.getDamageHp(t);
                    System.out.println(e.getHp());
                    if (e.isDead())
                        wait_enemies_delete.add(e);
                }
            }
        }
    }

    /**
     * Method to remove from the {@link Game} the {@link Enemy} to be deleted and add the created {@link Enemy}.
     */
    public void manageEnemies() {
        var iter = wait_enemies_delete.iterator();
        while (iter.hasNext()) {
            var e = iter.next();
            enemies.remove(e);
            iter.remove();
        }
        iter = wait_enemies_add.iterator();
        while (iter.hasNext()) {
            var e = iter.next();
            enemies.add(e);
            iter.remove();
        }
    }

    /**
     * Method to remove from the {@link Game} the {@link Laser} to be deleted and add the created {@link Laser}.
     */
    public void manageLasers() {
        var iter = wait_lasers.iterator();
        while (iter.hasNext()) {
            var l = iter.next();
            lasers.add(l);
            iter.remove();
        }
    }

    /**
     * Method to clean the view from all lasers displaying
     */
    public void delLasers() {
        if (!lasers.isEmpty()) {
            lasers.clear();
        }
    }


    /**
     * Method to move on to the next {@link Wave} if all enemies have been killed and the wave is over.
     */
    public void checkNextWave() {
        if (enemies.isEmpty() && wm.spawnFinnish())
            wm.nextWave();
    }

    /**
     * Check if any {@link Tower} have already been created in coords (X, Y)
     *
     * @param x Cell X of the {@link Tower}
     * @param y Cell Y of the {@link Tower}
     * @return true if the creation is allowed else false if a tower is already created at coords (X, Y)
     */
    private boolean checkTowerCreation(int x, int y) {
        for (var t : wait_towers) {
            if (t.getX() == x && t.getY() == y)
                return false;
        }
        for (var t : towers) {
            if (t.getX() == x && t.getY() == y)
                return false;
        }
        return true;
    }

    /**
     * Method for detecting whether the game is over.
     *
     * @return true if the {@link Game} is finnish else false
     */
    public boolean gameFinnish() {
        return wm.isFinnish();
    }

}
