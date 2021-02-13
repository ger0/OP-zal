package Core;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.HashMap;

public class MapSystem extends JFrame {
    private final int SIZE;
    private final int gridSize;
    private final int gridDensity;

    private Options gui;
    //contains coordinates for different types of entities
    private final PaintPractice panel = new PaintPractice();
    private Map<Integer, int[]> planeCoords;
    private Map<Integer, int[]> shipCoords;

    // stores vehicle ids
    private int gridIndices[];

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
            gridIndices = new int[gridDensity * gridDensity];
            this.gui = gui;

            // JFrame settings
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(panel);
            setVisible(true);
            initMouseListener();

            planeCoords     = new HashMap<Integer, int[]>();
            shipCoords      = new HashMap<Integer, int[]>();
        }
    }
    // key = id
    private synchronized void pushIndice(int key, int[] posXY) {
        gridIndices[posXY[0] / gridSize + (posXY[1] / gridSize) * gridDensity] = key;
    }
    public synchronized void pushPlane(int key, int[] posXY) {
        pushIndice(key, posXY);
        planeCoords.put(key, posXY);
    }
    public synchronized void pushShip(int key, int[] posXY) {
        pushIndice(key, posXY);
        shipCoords.put(key, posXY);
    }
    public synchronized void deleteVehicle(int key) {
        shipCoords.remove(key);
        planeCoords.remove(key);
    }
    public void repaint() {
        planeCoords.forEach((k, pos) -> panel.push(pos[0], pos[1], Color.RED));
        shipCoords.forEach((k, pos) -> panel.push(pos[0], pos[1], Color.BLUE));
        super.repaint();
    }
    private void initMouseListener() {
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int id = 0;
                id = gridIndices[
                        e.getPoint().x / gridSize
                        + (e.getPoint().y / gridSize) * gridDensity];
                gui.select(id);
            }
            public void mousePressed(MouseEvent mouseEvent) {}
            public void mouseReleased(MouseEvent mouseEvent) {}
            public void mouseEntered(MouseEvent mouseEvent) {}
            public void mouseExited(MouseEvent mouseEvent) {}
        });
    }
}