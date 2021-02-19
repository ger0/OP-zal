package Entities;

import java.util.Vector;

public interface Viewable {
    public abstract int[]  getPos();
    public abstract int    getId();
    public abstract String getType();

    public default int getCapacity() {
        return -1;
    }
    public default int getLoad() {
        return -1;
    }
    public default int getMaxSpeed() {
        return -1;
    }
    public default int getFuel() {
        return -1;
    }
    public default int getWorkers() {
        return -1;
    }

    // stored vehicles
    public default boolean canStoreVehicles() {
        return false;
    }
    public default Vector<Integer> getStoredVehicles() {
        return null;
    }

    public default boolean canSetDestination() {
        return false;
    }
    public default String getWeapon() {
        return "";
    }
    public default String getCompany() {
        return "";
    }
    public default boolean spawnsMilitary() {
        return false;
    }
    public default Class   getClassType() {
        return null;
    }
}
