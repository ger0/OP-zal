package Entities.Places;

import Core.Shapes.Square;
import java.awt.*;

public class CivilAirport extends Airport {
    public CivilAirport(int id, int[] posXY) {
        super(id, posXY);
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.BLACK, size + 10, super.getId());
    }

    public String getType() {
        return "CivilAirport";
    }
}
