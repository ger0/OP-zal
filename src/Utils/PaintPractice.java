package Utils;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import Utils.Shapes.Circle;
import java.awt.Color;

public class PaintPractice extends JPanel {
    private List<Circle> points = new ArrayList<Circle>();
    private int x, y;

    public void push(int x, int y, Color color) {
        points.add(new Circle(x, y, color));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle c: points) {
            c.draw(g);
        }
        points.clear();
    }
}