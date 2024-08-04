package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;

public class AreaThread implements Runnable {
    Area area;
    Island island;

    public AreaThread(Area area, Island island) {
        this.area = area;
        this.island = island;
    }

    @Override
    public void run() {
        area.eat();         // 1
        area.reproduce();   // 2
        area.move(island.getAreas());   // 3
        area.die();         // 4
    }
}