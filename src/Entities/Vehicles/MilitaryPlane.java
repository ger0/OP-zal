package Entities.Vehicles;

public class MilitaryPlane extends Airplane {
    private String weapon;

    public MilitaryPlane(int id, int[] posXY) {
        super(id, posXY);
    }

    public String getWeapon() { return this.weapon; }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public String getType() {
        return "MilitaryPlane";
    }
}