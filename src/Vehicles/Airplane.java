package Vehicles;
import Core.MapSystem;

public abstract class Airplane extends Vehicle {
    private int fuel;
    private int workers;
    private String nextStop;

    Airplane(int workers, int id, int[] posXY, MapSystem map) {
        super(id, posXY, map);
        this.workers = workers;
        this.fuel    = 100;
    }
    @Override
    public void draw() {
        super.map.pushPlane(super.getId(), super.getPos());
    }

    // emergency landing
    public void emergency() {
        // ...
    }

    public synchronized int      getFuel()       { return this.fuel;        }
    public synchronized int      getWorkers()    { return this.workers;     }
    public synchronized String   getNextStop()   { return this.nextStop;    }

    public synchronized void setFuel(int fuel) {
        this.fuel = fuel;
    }
    public synchronized void setWorkers(int workers) {
        this.workers = workers;
    }
}
