import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
import Places.*;

public class Application {
    public static void main(String[] args) {
        // rozmiar kwadratu mapy wraz z iloscia pkt. na mapie
        final int SIZE      = 600;
        final int DENSITY   = 20;
        int id = 1;

        try {
            Options gui = new Options();
            gui.start();
            MapSystem map = new MapSystem(SIZE, DENSITY, gui);
            EntityContainer container = new EntityContainer();

            int amnt = 8;
            int drawSize = SIZE / 3;
            while (id <= amnt) {
                int[] xy = {(int)(Math.cos((double)id * 3.14 * 2 / amnt) * drawSize + SIZE / 2),
                            (int)(Math.sin((double)id * 3.14 * 2 / amnt) * drawSize + SIZE / 2)};
                if (id % 3 == 0) {
                    container.add(new MilitaryAirport(id, "Bob", 20, xy, map), id);
                } else {
                    container.add(new CivilAirport(id, 5, xy, map), id);
                }
                id++;
            }
            container.drawStations();
            gui.attach(container, map, id);

            while (true) {
                container.drawVehicles();
                container.drawStations();
                map.repaint();
                Thread.sleep(32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
