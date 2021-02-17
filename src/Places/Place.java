package Places;

import Core.MapSystem;
import Core.Shapes.Square;

public abstract class Place {
    private int id;
    private int capacity;
    private int[] posXY;
    private MapSystem map;

    Place(int id, int capacity, int[] posXY, MapSystem map) {
        this.id         = id;
        this.capacity   = capacity;
        this.posXY      = posXY;
        this.map        = map;
    }
    public abstract Square render(int size);
    public int[] getPos() {
        return posXY;
    }
    public int getId() {
        return id;
    }
}
