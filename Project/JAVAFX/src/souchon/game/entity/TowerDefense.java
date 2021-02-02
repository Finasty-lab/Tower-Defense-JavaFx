package souchon.game.entity;

import java.util.ArrayList;

/**
 * Class of entity to represent the TowerDefense. It is represented by an {@link Game} list.
 */
public class TowerDefense {


    private final ArrayList<Game> levelPlayable = new ArrayList<>();

    public ArrayList<Game> getLevelPlayable() {
        return levelPlayable;
    }

    /**
     * Method to add a {@link Game} in the {@link TowerDefense}
     *
     * @param level Level added to the TowerDefense
     */
    public void AddLevel(Game level) {
        levelPlayable.add(level);
    }

    /**
     * Method to get the highest level cleared in the TowerDefense
     *
     * @return int The id of the highest game cleared
     */
    public int maxLevelValidated() {
        int maxLvl = -1;
        int idLvl;
        for (Game g : levelPlayable) {
            if (g.isValidated()) {
                System.out.println(g + " est validé");
                idLvl = g.getIdGame();
                if (idLvl > maxLvl) {
                    maxLvl = idLvl;
                }
            }
        }
        if (maxLvl == -1) {
            return 0;
        } else return maxLvl;
    }

    /**
     * Method to get a {@link Game} by his id
     *
     * @param id Id game searched
     * @return {@link Game} The game found by the id provided
     */
    public Game getGamebyId(int id) {
        for (Game g : levelPlayable) {
            if (g.getIdGame() == id) {
                return g;
            }
        }
        return null;
    }

    /**
     * Method to insert a {@link Game} instead of another one according conditions
     *
     * @param game {@link Game} wanted to insert.
     */
    public void setGame(Game game) {
        int idGameTmp = -1;
        boolean isValidated = false;
        for (Game g : levelPlayable) {
            if (g.getIdGame() == game.getIdGame()) {
                isValidated = g.isValidated();
                idGameTmp = g.getIdGame();
            }
        }
        if (idGameTmp != -1) {
            if (isValidated) {
                levelPlayable.set(idGameTmp - 1, game);
            } else {
                levelPlayable.set(idGameTmp - 1, new Game(idGameTmp, game.getUrlMap()));
            }

        }
    }

    /**
     * Display the game(s) validated by the player
     */
    public void whoValidated() {
        for (Game g : levelPlayable) {
            if (g.isValidated()) {
                System.err.println("Les Validés sont!! :" + g.getIdGame());
            }
        }
    }
}
