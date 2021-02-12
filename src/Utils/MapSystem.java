package Utils;
import javax.swing.*;

import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

public class MapSystem extends JFrame {
    private final int SIZE;
    private final int gridSize;
    private final PaintPractice panel = new PaintPractice();

    private Map<Integer, int[]> planeCoords;
    private Map<Integer, int[]> shipCoords;

    public MapSystem(int size, int gridSize) {
        if (size < 10) {
            throw new IllegalArgumentException("window's size: " +
                    Integer.toString(size));
        } else if (gridSize < 1) {
            throw new IllegalArgumentException("window's density: " +
                    Integer.toString(gridSize));
        } else {
            setSize(size, size);
            SIZE = size;
            this.gridSize = gridSize;

            setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(panel);

            planeCoords     = new HashMap<Integer, int[]>();
            shipCoords      = new HashMap<Integer, int[]>();
        }
    }
    // key = id
    public synchronized void pushPlane(int key, int[] posXY) {
        planeCoords.put(key, posXY);
    }
    public synchronized void pushShip(int key, int[] posXY) {
        shipCoords.put(key, posXY);
    }
    public void repaint() {
        planeCoords.forEach((k, pos) -> panel.push(pos[0], pos[1], Color.RED));
        super.repaint();
    }
}