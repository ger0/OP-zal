package Core;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapSystem extends JFrame {
    private final int SIZE;
    private final int gridSize;
    private final int gridDensity;
    private final PaintPractice panel = new PaintPractice();

    // gui object reference
    private Options gui;

    public MapSystem(int size, int density, Options gui) {
        if (size < 10) {
            throw new IllegalArgumentException("window's size: " +
                    (size));
        } else if (density < 1) {
            throw new IllegalArgumentException("window's density: " +
                    (density));
        } else {
            setSize(size, size);
            SIZE = size;
            gridDensity = density;
            gridSize    = size / density;
            this.gui = gui;
            this.setSize(SIZE, SIZE);
            this.setResizable(false);

            // JFrame settings
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel.setSize(SIZE, SIZE);
            this.add(panel);
            setVisible(true);
            initMouseListener();
        }
    }
    public void repaint(EntityContainer c) {
        for (Integer key: c.getPlaces().keySet()) {
            panel.push((c.getPlaces().get(key)).render(gridSize));
        }
        for (Integer key: c.getPlanes().keySet()) {
            if (c.getPlanes().get(key).renderable())
                panel.push((c.getPlanes().get(key)).render(gridSize));
        }
        for (Integer key: c.getShips().keySet()) {
            if (c.getShips().get(key).renderable())
                panel.push((c.getShips().get(key)).render(gridSize));
        }
        super.repaint();
    }
    private void initMouseListener() {
        panel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gui.select(new int[] {e.getPoint().x, e.getPoint().y});
            }
            public void mousePressed(MouseEvent mouseEvent) {}
            public void mouseReleased(MouseEvent mouseEvent) {}
            public void mouseEntered(MouseEvent mouseEvent) {}
            public void mouseExited(MouseEvent mouseEvent) {}
        });
    }
    public int getMapSize() {
        return SIZE;
    }
    public int getGridSize() {
        return gridSize;
    }
}