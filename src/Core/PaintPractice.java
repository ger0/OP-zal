package Core;

import Core.Shapes.Circle;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PaintPractice extends JPanel {
    private List<Circle> points = new ArrayList<Circle>();
    private int x, y;

    public void push(int x, int y, Color color) {
        points.add(new Circle(x, y, color));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  ----------------------------------------
        Graphics2D g2d = (Graphics2D) g.create();
        int size = Math.min(600 - 4, 600 - 4) / 10;
        int width = getWidth() - (600 * 2);
        int height = getHeight() - (600 * 2);

        int y = (getHeight() - (size * 10)) / 2;
        for (int horz = 0; horz < 10; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
        //  ----------------------------------------

        for (Circle c: points) {
            c.draw(g);
        }
        points.clear();
    }
}