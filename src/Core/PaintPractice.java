package Core;

import Core.Shapes.Circle;
import Core.Shapes.Square;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PaintPractice extends JPanel {
    private List<Circle> points = new ArrayList<>();
    private List<Square> consts = new ArrayList<>();

    public void push(Circle c) {
        points.add(c);
    }
    public void push(Square s) {
        consts.add(s);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square s: consts) {
            s.draw(g);
        }
        for (Circle c: points) {
            c.draw(g);
        }
        points.clear();
    }
}