package com.javarush.baliuk.islandofanimals;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {

        System.err.println("Start simulation game");
        System.err.println("===============================================");

        Island island = new Island(10, 20);
        island.init();

        ScheduledExecutorService executorAreaThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorPlantThread = Executors.newScheduledThreadPool(1);

        //AreaThread[][] areaThreads = new AreaThread[10][20];
        for (int i = 0; i < island.getAreas().length; i++) {
            for (int j = 0; j < island.getAreas()[i].length; j++) {
                Area area = island.getAreas()[i][j];
                //areaThreads[i][j] =  new AreaThread(area, island);
                executorAreaThread.scheduleWithFixedDelay(new AreaThread(area, island), 2, 1, TimeUnit.SECONDS);
                executorPlantThread.scheduleWithFixedDelay(new PlantThread(area), 3, 3, TimeUnit.SECONDS);
            }
        }

        int iterat = 1;
        while (true) {
//            for (int i = 0; i < island.getAreas().length; i++) {
//                for (int j = 0; j < island.getAreas()[i].length; j++) {
//                    areaThreads[i][j].run();
//                }
//            }
            System.out.println("iterat="+iterat+" getAnimalsPopulation()="+island.getAnimalsPopulation());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0) {
                break;
            }
            iterat++;
        }

        executorAreaThread.shutdown();
        executorPlantThread.shutdown();

        System.err.println("===============================================");
        System.err.println("No animals. End of simulation");
    }
}