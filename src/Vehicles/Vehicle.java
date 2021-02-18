package Vehicles;

import Core.MapSystem;
import Core.Shapes.Circle;
import Places.Place;

public abstract class Vehicle implements Runnable {
    private Thread t;

    private final int   id;
    private int         velocity = 6;
    private final int[] posXY;
    private int[]       targetXY;
    private Place       targetStation;

    private boolean isRenderable = true;

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
        if (targetXY != null && isRenderable) {
            int dirX = targetXY[0] - posXY[0];
            int dirY = targetXY[1] - posXY[1];
            double factor = velocity / Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));
            this.posXY[0] += (int) (dirX * factor);
            this.posXY[1] += (int) (dirY * factor);

            // check if target coords have been reached
            if (checkCollision()) {
                setRenderable(false);
                targetStation.setVehicle(this.id);
            }
        }
    }
    boolean checkCollision() {
        return Math.abs(targetXY[0] - posXY[0]) < map.getGridSize() &&
                Math.abs(targetXY[1] - posXY[1]) < map.getGridSize();
    }

    public int getId()     { return this.id;       }
    public int[] getPos()   { return this.posXY;    }
    public boolean renderable() {
        return isRenderable;
    }

    public void setRenderable(boolean bool) {
        isRenderable = bool;
    }
    public void setMap(MapSystem map) {
        this.map = map;
    }
    public boolean setTarget(int[] tar, Place ref) {
        if (tar.length == 2 && id > 0) {
            if (tar[0] < map.getMapSize() &&
                    tar[0] > 0 && tar[1] > 0 &&
                    tar[1] < map.getMapSize()) {
                this.targetXY       = tar;
                this.targetStation  = ref;
                return true;
            }
        }
        return false;
    }
}