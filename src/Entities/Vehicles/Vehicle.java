package Entities.Vehicles;

import Core.MapSystem;
import Core.Shapes.Circle;
import Entities.Places.Place;
import Entities.Viewable;

public abstract class Vehicle implements Runnable, Viewable {
    private Thread t;

    private final int   id;
    private int         velocity = 6;
    private int[]       posXY;
    private int[]       targetXY;
    protected Place     targetStation;

    private int tileHas;

    protected boolean   isRenderable = true;
    private boolean     isRunning    = true;

    MapSystem map;

    Vehicle(int id, int[] posXY) {
        this.id = id;
        this.posXY = posXY;
    }
    public void start() {
        try {
            tileHas = map.acquireLock(map.calcIdx(posXY), this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Starting thread: VEHICLE");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    public void run() {
        try {
            while (isRunning) {
                try {
                    update();
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    System.out.println("Exiting Thread...");
                }
            }
        } finally {
            try {
                map.releaseLock(tileHas, this);
                System.out.println("Unlocked tile");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop() {
        setTarget(new int[]{1, 1}, null);
        isRunning = false;
        t.interrupt();
        System.out.println("Stopping thread: VEHICLE");
    }
    public abstract Circle render(int size);

    void update() throws InterruptedException {
        if (targetXY != null && isRenderable) {
            int dirX = targetXY[0] - posXY[0];
            int dirY = targetXY[1] - posXY[1];

            double factor = velocity / Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));
            int newPos[] = {posXY[0] + (int)(dirX * factor),
                            posXY[1] + (int)(dirY * factor)};

            if (map.calcIdx(newPos) != map.calcIdx(posXY)) {
                int tileWants = map.acquireLock(map.calcIdx(newPos), this);
                map.releaseLock(tileHas,  this);
                tileHas = tileWants;
            }
            this.posXY = newPos;

            // check if target coords have been reached
            if (checkCollision()) {
                if (targetStation.setVehicle(this.id)) {
                    setRenderable(false);
                    map.releaseLock(map.calcIdx(posXY), this);
                } else {
                    // dodaje do kolejki!
                }
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
        if (!isRenderable) {
            targetStation.removeVeh(this.id);
            isRenderable = true;
        }
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

    public boolean canSetDestination() {
        return true;
    }
}