package Core;

import Core.Shapes.Circle;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PaintPractice extends JPanel {
    private List<Circle> points = new ArrayList<Circle>();
    private int x, y;

    public void push(int x, int y, Color color, int size) {
        points.add(new Circle(x, y, color, size));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle c: points) {
            c.draw(g);
        }
        points.clear();
    }
}