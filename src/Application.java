import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
import Entities.Places.*;

public class Application {
    public static void main(String[] args) {
        // rozmiar kwadratu mapy wraz z iloscia pkt. na mapie
        final int SIZE      = 600;
        final int DENSITY   = 18;
        int id = 1;

        try {
            EntityContainer container = new EntityContainer();

            int amnt = 8;
            int drawSize = (int)((float)SIZE / 2.5);
            while (id <= amnt) {
                int[] xy = {(int)(Math.cos((double)id * 3.14 * 2 / amnt) * drawSize + SIZE / 2),
                            (int)(Math.sin((double)id * 3.14 * 2 / amnt) * drawSize + SIZE / 2)};
                if (id % 3 == 0) {
                    MilitaryAirport port = new MilitaryAirport(id, xy);
                    port.setCapacity(2);
                    port.setWeapon("Bob");
                    container.add(port, id);
                } else {
                    CivilAirport port = new CivilAirport(id, xy);
                    port.setCapacity(3);
                    container.add(port, id);
                }
                id++;
            }
            container.add(new Harbour(id, new int[]{20, SIZE - 20}), id++);
            container.add(new Harbour(id, new int[]{SIZE - 20, 20}), id++);

            Options gui = new Options();
            MapSystem map = new MapSystem(SIZE, DENSITY, gui);
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
}
