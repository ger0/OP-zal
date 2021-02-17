package Places;

import Core.MapSystem;
import Core.Shapes.Square;

import java.awt.*;

public class Harbour extends Place {
    public Harbour(int id, int capacity, int[] posXY, MapSystem map) {
        super(id, capacity, posXY, map);
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.GREEN, size + 10);
    }
}