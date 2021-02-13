package Vehicles;

import Core.MapSystem;

public class PassengerPlane extends Airplane {
    private int capacity;
    private int load;

    public PassengerPlane(int capacity, int workers, int id, int[] posXY, MapSystem map) {
        super(workers, id, posXY, map);
        this.capacity = capacity;
    }
    public int getCapacity() { return this.capacity; }
    public int getLoad()     { return this.load; }

    public void setLoad(int load) {
        if (load < 0) {
            this.load = 0;
        } else if (load > this.capacity) {
            this.load = this.capacity;
        } else {
            this.load = load;
        }
    }
}
