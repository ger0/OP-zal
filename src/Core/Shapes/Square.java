package Core.Shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square {
    int x, y;
    int size;
    String name;
    Color color;

    public Square(int[] xy, Color col, int size, int id) {
        if (size < 1) {
            this.size = 20;
        } else {
            this.size = size;
        }
        this.x = xy[0];
        this.y = xy[1];
        this.color = col;
        this.name = Integer.toString(id);
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double square = new Rectangle2D.Double(x - size / 2, y - size / 2, size, size);
        g2d.setColor(color);
        g2d.fill(square);

        g2d.setColor(Color.WHITE);
        g2d.drawString(name, x - size / 4, y);
    }
}