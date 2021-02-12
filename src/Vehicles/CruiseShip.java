package Vehicles;
import Utils.MapSystem;

public class CruiseShip extends Ship {
    private int     capacity;
    private int     load;
    private String  company;

    public CruiseShip(String company, int cap, int maxSpd, int id, int[] posXY, MapSystem map) {
        super(maxSpd, id, posXY, map);
        this.company = company;
        if (cap > 10000) {
            this.capacity = 10000;
        } else if (cap < 1) {
            this.capacity = 1;
        } else {
            this.capacity = cap;
        }
    }
    public int      getCapacity()   { return this.capacity; }
    public int      getLoad()       { return this.load; }
    public String   getCompany()    { return this.company; }

    // make setting adjustments later
    public boolean setLoad(int val) {
        if (val > capacity && val < 0) {
            this.load = 0;
            return false;
        } else {
            this.load = val;
            return true;
        }
    }
}
