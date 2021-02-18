package Vehicles;

import Core.MapSystem;
import Core.Shapes.Circle;

public abstract class Vehicle implements Runnable {
    private final int id;
    private int velocity = 6;
    private Thread t;
    private final int[] posXY;
    private int[] targetXY;

    MapSystem map;

    Vehicle(int id, int[] posXY) {
        this.id = id;
        this.posXY = posXY;
    }
    public void start() {
        System.out.println("Starting thread: VEHICLE");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    public void run() {
        while (true) {
            try {
                update();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public abstract Circle render(int size);
    void changeVelocity(int vel) {
        if (velocity > 1) {
            this.velocity = vel;
        }
    }
    void update() {
        if (targetXY != null) {
            int dirX = targetXY[0] - posXY[0];
            int dirY = targetXY[1] - posXY[1];
            double factor = velocity / Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));
            this.posXY[0] += (int)(dirX * factor);
            this.posXY[1] += (int)(dirY * factor);
        }
    }
    public int getId()     { return this.id;       }
    public int[] getPos()   { return this.posXY;    }

    public void setMap(MapSystem map) {
        this.map = map;
    }
    public boolean setTarget(int[] tar) {
        if (tar.length == 2) {
            if (tar[0] < map.getMapSize() &&
                    tar[0] > 0 && tar[1] > 0 &&
                    tar[1] < map.getMapSize()) {
                this.targetXY = tar;
                return true;
            }
        }
        return false;
    }
}