package Entities.Vehicles;

public class CarrierShip extends Ship {
    private String weaponName;

    public CarrierShip(int id, int[] posXY) {
        super(id, posXY);
    }
    public String getWeapon()   { return this.weaponName; }
    public void setWeapon(String name) throws NumberFormatException {
        if (name.equals("")) {
            throw new NumberFormatException("Incorrect weapon name!");
        } else {
            weaponName = name;
        }
    }
    public String getType() {
        return "CarrierShip";
    }
    public boolean spawnsMilitary() {
        return true;
    }
}
