package Vehicles;

import Core.MapSystem;

public class CarrierShip extends Ship {
    private String weaponName;

    public CarrierShip(String name, int maxSpeed, int id, int[] posXY, MapSystem map) {
        super(maxSpeed, id, posXY, map);
        this.weaponName = name;
    }
    public String getWeapon()   { return this.weaponName; }
}
