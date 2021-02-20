package Entities.Vehicles;
import Core.Shapes.Circle;
import Entities.Places.Place;

import java.awt.*;

public abstract class Airplane extends Vehicle {
    private int fuel;
    private int workers;

    Airplane(int id, int[] posXY) {
        super(id, posXY);
        this.fuel    = 10000;
    }
    public synchronized Circle render(int size) {
        return new Circle(super.getPos(), Color.RED, size);
    }
    @Override
    void update() throws InterruptedException {
        super.update();
        if (isRenderable && targetStation != null) {
            fuel--;
        }
    }
    public synchronized int      getFuel()       { return this.fuel;        }
    public synchronized int      getWorkers()    { return this.workers;     }

    public boolean setTarget(int[] tar, Place ref) {
        if (!isRenderable) {
            fuel = 10000;
        }
        return super.setTarget(tar, ref);
    }
    public synchronized void setWorkers(int workers) throws NumberFormatException {
        if (workers < 0) {
            throw new NumberFormatException("Input workers in proper format!");
        } else {
            this.workers = workers;
        }
    }
}
