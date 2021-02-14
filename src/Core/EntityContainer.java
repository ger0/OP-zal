package Core;

import Vehicles.*;
import Places.*;

import java.util.Map;
import java.util.HashMap;

public class EntityContainer {
    private Map<Integer, Airplane>  planes = new HashMap<Integer, Airplane>();
    private Map<Integer, Ship>      ships = new HashMap<Integer, Ship>();
    private Map<Integer, Place>     stations = new HashMap<Integer, Place>();

    public void add(Airplane plane, int key)    { planes.put(key, plane); plane.draw();  }
    public void add(Ship ship, int key)         { ships.put(key, ship);     }
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
}