import Vehicles.*;
import java.util.Vector;

public class EntityContainer {
    private Vector<Airplane>    planes;
    private Vector<Ship>        ships;
    private int planeCount, shipCount;

    public EntityContainer() {
        planes  = new Vector<Airplane>();
        ships   = new Vector<Ship>();
        planeCount = shipCount = 0;
    }
    public void add(Airplane plane, int key)  { planes.add(plane);    }
    public void add(Ship ship, int key)       { ships.add(ship);      }

    public void remove(int key) {
        // tbd removal
    }
}
