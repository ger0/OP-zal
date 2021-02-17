package Places;

import Core.MapSystem;
import Core.Shapes.Square;

import java.awt.*;

public class MilitaryAirport extends Airport {
    private String type;

    public MilitaryAirport(int id, String type, int capacity, int[] posXY, MapSystem map) {
            super(id, capacity, posXY, map);
            this.type = type;
    }
    // public boolean spawnPlane();
    public void changeType(String type) {
        this.type = type;
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.DARK_GRAY, size + 10);
    }
}
