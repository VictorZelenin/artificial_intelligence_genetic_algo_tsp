package genetic_algortithm_API.elementary_parts.city;

import java.io.Serializable;

/**
 * Created by User on 24.02.2016.
 */
public final class City implements Serializable {
    private final double x;
    private final double y;
    private final int id;

    public City(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public City(int id) {
        this.id = id;
        x = Math.random() * 1000;
        y = Math.random() * 1000;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "City[" + id
                + "] x = "
                + x
                + ", y = "
                + y
                + "\n";
    }
}
