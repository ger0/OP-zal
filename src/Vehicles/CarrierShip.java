package Vehicles;

public class CarrierShip extends Ship {
    private String weaponName;

    public CarrierShip(int id, int[] posXY) {
        super(id, posXY);
    }
    public String getWeapon()   { return this.weaponName; }
    public void setWeapon(String name) {
        weaponName = name;
    }

    public MilitaryPlane spawnPlane(int id, int fuel) {
        MilitaryPlane plane = new MilitaryPlane(id, super.getPos());
        plane.setMap(map);
        plane.setFuel(5000);
        plane.setType(weaponName);
        plane.setWorkers(2);
        return plane;
    }
}
