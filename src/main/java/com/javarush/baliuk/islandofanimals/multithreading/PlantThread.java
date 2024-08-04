package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.plants.Plant;

public class PlantThread implements Runnable {
    private final Area area;

    public PlantThread(Area area) {
        this.area = area;
    }

    @Override
    public void run() {
        area.grow();
    }

}
