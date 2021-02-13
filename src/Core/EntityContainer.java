package Core;

import Vehicles.*;

import java.util.Map;
import java.util.HashMap;

public class EntityContainer {
    private Map<Integer, Airplane>  planes;
    private Map<Integer, Ship>      ships;

    public EntityContainer() {
        planes  = new HashMap<Integer, Airplane>();
        ships   = new HashMap<Integer, Ship>();
    }
    public void add(Airplane plane, int key) {
        planes.put(key, plane);
    }
    public void add(Ship ship, int key) {
        ships.put(key, ship);
    }
    public void drawVehicles() {
        planes.forEach((id, p) -> p.draw());
        ships.forEach((id, s) -> s.draw());
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
        } else {
            return null;
        }
    }
}