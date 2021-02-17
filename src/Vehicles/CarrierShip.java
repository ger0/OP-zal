package Vehicles;

import Core.MapSystem;

public class CarrierShip extends Ship {
    private String weaponName;

    public CarrierShip(int id, int[] posXY) {
        super(id, posXY);
    }
    public String getWeapon()   { return this.weaponName; }
    public void setWeapon(String name) {
        weaponName = name;
    }
}
