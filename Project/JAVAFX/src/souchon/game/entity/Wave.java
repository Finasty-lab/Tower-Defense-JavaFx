package souchon.game.entity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Class of entity to represent a wave of enemy. It is represented by an id, to represent its wave number, as well as an {@link Enemy} list.
 */
public class Wave {

    private final ArrayList<Enemy> enemies;
    private final int level;
    private final long id;
    private int index;

    /**
     * Method to create the enemy accord the level and the json
     *
     * @param json Json object holding the informations on the waves of enemies
     * @param lvl  Actual {@link Game} level
     */
    public Wave(JSONObject json, int lvl) {
        this.level = lvl;
        id = (long) json.get("lvlID");
        index = 0;
        enemies = new ArrayList<>();
        for (var o1 : (JSONArray) json.get("enemies")) {
            long lvlE = (long) o1;

            int miniY = 160, maxiY = 220;
            var lvl1Ytmp = (Math.random() * ((maxiY - miniY) + 1)) + miniY;
            var lvl2Xtmp = 100;

            switch ((int) lvlE) {
                case 1 :
                    if (level == 0 || level == 1) {
                        Enemy e = new Enemy(0, lvl1Ytmp, "images/enemy/lvl1.png", 2, 20, 1);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 2) {
                        Enemy e = new Enemy(lvl2Xtmp, 300, "images/enemy/lvl1.png", 2, 20,1);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 3) {
                        Enemy e = new Enemy(0, 160, "images/enemy/lvl1.png", 2, 20,1);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    break;

                case 2 :
                    if (level == 0 || level == 1) {
                        Enemy e = new Enemy(0, lvl1Ytmp, "images/enemy/lvl2.png", 2, 40,2);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 2) {
                        Enemy e = new Enemy(lvl2Xtmp, 300, "images/enemy/lvl2.png", 2, 40,2);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 3) {
                        Enemy e = new Enemy(0, 160, "images/enemy/lvl2.png", 2, 40,2);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    break;

                case 3 :
                    if (level == 0 || level == 1) {
                        Enemy e = new Enemy(0, lvl1Ytmp, "images/enemy/lvl3.png", 4, 30,3);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 2) {
                        Enemy e = new Enemy(lvl2Xtmp, 300, "images/enemy/lvl3.png", 4, 30,3);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 3) {
                        Enemy e = new Enemy(0, 160, "images/enemy/lvl3.png", 4, 30,3);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    break;

                case 4 :
                    if (level == 0 || level == 1) {
                        Enemy e = new Enemy(0, lvl1Ytmp, "images/enemy/lvl4.png", 4, 60,4);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 2) {
                        Enemy e = new Enemy(lvl2Xtmp, 300, "images/enemy/lvl4.png", 4, 60,4);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 3) {
                        Enemy e = new Enemy(0, 160, "images/enemy/lvl4.png", 4, 60,4);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    break;

                case 5 :
                    if (level == 0 || level == 1) {
                        Enemy e = new Enemy(0, lvl1Ytmp, "images/enemy/lvl5.png", 8, 80,5);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 2) {
                        Enemy e = new Enemy(lvl2Xtmp, 300, "images/enemy/lvl5.png", 8, 80,5);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    if (level == 3) {
                        Enemy e = new Enemy(0, 160, "images/enemy/lvl5.png", 8, 80,5);
                        e.setySpawn(lvl1Ytmp);
                        enemies.add(e);
                    }
                    break;
            }
        }
    }

    /**
     * Get the first {@link Enemy} in the list and returns it to be displayed in the game.
     *
     * @return the enemy who must be spawn in the game
     */
    public Enemy getEnemy() {
        var e = enemies.get(index);
        index++;
        return e;
    }

    /**
     * Method to indicate whether True or False the spawn event is finished
     *
     * @return true if spawn is finished, false then.
     */
    public boolean isSpawnFinnish() {
        return index == enemies.size();
    }

}
