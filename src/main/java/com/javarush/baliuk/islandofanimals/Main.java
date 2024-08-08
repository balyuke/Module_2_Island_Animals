package com.javarush.baliuk.islandofanimals;

import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.Carnivorous;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.Herbivorous;
import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.InfoThread;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;
import com.javarush.baliuk.islandofanimals.plants.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class Main {

    public static int lengthOfIsland = 10;
    public static int widthOfIsland = 20;


    private static Island island;
    private static Area[][] areas;

    public static void main(String[] args) {

        System.err.println("Start simulation game");
        System.err.println("===============================================");


        //Island island = new Island(10, 20);
        island = new Island(lengthOfIsland, widthOfIsland);
        island.init();
        areas = island.getAreas();
        int iteration = 1;

        ScheduledExecutorService executorThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorAreaThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorPlantThread = Executors.newScheduledThreadPool(1);

        //executorThread.scheduleWithFixedDelay(new InfoThread(island/*, iteration++*/), 1, 2, TimeUnit.SECONDS);

        AreaThread[][] areaThreads = new AreaThread[lengthOfIsland][widthOfIsland];
        PlantThread[][] plantThreads = new PlantThread[lengthOfIsland][widthOfIsland];
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                areaThreads[i][j] =  new AreaThread(area, island);
                plantThreads[i][j] = new PlantThread(area);
                //executorAreaThread.scheduleWithFixedDelay(new AreaThread(area, island), 2, 1, TimeUnit.SECONDS);
                //executorPlantThread.scheduleWithFixedDelay(new PlantThread(area), 3, 3, TimeUnit.SECONDS);
            }
        }

        while (true) {
            for (int i = 0; i < areas.length; i++) {
                for (int j = 0; j < areas[i].length; j++) {
                    plantThreads[i][j].run();
                    areaThreads[i][j].run();
                }
            }

            int plantCount = 0;
//            for (int i = 0; i < areas.length; i++) {
//                for (int j = 0; j < areas[i].length; j++) {
//                    plantCount = plantCount + areas[i][j].getPlants().size();
//                }
//            }
//            System.out.println("***********************************************");

//            printInfo();
//            System.out.println("Plants in total: " + plantCount);
            System.out.println("Carnivorous population: " + island.getCarnivorousPopulation());
            System.out.println("Herbivorous population: " + island.getHerbivorousPopulation());
            System.out.println("Animals in total: " + island.getAnimalsPopulation());
            System.out.println("Iteration="+ iteration);
            System.out.println("***********************************************");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0) {
                break;
            }

            //System.out.println("iteration="+iteration+" getAnimalsPopulation()="+island.getAnimalsPopulation());
            iteration++;
        }

        executorThread.shutdown();
        executorAreaThread.shutdown();
        executorPlantThread.shutdown();

        System.err.println("===============================================");
        System.err.println("No animals. End of simulation");
    }

    private static void printInfo() {
        int plantCount = 0;
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                System.out.println("Area [" + (area.getPosition().getX() + 1) + ", " + (area.getPosition().getY() + 1) + "]");
                System.out.println(" - Carnivorous:");
                for (Species species : Species.values()) {
                    List<Carnivorous> carnivorous = new ArrayList<>(area.getCarnivorous());
                    int carnivorousNumber = (int) carnivorous.stream().filter(c -> c.toString().equalsIgnoreCase(species.toString())).count();
                    if (carnivorousNumber > 0) {
                        System.out.println("   - " + species + " : " + carnivorousNumber);
                    }
                }
                System.out.println(" - Herbivorous:");
                for (Species species : Species.values()) {
                    List<Herbivorous> herbivorous = new ArrayList<>(area.getHerbivorous());
                    int herbivorousNumber = (int) herbivorous.stream().filter(h -> h.toString().equalsIgnoreCase(species.toString())).count();
                    if (herbivorousNumber > 0) {
                        System.out.println("   - " + species + " : " + herbivorousNumber);
                    }
                }
                plantCount = plantCount + area.getPlants().size();
                System.out.println(" - Plants : " + area.getPlants().size());
            }
        }
        System.out.println("Plants in total: " + plantCount);
        //return plantCount;
    }
}