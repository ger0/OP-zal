package Entities.Vehicles;

public class PassengerPlane extends Airplane {
    private int capacity;
    private int load;

    public PassengerPlane(int id, int[] posXY) {
        super(id, posXY);
    }

    public void setCapacity(int cap) {
        if (cap > 100) {
            this.capacity = 100;
        } else if (cap < 10) {
            this.capacity = 10;
        } else {
            this.capacity = cap;
        }
    }
    public void setLoad(int load) {
        if (load < 0) {
            this.load = 0;
        } else if (load > this.capacity) {
            this.load = this.capacity;
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
