package souchon.game.loop;

import souchon.game.entity.Game;
import souchon.game.controller.GameController;
import javafx.application.Platform;

/**
 * Entity class to represent a GameLoop.
 */
public class GameLoop implements Runnable {

    private final GameController gc;
    private boolean live = true;
    private int seconds = 0;
    private long tick = 0;
    private final int fps;
    private Game game;


    /**
     * Constructor of {@link GameLoop}
     *
     * @param gc   {@link GameController}
     * @param game {@link Game}
     * @param fps  Integer meaning Frame per Second.
     */
    public GameLoop(GameController gc, Game game, int fps) {
        this.gc = gc;
        this.game = game;
        this.fps = fps;
    }

    public void setGame(Game g) {
        this.game = g;
    }

    /**
     * Method run each tick in the the time loop.
     */
    @Override
    public void run() {
        while (live) {
            // Move all enemies
            game.moveEnemies();

            // Create enemy under certain conditions
            if (tick % (fps / 2) == 0)
                game.createEnemies();

            // Create Tower
            game.createTowers();

            // Update the game object with all wait entity
            game.updateGame();

            // Manage all enemies in possible radius of towers
            game.manageEnemies();

            //Manage all lasers
            game.manageLasers();

            // Check if it's possible to change wave
            game.checkNextWave();

            //  Check if the game is end
            if (game.gameFinnish()) {
                live = false;
            }

            /**
             * Plateform.runLater is used to avoid concurrency between thread
             */
            // Render all enemies and towers created
            Platform.runLater(() -> {
                gc.clear();
                gc.clearLasers();
                gc.displayEnemies(game.getEnemies());
                gc.displayTowers(game.getTowers());
                gc.displayLasers(game.getLasers());
                game.delLasers();
                gc.displayHealthBarBase(game.getBase());
            });


            // Wait some milliseconds to respect fps limit
            try {
                Thread.sleep(fps);
                tick++;
                if (tick * fps == 1000) {
                    tick = 0;
                    seconds++;
                    gc.incrementTime(seconds);
                    gc.incrementGold(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                live = false;
            }

            if (game.getBase().getActualHp() == 0) {
                gc.setGameOver();
                break;
            }
        }
        if (game.getBase().getActualHp() != 0) {
            gc.setVictoryScreen();
            game.setValidated();
        }
        System.out.println("GAME END");
        gc.setGame(game);
        Thread.currentThread().interrupt();
    }

}
