package Vehicles;

import Core.MapSystem;

public abstract class Ship extends Vehicle {
    private int maxSpeed;

    Ship(int id, int[] posXY) {
        super(id, posXY);
    }
    public void setMaxSpeed(int speed) {
        if (speed > 5) {
            this.maxSpeed = 5;
        } else if (speed < 1) {
            this.maxSpeed = 1;
        } else {
            this.maxSpeed = speed;
        }
    }
    @Override
    public synchronized void draw() {
        super.map.pushShip(super.getId(), super.getPos());
    }
    public int getMaxSpeed()    { return this.maxSpeed; }
}