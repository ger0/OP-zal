package Vehicles;

import Core.MapSystem;

public abstract class Ship extends Vehicle {
    private int maxSpeed;

    Ship(int maxSpeed, int id, int[] posXY, MapSystem map) {
        super(id, posXY, map);
        this.maxSpeed = maxSpeed;
    }
    public void setMaxSpeed(int speed) {
        if (speed > 60) {
            this.maxSpeed = 60;
        } else if (speed < 1) {
            this.maxSpeed = 1;
        } else {
            this.maxSpeed = speed;
        }
    }
    @Override
    public void draw() {
        super.map.pushShip(super.getId(), super.getPos());
    }
    public int getMaxSpeed()    { return this.maxSpeed; }
}