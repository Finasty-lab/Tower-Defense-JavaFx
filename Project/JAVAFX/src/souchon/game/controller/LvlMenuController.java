package souchon.game.controller;

import souchon.game.entity.Game;
import souchon.game.entity.TowerDefense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class LvlMenuController {

    @FXML
    private AnchorPane lvlMenuAnchor;
    @FXML
    private Button Lvl1Btn;
    @FXML
    private Button Lvl2Btn;
    @FXML
    private Button Lvl3Btn;
    private TowerDefense td;
    public String selected = null;

    /**
     * Constructor of LvlMenuController without any parameters and the 3 minimum level of the game.
     */
    public LvlMenuController() {
        td = new TowerDefense();
        td.AddLevel(new Game(1, "images/Carte2.png"));
        td.AddLevel(new Game(2, "images/lvl2.png"));
        td.AddLevel(new Game(3, "images/lvl3.png"));
    }

    /**
     * Method to set the available buttons according the game level already beaten.
     * and to get the informations of the already unlocked level from deserialization
     */
    @FXML
    public void initialize() {

        try {
            FileInputStream fis = new FileInputStream("TowerDefense.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int idGame1 = (int)ois.readObject();
            boolean isValidated1 = (boolean)ois.readObject();
            int idGame2 = (int)ois.readObject();
            boolean isValidated2 = (boolean)ois.readObject();
            int idGame3 = (int)ois.readObject();
            boolean isValidated3 = (boolean)ois.readObject();
            System.out.println("Deserialization done !");
            System.out.println(idGame1+" "+isValidated1);
            System.out.println(idGame2+" "+isValidated2);
            System.out.println(idGame3+" "+isValidated3);
            if(isValidated1) {
                td.getGamebyId(1).setValidated();
            }
            if (isValidated2) {
                td.getGamebyId(2).setValidated();
            }

            if(isValidated2) {
                td.getGamebyId(3).setValidated();
            }

            ois.close();
        }catch(EOFException eof) {
            System.err.println("File empty");
        }
        catch (FileNotFoundException e) {
            System.err.println("ERROR OPEN FILE SO CREATION OF THE FILE");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        int maxLvlUnlocked = td.maxLevelValidated();
        //System.out.println("LVL : "+maxLvlUnlocked);
        td.whoValidated();
        switch (maxLvlUnlocked) {
            case 0 :
                Lvl1Btn.setDisable(false);
                break;
            case 1 :
                Lvl1Btn.setDisable(false);
                Lvl2Btn.setDisable(false);
                break;

            case 2 :

            case 3 :
                Lvl1Btn.setDisable(false);
                Lvl2Btn.setDisable(false);
                Lvl3Btn.setDisable(false);
                break;
        }
    }

    /**
     * Method to manage the available buttons according the game level already beaten in the actual list of {@link Game} in the {@link TowerDefense}.
     *
     * @param td Actual list of games in {@link TowerDefense}.
     */
    public void initData(TowerDefense td) {
        this.td = td;

        td.whoValidated();
        int maxLvlUnlocked = td.maxLevelValidated();
        //System.out.println("INITDATA");
        switch (maxLvlUnlocked) {
            case 0 :
                Lvl1Btn.setDisable(false);
                break;
            case 1 :
                Lvl1Btn.setDisable(false);
                Lvl2Btn.setDisable(false);
                break;

            case 2 :

            case 3 :
                Lvl1Btn.setDisable(false);
                Lvl2Btn.setDisable(false);
                Lvl3Btn.setDisable(false);
                break;

        }
    }

    /**
     * Method to construct the wanted game in the {@link TowerDefense} and start it.
     *
     * @param ae Action called by clicking on a button (lvl1Btn,lvl2Btn,lvl3Btn).
     * @throws IOException
     */
    public void startLvl(ActionEvent ae) throws IOException {
        Button btn = (Button) ae.getSource();
        selected = btn.getId();
        int idGameSelected = 0;

        Stage stage = (Stage) Lvl1Btn.getScene().getWindow();
        stage.close();


        Game g = new Game();
        switch (selected) {
            case "Lvl1Btn" :
                g = new Game(1, "images/Carte2.png");
                idGameSelected = 1;
                break;

            case "Lvl2Btn" :
                g = new Game(2, "images/lvl2.png");
                idGameSelected = 2;
                g.getWm().addWaveOrder("Forward", 200);
                g.getWm().addWaveOrder("Up", 100);
                g.getWm().addWaveOrder("Forward", 200);
                break;

            case "Lvl3Btn" :
                g = new Game(3, "images/lvl3.png");
                idGameSelected = 3;
                break;
        }

        td.setGame(g);
        td.getGamebyId(2).getWm().displayWaveOrders();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Stage stage2 = new Stage(StageStyle.DECORATED);
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/css/game.css").toExternalForm());
        stage2.setScene(scene);
        GameController controller = loader.getController();
        controller.initData(td, idGameSelected);
        controller.setBasePos();
        stage2.show();


    }

}
