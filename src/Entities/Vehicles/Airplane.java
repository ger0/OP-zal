package Entities.Vehicles;
import Core.Shapes.Circle;

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
    // emergency landing
    public void emergency() {
        // ...
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

    public synchronized void setFuel(int fuel)          { this.fuel = fuel;         }
    public synchronized void setWorkers(int workers)    { this.workers = workers;   }
}
