package Places;

import Core.MapSystem;

public abstract class Place {
    private int id;
    private int capacity;
    private int[] posXY;
    private MapSystem map;

    // public void draw();

    Place(int id, int capacity, int[] posXY, MapSystem map) {
        this.id         = id;
        this.capacity   = capacity;
        this.posXY      = posXY;
        this.map        = map;
    }
    public void draw() {
        map.pushStation(id, posXY);
    }
    public int[] getPos() {
        return posXY;
    }
    public int getId() {
        return id;
    }
}
