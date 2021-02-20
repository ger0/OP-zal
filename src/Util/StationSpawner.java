package Util;

import Core.EntityContainer;
import Entities.Places.CivilAirport;
import Entities.Places.Harbour;
import Entities.Places.MilitaryAirport;

public class StationSpawner {

    // returns recent id
    public static int spawnCircular(EntityContainer c, int size, int amnt) {
        int id = 1;
        int drawSize = (int)((float)size/ 2.5);

        while (id <= amnt) {
            int[] xy = {(int)(Math.cos((double)id *
                    3.14 * 2 / amnt) * drawSize + size / 2),
                    (int)(Math.sin((double)id *
                            3.14 * 2 / amnt) * drawSize + size / 2)};
            if (id % 3 == 0) {
                MilitaryAirport port = new MilitaryAirport(id, xy);
                port.setCapacity(2);
                port.setWeapon("Bob");
                c.add(port, id);
            } else {
                CivilAirport port = new CivilAirport(id, xy);
                port.setCapacity(3);
                c.add(port, id);
            }
            id++;
        }
        // 2 harbours
        c.add(new Harbour(id, new int[]{20, size - 20}), id++);
        c.add(new Harbour(id, new int[]{size - 20, 20}), id++);
        return id;
    }
}
