package Core.Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle {
    int x, y;
    int size;
    Color color;

    public Circle(int[] xy, Color col, int size) {
        if (size < 1) {
            this.size = 20;
        } else {
            this.size = size;
        }
        this.x = xy[0];
        this.y = xy[1];
        this.color = col;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
        g2d.setColor(color);
        g2d.fill(circle);
    }
}