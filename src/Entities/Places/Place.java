package Entities.Places;

import Core.Shapes.Square;
import Entities.Viewable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class Place implements Viewable {
    private final int id;
    private final int[] posXY;
    private int capacity;

    Vector<Integer> stored;
    Queue<Integer>  queue = new LinkedList<>();

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
            queue.add(vId);
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
    public synchronized void removeVeh(int id) {
        stored.removeElement(id);
        if (queue.size() > 0) {
            stored.add(queue.remove());
        }
    }

    public boolean canStoreVehicles() {
        return true;
    }
}
