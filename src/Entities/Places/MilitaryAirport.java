package Entities.Places;

import Core.Shapes.Square;

import java.awt.*;

public class MilitaryAirport extends Airport {
    private String weapon;

    public MilitaryAirport(int id, int[] posXY) {
            super(id, posXY);
    }
    // public boolean spawnPlane();
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.DARK_GRAY, size + 10, super.getId());
    }

    public String   getType() {
        return "MilitaryAirport";
    }
}
