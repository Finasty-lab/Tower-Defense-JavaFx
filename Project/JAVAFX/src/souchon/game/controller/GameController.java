package souchon.game.controller;

import javafx.scene.Parent;
import souchon.game.loop.GameLoop;
import souchon.game.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import souchon.game.view.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameController {

    public static final int FPS_MAX = 50;
    public String selected = null;
    private GameLoop loop;
    private TowerDefense td;
    private Game game = new Game();


    @FXML
    private ImageView imgv1, imgv2, imgv3;
    @FXML
    private GridPane gridPane1;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Text tempsTxt;
    @FXML
    private Text goldTxt;
    @FXML
    private ImageView gameOverTxt;
    @FXML
    private Button tryAgain;
    @FXML
    private Button backHome;
    @FXML
    private ImageView victoryScreen;
    @FXML
    private ImageView carteURL;
    @FXML
    ImageView base;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setCarteURL() {
        MapView mv = new MapView(game,carteURL);
        carteURL=mv.getCarte();
    }

    /**
     * Set the position of the base depending on the level
     */
    public void setBasePos() {
        BaseView bv = new BaseView(game,base);
        base=bv.getBase();
    }

    /**
     * Display the element on the view {@link FXML} when the game is loosed
     */
    public void setGameOver() {
        gameOverTxt.setVisible(true);
        tryAgain.setVisible(true);
        backHome.setVisible(true);
    }

    /**
     * Display the element on the view {@link FXML} when the game is won
     */
    public void setVictoryScreen() {
        victoryScreen.setVisible(true);
        tryAgain.setVisible(true);
        backHome.setVisible(true);
    }

    /**
     * Set the current {@link Game} from the game choose in the {@link TowerDefense}
     *
     * @param td     Actual object {@link TowerDefense} use to get the game
     * @param idGame Actual id of the game
     */
    public void initData(TowerDefense td, int idGame) {
        Game g;
        g = td.getGamebyId(idGame);
        this.game = g;
        this.td = td;
        td.whoValidated();
        System.out.println("Id / Lvl GAME :> " + game.getIdGame());
        if (game.isUrlIsSet()) {
            System.out.println("URL IS SET");
            this.setCarteURL();
        }
        loop = new GameLoop(this, game, FPS_MAX);
        startLoop();
    }

    /**
     * Get the actual tower selected in the tower buy menu
     *
     * @param ae {@link ActionEvent} get from the click on the menu
     */
    @FXML
    public void selectTower(ActionEvent ae) {
        Button btn = (Button) ae.getSource();
        selected = btn.getId();
        //System.out.println(selected);
        //System.out.println("SELECTION");
    }


    /**
     * Try to put the turret selected on the area clicked
     *
     * @param me {@link MouseEvent} get from the click on the game while game is running
     */
    public void leftClick(MouseEvent me) {
        int x = (int) me.getX(), y = (int) me.getY();
        int cellX = (int) Math.ceil(x * 15 / 600), cellY = (int) Math.ceil(y * 10 / 400);
        System.out.println("x=" + x + " y=" + y + " cellX: " + cellX + " cellY " + cellY);

        if (selected == null) {
            System.err.println("ERROR : NO SELECTED !");
        } else {
            if (game.getIdGame() == 2) {
                if (0 <= cellX && cellX < 10 && 3 <= cellY && cellY < 5
                        || 12 <= cellX && cellX < 15 && 0 <= cellY && cellY < 8
                        || 0 <= cellX && cellX < 15 && cellY == 0
                        || cellX == 0 && 5 <= cellY && cellY < 8) {
                    int actualGold = Integer.parseInt(goldTxt.getText());
                    switch (selected) {
                        case "lvl1" :
                            if (actualGold < 100) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(100);
                                }
                            }

                        case "lvl2" :
                            if (actualGold < 150) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(150);
                                }
                            }

                        case "lvl3" :
                            if (actualGold < 250) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(250);
                                }
                            }
                    }
                } else {
                    System.err.println("IMPOSSIBLE PLACEMENT");
                }
            }
            if(game.getIdGame() == 3) {
                if (0 <= cellX && cellX < 7 && 0 <= cellY && cellY < 3
                    || cellX==6 && 3 <= cellY && cellY < 6
                    || 0 <= cellX && cellX < 3 && 6 <= cellY && cellY < 8
                    || cellX==10 && 3 <= cellY && cellY < 8
                    || cellX==11 && 3 <= cellY && cellY < 5) {
                    int actualGold = Integer.parseInt(goldTxt.getText());
                    switch (selected) {
                        case "lvl1" :
                            if (actualGold < 100) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(100);
                                }

                            }

                        case "lvl2" :
                            if (actualGold < 150) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(150);
                                }
                            }

                        case "lvl3" :
                            if (actualGold < 250) {
                                System.err.println("NOT ENOUGH GOLD");
                            } else {
                                int radius = 70;
                                if (!game.createTower(selected, cellX, cellY, radius)) {
                                    System.err.println("ERROR : CELL ALREADY USE");
                                }
                                else {
                                    decrementGold(250);
                                }
                            }
                    }
                } else {
                    System.err.println("IMPOSSIBLE PLACEMENT");
                }
            }
            else {
                if (0 <= cellX && cellX < 15) {
                    if (0 <= cellY && cellY < 4
                            || 6 <= cellY && cellY < 8) {
                        int actualGold = Integer.parseInt(goldTxt.getText());
                        switch (selected) {
                            case "lvl1" :
                                if (actualGold < 100) {
                                    System.err.println("NOT ENOUGH GOLD");
                                } else {
                                    int radius = 70;
                                    if (!game.createTower(selected, cellX, cellY, radius)) {
                                        System.err.println("ERROR : CELL ALREADY USE");
                                    }
                                    else {
                                        decrementGold(100);
                                    }
                                }

                            case "lvl2" :
                                if (actualGold < 150) {
                                    System.err.println("NOT ENOUGH GOLD");
                                } else {
                                    int radius = 70;
                                    if (!game.createTower(selected, cellX, cellY, radius)) {
                                        System.err.println("ERROR : CELL ALREADY USE");
                                    }
                                    else {
                                        decrementGold(150);
                                    }
                                }

                            case "lvl3" :
                                if (actualGold < 250) {
                                    System.err.println("NOT ENOUGH GOLD");
                                } else {
                                    int radius = 70;
                                    if (!game.createTower(selected, cellX, cellY, radius)) {
                                        System.err.println("ERROR : CELL ALREADY USE");
                                    }
                                    else {
                                        decrementGold(250);
                                    }
                                }


                        }
                    } else {
                        System.err.println("IMPOSSIBLE PLACEMENT");
                    }
                } else {
                    System.err.println("IMPOSSIBLE PLACEMENT");
                }
            }

        }
    }

    /**
     * Clear the gridPanel of the windows
     */
    public void clear() {
        if (gridPane1 != null)
            gridPane1.getChildren().clear();

    }

    /**
     * Display all enemies
     *
     * @param enemies List of {@link Enemy} in {@link Game}
     */
    public void displayEnemies(ArrayList<Enemy> enemies) {
        pane1.getChildren().clear();
        for (var e : enemies) {
            EnemyView ev = new EnemyView(e);
            ImageView iv = ev.getImage();
            pane1.getChildren().add(iv);

            HpBarView hbv = new HpBarView(e);
            ProgressBar hpBar = hbv.getBar();
            pane1.getChildren().add(hpBar);
        }
    }

    /**
     * Display a {@link ProgressBar} for the dynamic health bar of the {@link Base}
     *
     * @param b Actual base of the game
     */
    public void displayHealthBarBase(Base b) {
        HpBarView hbv = new HpBarView(game,b);
        ProgressBar hpBar = hbv.getBar();
        pane1.getChildren().add(hpBar);
    }

    /**
     * Display all towers
     *
     * @param towers List of {@link Tower} in {@link Game}
     */
    public void displayTowers(ArrayList<Tower> towers) {
        for (var t : towers) {
            TowerView tv = new TowerView(t);
            Circle c = tv.getCircle();
            gridPane1.add(c, t.getX(), t.getY());

            ImageView iv = tv.getImage();
            gridPane1.add(iv, t.getX(), t.getY());
        }
    }

    /**
     * Display all lasers
     */
    public void displayLasers(ArrayList<Laser> lasers) {
        for (var l : lasers) {
            LaserView lv = new LaserView(l);
            Line line = lv.getLine();
            pane2.getChildren().add(line);
        }
    }

    /**
     * Clean all {@link Laser} from the fxml
     */
    public void clearLasers() {
        if (pane2 != null) {
            pane2.getChildren().clear();
        }

    }

    /**
     * Increment the time in the view  {@link FXML}
     *
     * @param seconds Seconds to display in the view {@link FXML}
     */
    public void incrementTime(int seconds) {
        tempsTxt.setText(String.valueOf(seconds));
    }

    /**
     * Increment the gold in the view  {@link FXML}
     *
     * @param gold Amount of gold to add in the view {@link FXML}
     */
    public void incrementGold(int gold) {
        int actualGold = Integer.parseInt(goldTxt.getText());
        goldTxt.setText(String.valueOf(actualGold + gold));
    }

    /**
     * Decrement the gold in the view  {@link FXML}
     *
     * @param gold Amount of gold to decrement  in the view {@link FXML}
     */
    public void decrementGold(int gold) {
        int actualGold = Integer.parseInt(goldTxt.getText());
        goldTxt.setText(String.valueOf(actualGold - gold));
    }

    /**
     * Method to load a new {@link Game} with the same parameters as before
     * to Serialize the level(s) unlocked
     *
     * @throws IOException
     */
    public void tryAgain() throws IOException {
        Game gm = new Game(game.getIdGame(), game.getUrlMap());
        td.setGame(gm);

        try {
            FileOutputStream fos = new FileOutputStream("TowerDefense.txt");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for(Game g : td.getLevelPlayable()) {
                os.writeObject(g.getIdGame());
                os.writeObject(g.isValidated());
            }
            System.out.println("Serialization done !");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Stage stage2 = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/game.css").toExternalForm());
        stage2.setScene(scene);
        GameController controller = loader.getController();
        controller.initData(td, gm.getIdGame());
        controller.setBasePos();
        stage2.show();
    }

    /**
     * Method to go back at the level menu by inserting the actual {@link Game} in
     * the {@link TowerDefense} if you have win the game and to Serialize the level(s) unlocked
     *
     * @throws IOException
     */
    public void backHome() throws IOException {

        td.setGame(game);

        try {
            FileOutputStream fos = new FileOutputStream("TowerDefense.txt");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for(Game g: td.getLevelPlayable()) {
                os.writeObject(g.getIdGame());
                os.writeObject(g.isValidated());
            }
            System.out.println("Serialization done !");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.close();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LvlMenu.fxml"));
        Stage stage2 = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/lvlMenu.css").toExternalForm());
        stage2.setScene(scene);
        LvlMenuController controller = loader.getController();
        controller.initData(td);
        stage2.show();
    }

    /**
     * Method to start the time loop of the {@link Game} and
     * to set the images for the tower buy menu
     */
    public void startLoop() {
        imgv1.setImage(new Image("images/tower/tower1Menu.png"));
        imgv2.setImage(new Image("images/tower/tower2Menu.png"));
        imgv3.setImage(new Image("images/tower/tower3Menu.png"));
        Thread thread = new Thread(loop);
        thread.setDaemon(true);
        thread.start();
    }

}
