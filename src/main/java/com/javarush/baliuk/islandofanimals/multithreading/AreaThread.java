package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;

public class AreaThread implements Runnable {
//public class AreaThread extends Thread {
    Area area;
    Island island;
    private final Object lock = new Object();

    public AreaThread(Area area, Island island) {
        this.area = area;
        this.island = island;
    }

    @Override
    public void run() {
        synchronized(lock) {
            area.eat();         // 1
            area.reproduce();   // 2
            area.move(island.getAreas());   // 3
            area.die();         // 4
        }
    }
}