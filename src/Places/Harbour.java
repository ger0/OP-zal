package Places;

import Core.Shapes.Square;
import java.awt.*;

public class Harbour extends Place {
    public Harbour(int id, int[] posXY) {
        super(id, posXY);
    }
    public Square render(int size) {
        return new Square(super.getPos(), Color.GREEN, size + 10, super.getId());
    }
}