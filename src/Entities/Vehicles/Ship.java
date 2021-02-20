package Entities.Vehicles;

import Core.Shapes.Circle;

import java.awt.*;

public abstract class Ship extends Vehicle {
    private int maxSpeed;

    Ship(int id, int[] posXY) {
        super(id, posXY);
        setMaxSpeed(3);
    }
    public void setMaxSpeed(int speed) throws NumberFormatException {
        if (speed < 1) {
            throw new NumberFormatException("Speed must be >= 1!");
        } else if (speed > 5) {
            // hardcapped at 5
            this.maxSpeed = 5;
        }
    }
    public synchronized Circle render(int size) {
        return new Circle(super.getPos(), Color.BLUE, size);
    }
    public int getMaxSpeed()    { return this.maxSpeed; }
}