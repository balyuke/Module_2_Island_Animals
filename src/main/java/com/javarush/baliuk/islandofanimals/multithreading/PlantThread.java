package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.plants.Plant;

public class PlantThread implements Runnable {
//public class PlantThread extends Thread {
    private final Area area;
    private final Object lock = new Object();

    public PlantThread(Area area) {
        this.area = area;
    }

    @Override
    public void run() {
        synchronized(lock) {
            area.grow();
        }
    }

//    public void grow() {
//        int currentPlantsPopulation = area.getPlants().size();
//        int maxPopulation = area.getMaxAreaPopulation(Plant.class);
//        int localPlantsPopulation = area.getRandomPopulation(maxPopulation);
//        if (currentPlantsPopulation + localPlantsPopulation > maxPopulation) {
//            localPlantsPopulation = maxPopulation - currentPlantsPopulation;
//        }
//        for (int i = 0; i < localPlantsPopulation; i++) {
//            area.getPlants().add(new Plant());
//        }
//    }

}
