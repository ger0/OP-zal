package Vehicles;
import Utils.MapSystem;

public abstract class Airplane extends Vehicle {
    private int fuel;
    private int workers;
    private String nextStop;

    Airplane(int workers, int id, int[] posXY, MapSystem map) {
        super(id, posXY, map);
        this.workers = workers;
        this.fuel    = 100;
    }
    // emergency landing
    public void emergency() {
        // ...
    }

    @Override
    public void draw() {
        super.map.pushPlane(super.getId(), super.getPos());
    }

    public int      getFuel()       { return this.fuel; }
    public int      getWorkers()    { return this.workers; }
    public String   getNextStop()   { return this.nextStop; }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
    public void setWorkers(int workers) {
        this.workers = workers;
    }
}
