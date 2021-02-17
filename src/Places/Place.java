package Places;

import Core.Shapes.Square;

public abstract class Place {
    private int id;
    private int capacity;
    private int[] posXY;

    Place(int id, int[] posXY) {
        this.id         = id;
        this.posXY      = posXY;
    }
    public abstract Square render(int size);
    public int[] getPos() {
        return posXY;
    }
    public int getId() {
        return id;
    }
    public void setCapacity(int cap) {
        if (cap > 2) {
            this.capacity = cap;
        }
    }
}
