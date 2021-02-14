package Places;

import Core.MapSystem;

abstract class Airport extends Place {
    Airport(int id, int capacity, int[] posXY, MapSystem map) {
        super(id, capacity, posXY, map);
    }
}
