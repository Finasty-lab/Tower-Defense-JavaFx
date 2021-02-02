package souchon.game.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import souchon.game.entity.Enemy;

import java.awt.*;

/**
 * Class to represent the view of an {@link Enemy}
 */
public class EnemyView {

    private ImageView image;

    public ImageView getImage() { return image; }

    public EnemyView(Enemy e) {
        int lvlE = e.getLvl();
        switch (lvlE) {
            case 1:
                ImageView entityImage = new ImageView();
                Image img = new Image("images/enemy/lvl1.png");
                entityImage.setImage(img);
                entityImage.setLayoutX(e.getX());
                entityImage.setLayoutY(e.getY());
                entityImage.setFitWidth(20);
                entityImage.setFitHeight(20);
                this.image=entityImage;
                break;
            case 2 :
                ImageView entityImage2 = new ImageView();
                Image img2 = new Image("images/enemy/lvl2.png");
                entityImage2.setImage(img2);
                entityImage2.setLayoutX(e.getX());
                entityImage2.setLayoutY(e.getY());
                entityImage2.setFitWidth(20);
                entityImage2.setFitHeight(20);
                this.image=entityImage2;
                break;
            case 3 :
                ImageView entityImage3 = new ImageView();
                Image img3 = new Image("images/enemy/lvl3.png");
                entityImage3.setImage(img3);
                entityImage3.setLayoutX(e.getX());
                entityImage3.setLayoutY(e.getY());
                entityImage3.setFitWidth(20);
                entityImage3.setFitHeight(20);
                this.image=entityImage3;
                break;
            case 4 :
                ImageView entityImage4 = new ImageView();
                Image img4 = new Image("images/enemy/lvl4.png");
                entityImage4.setImage(img4);
                entityImage4.setLayoutX(e.getX());
                entityImage4.setLayoutY(e.getY());
                entityImage4.setFitWidth(20);
                entityImage4.setFitHeight(20);
                this.image=entityImage4;
                break;
            case 5 :
                ImageView entityImage5 = new ImageView();
                Image img5 = new Image("images/enemy/lvl5.png");
                entityImage5.setImage(img5);
                entityImage5.setLayoutX(e.getX());
                entityImage5.setLayoutY(e.getY());
                entityImage5.setFitWidth(20);
                entityImage5.setFitHeight(20);
                this.image=entityImage5;
                break;
        }
        //if (ennemeiView.getLayoutBounds().intersects(rayonAttaque.getLayoutBounds()));

    }
}
