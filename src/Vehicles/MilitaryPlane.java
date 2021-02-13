package Vehicles;

import Core.MapSystem;

public class MilitaryPlane extends Airplane {
    private String type;

    public MilitaryPlane(String type, int workers, int id, int[] posXY, MapSystem map) {
        super(workers, id, posXY, map);
        this.type = type;
    }

    public String getType() { return this.type; }
}