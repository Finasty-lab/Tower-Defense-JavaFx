package souchon.game.entity;


/**
 * Class of entity to represent a Laser of a {@link Tower}
 */
public class Laser {

    private final double x_start;
    private final double y_start;
    private final double x_end;
    private final double y_end;

    /**
     * Constructor of {@link Laser}
     *
     * @param xStart X position of the tower emitting laser.
     * @param yStart Y position of the tower emitting laser.
     * @param xEnd   X position of the {@link Enemy} hit.
     * @param yEnd   Y position of the {@link Enemy} hit.
     */
    public Laser(double xStart, double yStart, double xEnd, double yEnd) {
        this.x_start = xStart;
        this.y_start = yStart;
        this.x_end = xEnd;
        this.y_end = yEnd;
    }

    public double getX_start() {
        return x_start;
    }

    public double getY_start() {
        return y_start;
    }

    public double getX_end() {
        return x_end;
    }

    public double getY_end() {
        return y_end;
    }
}
