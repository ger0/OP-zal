import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
import Entities.Places.*;
import Util.StationSpawner;

public class Application {
    final int SIZE      = 600;
    final int DENSITY   = 18;
    int id;

    Application() {
        try {
            EntityContainer container = new EntityContainer();
            id = StationSpawner.spawnCircular(container, SIZE, 10);

            Options gui     = new Options();
            MapSystem map   = new MapSystem(SIZE, DENSITY, gui);
            gui.attach(container, map, id);
            gui.start();

            while (true) {
                map.repaint(container);
                Thread.sleep(32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Application();
    }
}
