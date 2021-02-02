package souchon.game.view;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import souchon.game.entity.Base;
import souchon.game.entity.Game;

/**
 * Class to represent the view of a {@link Base}
 */
public class BaseView {

    private ImageView base;

    public ImageView getBase() {
        return base;
    }

    public BaseView(Game g, ImageView b) {

        switch (g.getIdGame()) {
            case 1 :
                b.setLayoutX(517);
                b.setLayoutY(159);
                break;
            case 2:
                b.setLayoutX(50);
                b.setLayoutY(40);
                break;
            case 3:
                b.setLayoutX(480);
                b.setLayoutY(225);
                break;
        }
        this.base=b;
    }
}
