import Vehicles.*;
import Utils.*;

public class Application {
    public static void main(String[] args) {
        // rozmiar kwadratu mapy wraz z iloscia pkt. na mapie
        final int SIZE      = 600;
        final int DENSITY   = 5;

        try {
            MapSystem map = new MapSystem(SIZE, DENSITY);
            map.add(new PaintPractice());

            Options options = new Options();
            options.start();

            EntityContainer container = new EntityContainer();
            PassengerPlane test = new PassengerPlane(100, 10, 1, new int[]{10, 10}, map);
            test.start();
            container.add((Airplane)test, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
