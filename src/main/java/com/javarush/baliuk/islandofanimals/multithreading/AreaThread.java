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
        area.eat();
        area.reproduce();
        area.move(island.getAreas());
        area.die();
    }
}