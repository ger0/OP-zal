package Vehicles;

import Core.MapSystem;

public class CruiseShip extends Ship {
    private int     capacity;
    private int     load;
    private String  company;

    public CruiseShip(int id, int[] posXY) {
        super(id, posXY);
    }
    public int      getCapacity()   { return this.capacity; }
    public int      getLoad()       { return this.load; }
    public String   getCompany()    { return this.company; }

    public void setCompany(String company) {
        this.company = company;
    }
    public void setCapacity(int cap) {
        if (cap > 10000) {
            this.capacity = 10000;
        } else if (cap < 1) {
            this.capacity = 1;
        } else {
            this.capacity = cap;
        }
    }
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
