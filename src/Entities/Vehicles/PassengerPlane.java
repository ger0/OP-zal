package Entities.Vehicles;

public class PassengerPlane extends Airplane {
    private int capacity;
    private int load;

    public PassengerPlane(int id, int[] posXY) {
        super(id, posXY);
    }

    public void setCapacity(int cap) throws NumberFormatException {
        if (cap < 0) {
            throw new NumberFormatException("Incorrect capacity number!");
        } else {
            this.capacity = cap;
        }
    }
    public void setLoad(int load) throws NumberFormatException {
        if (load < 0 || load > this.capacity) {
            throw new NumberFormatException("Incorrect load number!");
        } else {
            this.load = load;
        }
    }
    public String getType() {
        return "PassengerPlane";
    }
    public int getCapacity() { return this.capacity; }
    public int getLoad()     { return this.load; }
}
