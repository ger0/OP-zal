package Entities.Vehicles;

public class CruiseShip extends Ship {
    private int     capacity;
    private int     load;
    private String  company;

    public CruiseShip(int id, int[] posXY) {
        super(id, posXY);
    }

    public void setCompany(String company) throws NumberFormatException {
        if (company.equals("")) {
            throw new NumberFormatException("Please insert company's name in correct format!");
        } else {
            this.company = company;
        }
    }
    public void setCapacity(int cap) throws NumberFormatException {
        if (cap < 0) {
            throw new NumberFormatException("Incorrect capacity number!");
        } else {
            this.capacity = cap;
        }
    }
    // make setting adjustments later
    public void setLoad(int val) throws NumberFormatException {
        if (val > capacity || val < 0) {
            throw new NumberFormatException("Incorrect load number!");
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
