package Entities;

import Core.MapSystem;

import java.util.Vector;

 public interface Viewable {
     int[]  getPos();
     int    getId();
     String getType();

     default int getCapacity() {
        return -1;
    }
     default int getLoad() {
        return -1;
    }
     default int getMaxSpeed() {
        return -1;
    }
     default int getFuel() {
        return -1;
    }
     default int getWorkers() {
        return -1;
    }

    // stored vehicles
     default boolean canStoreVehicles() {
        return false;
    }
     default Vector<Integer> getStoredVehicles() {
        return null;
    }

     default boolean canSetDestination() {
        return false;
    }
     default String getWeapon() {
        return "";
    }
     default String getCompany() {
        return "";
    }
     default int getSelected() {
        return -1;
    }
     default boolean spawnsMilitary() {
        return false;
    }

     default void     setMap(MapSystem map) {}

     default void     setCapacity(int x) {}
     default void     setWorkers(int x) {}
     default void     setLoad(int x) {}
     default void     setMaxSpeed(int x) {}
     default void     setWeapon(String s) {}
     default void     setCompany(String s) {}
}
