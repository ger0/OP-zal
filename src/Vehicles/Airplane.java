package Vehicles;
import Core.MapSystem;

public abstract class Airplane extends Vehicle {
    private int fuel;
    private int workers;
    private String nextStop;

    Airplane(int id, int[] posXY) {
        super(id, posXY);
        this.fuel    = 100;
    }
    @Override
    public synchronized void draw() {
        super.map.pushPlane(super.getId(), super.getPos());
    }
    // emergency landing
    public void emergency() {
        // ...
    }
    @Override
    void update() {
        super.update();
        fuel--;
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
