package Core;

import Entities.Vehicles.*;
import Entities.Places.*;
import Entities.Viewable;

import java.util.Map;
import java.util.HashMap;

public class EntityContainer {
    private final Map<Integer, Airplane>  planes      = new HashMap<>();
    private final Map<Integer, Ship>      ships       = new HashMap<>();
    private final Map<Integer, Place>     stations    = new HashMap<>();

    public void add(Vehicle veh, int key) {
        Class<?> type = veh.getClass();
        if (Airplane.class.isAssignableFrom(type)) {
            planes.put(key, (Airplane)veh);
            veh.start();
        } else if (Ship.class.isAssignableFrom(type)) {
            ships.put(key, (Ship)veh);
            veh.start();
        }
    }
    public void add(Place place, int key) {
        stations.put(key, place);
    }
    public void remove(int key) {
        ((Vehicle)get(key)).stop();
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
    // unify ???
    public synchronized Map<Integer, Airplane> getPlanes() {
        return this.planes;
    }
    public synchronized Map<Integer, Ship> getShips() {
        return this.ships;
    }
    public synchronized Map<Integer, Place> getPlaces() {
        return this.stations;
    }

    public Place findClosestAirport(int[] xy, Class<?> type) {
        int minDist = Integer.MAX_VALUE;
        Place place = null;

        for (Integer key: stations.keySet()) {
            Place obj = stations.get(key);
            if (obj.getLoad() < obj.getCapacity() &&
                    obj.getClass() == type)
            {
                int[] ref = obj.getPos();
                int dist = (int)Math.sqrt(Math.pow(ref[0] - xy[0], 2) +
                                Math.pow(ref[1] - xy[1], 2));
                if (dist < minDist) {
                    minDist = dist;
                    place = obj;
                }
            }
        }
        return place;
    }

    // find an entity colliding with xy coordinates
    public Object findByPos(int[] xy, int size) {
        if (size >= 1) {
            for (Integer key: planes.keySet()) {
                int[] refXY = (planes.get(key)).getPos();
                if (planes.get(key).renderable() && isColliding(xy, size, refXY)) {
                    return planes.get(key);
                }
            }
            for (Integer key: ships.keySet()) {
                int[] refXY = (ships.get(key)).getPos();
                if (ships.get(key).renderable() && isColliding(xy, size, refXY)) {
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