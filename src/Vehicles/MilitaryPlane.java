package Vehicles;

public class MilitaryPlane extends Airplane {
    private String type;

    public MilitaryPlane(int id, int[] posXY) {
        super(id, posXY);
    }

    public String getType() { return this.type; }

    public void setType(String type) {
        this.type = type;
    }
}