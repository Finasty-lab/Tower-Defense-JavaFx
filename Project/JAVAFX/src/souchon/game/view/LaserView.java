package souchon.game.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import souchon.game.entity.Laser;

/**
 * Class to represent the view of a {@link Laser}
 */
public class LaserView {

    private final Line line;

    public Line getLine() {
        return line;
    }

    public LaserView(Laser l) {
        Line line = new Line(l.getX_start(), l.getY_start(), l.getX_end(), l.getY_end());
        line.setStroke(Color.RED);
        line.setStrokeWidth(4);
        this.line=line;
    }
}
