package Places;

import Core.MapSystem;
import Core.Shapes.Square;

import java.awt.*;

public class CivilAirport extends Airport {
    public CivilAirport(int id, int capacity, int[] posXY, MapSystem map) {
        super(id, capacity, posXY, map);
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.BLACK, size + 10);
    }
}
