package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.HashMap;

public class MapSystem extends JFrame {
    private final int SIZE;
    private final int gridSize;
    private final int gridDensity;

    // gui object reference
    private Options gui;

    // contains coordinates for different types of entities drawn on map
    private final PaintPractice panel = new PaintPractice();
    private Map<Integer, int[]> planeCoords = new HashMap<Integer, int[]>();
    private Map<Integer, int[]> shipCoords  = new HashMap<Integer, int[]>();
    private Map<Integer, int[]> statCoords  = new HashMap<Integer, int[]>();

    public MapSystem(int size, int density, Options gui) {
        if (size < 10) {
            throw new IllegalArgumentException("window's size: " +
                    Integer.toString(size));
        } else if (density < 1) {
            throw new IllegalArgumentException("window's density: " +
                    Integer.toString(density));
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
    // key = id
    public synchronized void pushPlane(int key, int[] posXY) {
        planeCoords.put(key, posXY);
    }
    public synchronized void pushShip(int key, int[] posXY) {
        shipCoords.put(key, posXY);
    }
    public synchronized void deleteVehicle(int key) {
        shipCoords.remove(key);
        planeCoords.remove(key);
    }
    public synchronized void pushStation(int key, int[] posXY) {
        statCoords.put(key, posXY);
    }
    public void repaint() {
        planeCoords.forEach((k, pos) -> panel.push(pos[0], pos[1], Color.RED, gridSize));
        shipCoords.forEach((k, pos) ->  panel.push(pos[0], pos[1], Color.BLUE, gridSize));
        statCoords.forEach((k, pos) ->  panel.push(pos[0], pos[1], Color.BLACK, gridSize + 10));
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