import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
import Places.*;

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

            int amnt = 8;
            int drawSize = SIZE / 3;
            for (int i = 1; i <= amnt; ++i) {
                int[] xy = {(int)(Math.cos((double)i * 3.14 * 2 / amnt) * drawSize + SIZE / 2),
                            (int)(Math.sin((double)i * 3.14 * 2 / amnt) * drawSize + SIZE / 2)};
                if (i % 3 == 0) {
                    container.add(new MilitaryAirport(i, "Bob", 20, xy, map), i);
                } else {
                    container.add(new CivilAirport(i, 5, xy, map), i);
                }
            }
            container.drawStations();
            gui.attach(container, map);

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
