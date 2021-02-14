package Places;

import Core.MapSystem;

public class MilitaryAirport extends Airport {
    private String type;

    public MilitaryAirport(int id, String type, int capacity, int[] posXY, MapSystem map) {
            super(id, capacity, posXY, map);
            this.type = type;
    }
    // public boolean spawnPlane();
    public void changeType(String type) {
        this.type = type;
    }
}
