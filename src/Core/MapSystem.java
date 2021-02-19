package Core;

import Entities.Vehicles.Airplane;
import Entities.Vehicles.Ship;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

public class MapSystem extends JFrame {
    private final int SIZE;
    private final int gridSize;
    private final int gridDensity;

    // semaphores
    private Semaphore[] shipPlacement;
    private Semaphore[] planePlacement;

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

            this.shipPlacement  = new Semaphore[density * density];
            this.planePlacement = new Semaphore[density * density];
            for (int i = 0; i < density * density; i++) {
                shipPlacement[i]    = new Semaphore(1);
                planePlacement[i]   = new Semaphore(1);
            }

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
            public void mousePressed(MouseEvent mouseEvent)     {}
            public void mouseReleased(MouseEvent mouseEvent)    {}
            public void mouseEntered(MouseEvent mouseEvent)     {}
            public void mouseExited(MouseEvent mouseEvent)      {}
        });
    }
    public int getMapSize() {
        return SIZE;
    }
    public int getGridSize() {
        return gridSize;
    }

    // semaphore operations
    public int acquireLock(int idx, Object type) throws InterruptedException {
        if (idx > -1 && idx < gridDensity * gridDensity) {
            if (Airplane.class.isAssignableFrom(type.getClass())) {
                planePlacement[idx].acquire();
            } else if (Ship.class.isAssignableFrom(type.getClass())) {
                shipPlacement[idx].acquire();
            }
        }
        return idx;
    }
    public void releaseLock(int idx, Object type) throws InterruptedException {
        if (idx > -1 && idx < gridDensity * gridDensity) {
            if (Airplane.class.isAssignableFrom(type.getClass())) {
                planePlacement[idx].release();
            } else if (Ship.class.isAssignableFrom(type.getClass())) {
                shipPlacement[idx].release();
            }
        }
    }
    public int calcIdx(int[] pos) {
        if (pos.length == 2) {
            return (pos[0] / gridSize) + ((pos[1] / gridSize) * gridDensity);
        } else
            return -1;
    }
}