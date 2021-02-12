package Utils;

public class Path {
    private int[] gridIndices;
    private boolean reversible;

    Path(int[] gridIndices, boolean reversible) {
        this.gridIndices = gridIndices;
        this.reversible = reversible;
    }

    public int[] getIndices()   { return this.gridIndices; }
    public boolean isReversible() { return this.reversible; }
}
