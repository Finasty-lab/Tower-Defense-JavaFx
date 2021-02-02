package souchon.game.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import souchon.game.entity.Game;

/**
 * Class to represent the view of a map game
 */
public class MapView {

    private final ImageView map;

    public ImageView getCarte() {
        return map;
    }

    public MapView(Game g, ImageView c) {
        c.setImage(new Image(g.getUrlMap()));
        this.map=c;
    }
}
