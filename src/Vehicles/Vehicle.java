package Vehicles;
import Utils.MapSystem;

abstract class Vehicle implements Runnable {
    private final int id;
    private Thread t;
    private int[] posXY;

    MapSystem map;

    Vehicle(int id, int[] posXY, MapSystem map) {
        this.id = id;
        this.posXY = posXY;
        this.map = map;
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
                Thread.sleep(250);
                draw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public abstract void draw();
    public int   getId()    { return this.id; }
    public int[] getPos()   { return this.posXY; }
    // path setting tbd
}
