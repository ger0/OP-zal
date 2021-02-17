package Core;

import Core.Shapes.Circle;
import Core.Shapes.Square;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Vector;

public class PaintPractice extends JPanel {
    private Vector<Circle> points = new Vector<>();
    private Vector<Square> consts = new Vector<>();

    public synchronized void push(Circle c) {
        points.add(c);
    }
    public synchronized void push(Square s) {
        consts.add(s);
    }
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square s: consts) {
            s.draw(g);
        }
        for (Circle c: points) {
            c.draw(g);
        }
        points.clear();
        consts.clear();
    }
}