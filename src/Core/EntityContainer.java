package Core;

import Vehicles.*;
import Places.*;

import java.util.Map;
import java.util.HashMap;

public class EntityContainer {
    private Map<Integer, Airplane>  planes      = new HashMap<Integer, Airplane>();
    private Map<Integer, Ship>      ships       = new HashMap<Integer, Ship>();
    private Map<Integer, Place>     stations    = new HashMap<Integer, Place>();

    public void add(Airplane plane, int key)    { planes.put(key, plane);   plane.start(); plane.draw();  }
    public void add(Ship ship, int key)         { ships.put(key, ship);     ship.start();  ship.draw();   }
    public void add(Place place, int key)       { stations.put(key, place); }

    public void drawVehicles() {
        planes.forEach((id, p) -> p.draw());
        ships.forEach((id, s) -> s.draw());
    }
    public void drawStations() {
        stations.forEach((id, s) -> s.draw());
    }
    public void remove(int key) {
        if (ships.containsKey(key)) {
            ships.get(key).del();
            ships.remove(key);
        } else if (planes.containsKey(key)) {
            planes.get(key).del();
            planes.remove(key);
        }
    }
    public Object get(int key) {
        if (ships.containsKey(key)) {
            return ships.get(key);
        } else if (planes.containsKey(key)) {
            return planes.get(key);
        } else if (stations.containsKey(key)) {
            return stations.get(key);
        } else {
            return null;
        }
    }

    // find an entity colliding with xy coordinates
    public Object findByPos(int[] xy, int size) {
        if (size >= 1) {
            // oh no
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
        if (Math.abs(refXY[0] - xy[0]) < size &&
            Math.abs(refXY[1] - xy[1]) < size) {
            return true;
        } else {
            return false;
        }
    }
}