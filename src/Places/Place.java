package Places;

abstract class Place {
    private int capacity;
    private int[] posXY;

    // public void draw();

    Place(int capacity, int[] posXY) {
        this.capacity = capacity;
        this.posXY = posXY;
    }
}
