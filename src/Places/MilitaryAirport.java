package Places;

import Core.Shapes.Square;

import java.awt.*;

public class MilitaryAirport extends Airport {
    private String type;

    public MilitaryAirport(int id, int[] posXY) {
            super(id, posXY);
    }
    // public boolean spawnPlane();
    public void setType(String type) {
        this.type = type;
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.DARK_GRAY, size + 10, super.getId());
    }
}
