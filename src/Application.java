import Core.EntityContainer;
import Core.MapSystem;
import Core.Options;
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

            PassengerPlane test = new PassengerPlane(100, 10, 1, new int[]{10, 10}, map);
            test.start();
            container.add((Airplane)test, 1);
            MilitaryPlane test1 = new MilitaryPlane("BONG", 100, 2, new int[]{100, 100}, map);
            test1.start();
            container.add((Airplane)test1, 2);

            CarrierShip test2 = new CarrierShip("mover", 10, 3, new int[]{500, 300}, map);
            test2.start();
            container.add((Ship)test2, 3);

            gui.attach(container);
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
