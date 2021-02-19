package Vehicles;

import Core.Shapes.Circle;

import java.awt.*;

public abstract class Ship extends Vehicle {
    private int maxSpeed;

    Ship(int id, int[] posXY) {
        super(id, posXY);
        setMaxSpeed(5);
    }
    public void setMaxSpeed(int speed) {
        if (speed > 5) {
            this.maxSpeed = 5;
        } else {
            this.maxSpeed = Math.max(speed, 1);
        }
    }
    public synchronized Circle render(int size) {
        return new Circle(super.getPos(), Color.BLUE, size - 10);
    }
    public int getMaxSpeed()    { return this.maxSpeed; }
}