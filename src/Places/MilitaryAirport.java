package Places;

public class MilitaryAirport extends Airport {
    private String type;

    MilitaryAirport(String type, int capacity, int[] posXY) {
            super(capacity, posXY);
            this.type = type;
    }
    // public boolean spawnPlane();
    public void changeType(String type) {
        this.type = type;
    }
}
