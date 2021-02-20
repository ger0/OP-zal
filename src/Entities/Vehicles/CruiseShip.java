package Entities.Vehicles;

public class CruiseShip extends Ship {
    private int     capacity;
    private int     load;
    private String  company;

    public CruiseShip(int id, int[] posXY) {
        super(id, posXY);
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public void setCapacity(int cap) {
        if (cap > 10000) {
            this.capacity = 10000;
        } else {
            this.capacity = Math.max(cap, 1);
        }
    }
    // make setting adjustments later
    public void setLoad(int val) {
        if (val > capacity && val < 0) {
            this.load = 0;
        } else {
            this.load = val;
        }
    }
    public String getType() {
        return "CruiseShip";
    }
    public int      getCapacity()   { return this.capacity; }
    public int      getLoad()       { return this.load; }
    public String   getCompany()    { return this.company; }
}
