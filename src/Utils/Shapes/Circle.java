package Utils.Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle {
    int x, y;
    Color color;

    public Circle(int x, int y, Color col) {
        this.x = x;
        this.y = y;
        this.color = col;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        g2d.setColor(color);
        g2d.fill(circle);
    }
}