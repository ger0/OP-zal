package Core;

import Vehicles.*;
import Places.*;

import java.util.Map;
import java.util.HashMap;

public class EntityContainer {
    private final Map<Integer, Airplane>  planes      = new HashMap<>();
    private final Map<Integer, Ship>      ships       = new HashMap<>();
    private final Map<Integer, Place>     stations    = new HashMap<>();

    public void add(Airplane plane, int key)    { planes.put(key, plane);   plane.start();}
    public void add(Ship ship, int key)         { ships.put(key, ship);     ship.start();}
    public void add(Place place, int key)       { stations.put(key, place); }

    public void remove(int key) {
        ships.remove(key);
        planes.remove(key);
    }
    public Object get(int key) {
        Object ret;
        ret = ships.getOrDefault(key, null);
        if (ret != null) return ret;

        ret = planes.getOrDefault(key, null);
        if (ret != null) return ret;

        return stations.getOrDefault(key, null);
    }
    public Map<Integer, Airplane> getPlanes() {
        return this.planes;
    }
    public Map<Integer, Ship> getShips() {
        return this.ships;
    }
    public Map<Integer, Place> getPlaces() {
        return this.stations;
    }

    // find an entity colliding with xy coordinates
    public Object findByPos(int[] xy, int size) {
        if (size >= 1) {
            for (Integer key: planes.keySet()) {
                int[] refXY = (planes.get(key)).getPos();
                if (isColliding(xy, size, refXY)) {
                    return planes.get(key);
                }
            }
            for (Integer key: ships.keySet()) {
                int[] refXY = (ships.get(key)).getPos();
                if (isColliding(xy, size, refXY)) {
                    return ships.get(key);
                }
            }
            for (Integer key: stations.keySet()) {
                int[] refXY = (stations.get(key)).getPos();
                if (isColliding(xy, size, refXY)) {
                    return stations.get(key);
                }
            }
        }
        return null;
    }
    boolean isColliding(int[] xy, int size, int[] refXY) {
        return Math.abs(refXY[0] - xy[0]) < size &&
                Math.abs(refXY[1] - xy[1]) < size;
    }
}