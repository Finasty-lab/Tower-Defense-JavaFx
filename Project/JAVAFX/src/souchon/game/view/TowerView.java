package souchon.game.view;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import souchon.game.entity.Tower;

/**
 * Class to represent the view of a {@link Tower}
 */
public class TowerView extends Parent {
    private final ImageView image;
    private final Circle circle;

    public ImageView getImage() { return image; }

    public Circle getCircle() { return circle; }

    public TowerView(Tower t) {

        Circle circle = new Circle();
        circle.setRadius(t.getRadius());
        circle.setStroke(Color.RED);
        circle.setStrokeWidth(1.5);
        circle.setFill(Color.rgb(200, 200, 200, 0));
        circle.managedProperty().bind(circle.visibleProperty());
        this.circle=circle;

        ImageView iv = new ImageView();
        Image towerImg = new Image(t.getImg());
        iv.setImage(towerImg);
        iv.setFitHeight(40);
        iv.setFitWidth(40);
        GridPane.setRowIndex(iv, t.getX());
        GridPane.setColumnIndex(iv, t.getY());
        iv.managedProperty().bind(iv.visibleProperty());
        this.image=iv;

        //Alternative for collisionner but the enemies need a hitbox
        //if (ennemeiView.getLayoutBounds().intersects(rayonAttaque.getLayoutBounds()));

    }
}
