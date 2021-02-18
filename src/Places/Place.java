package Places;

import Core.Shapes.Square;

import java.util.Vector;

public abstract class Place {
    private int id;
    private int capacity;
    private int[] posXY;

    Vector<Integer> stored;

    Place(int id, int[] posXY) {
        this.id         = id;
        this.posXY      = posXY;
        setCapacity(2);
        stored = new Vector<>(capacity);
    }
    public abstract Square render(int size);

    public int[] getPos() {
        return posXY;
    }
    public int getId() {
        return id;
    }
    public int getLoad() {
        return stored.size();
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int cap) {
        if (cap >= 2) {
            this.capacity = cap;
            stored = new Vector<>(capacity);
        }
    }
    public synchronized void setVehicle(int vId) {
        if ((stored.size() + 1) < capacity) {
            stored.add(vId);
        }
    }
}
