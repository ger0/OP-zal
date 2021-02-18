package Places;

import Core.Shapes.Square;

import java.util.Vector;

public abstract class Place {
    private final int id;
    private final int[] posXY;
    private int capacity;

    Vector<Integer> stored;

    Place(int id, int[] posXY) {
        this.id         = id;
        this.posXY      = posXY;
        setCapacity(2);
        stored = new Vector<>(capacity);
    }
    public abstract Square render(int size);

    public void setCapacity(int cap) {
        if (cap >= 2) {
            this.capacity = cap;
            stored = new Vector<>(capacity);
        }
    }
    public synchronized boolean setVehicle(int vId) {
        if ((stored.size()) < capacity) {
            stored.add(vId);
            return true;
        } else {
            return false;
        }
    }

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
    public Vector<Integer> getStoredVehicles() {
        return stored;
    }
    public void removeVeh(int id) {
        stored.removeElement(id);
    }
}
