import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
import Places.*;
import Vehicles.*;

public class Application {
    public static void main(String[] args) {
        // rozmiar kwadratu mapy wraz z iloscia pkt. na mapie
        final int SIZE      = 600;
        final int DENSITY   = 10;

        try {
            Options gui = new Options();
            gui.start();
            MapSystem map = new MapSystem(SIZE, DENSITY, gui);
            EntityContainer container = new EntityContainer();
            gui.attach(container, map);

            container.add(new CivilAirport(1, 100, new int[]{300, 10}, map), 1);
            container.add(new CivilAirport(2, 100, new int[]{170, 490}, map), 2);
            container.add(new CivilAirport(3, 100, new int[]{430, 490}, map), 3);

            container.add(new MilitaryAirport(4, "THONG", 100, new int[]{30, 240}, map), 4);
            container.add(new MilitaryAirport(5, "THONG", 100, new int[]{570, 240}, map), 5);
            container.drawStations();

            while (true) {
                container.drawVehicles();
                map.repaint();
                Thread.sleep(32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
