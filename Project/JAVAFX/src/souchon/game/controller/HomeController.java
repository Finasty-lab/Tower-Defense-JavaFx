package souchon.game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private javafx.scene.control.Button playBtn;

    /**
     * Method called when the play button is pressed*
     *
     * @throws IOException
     */
    public void clickPlay() throws IOException {
        Stage stage = (Stage) playBtn.getScene().getWindow();
        stage.close();


        Parent container = FXMLLoader.load(getClass().getResource("/fxml/LvlMenu.fxml"));
        Scene scene = new Scene(container);
        scene.getStylesheets().add(getClass().getResource("/css/lvlMenu.css").toExternalForm());
        stage.setScene(scene);

        stage.setTitle("Tower Defense");
        stage.show();
    }


}
