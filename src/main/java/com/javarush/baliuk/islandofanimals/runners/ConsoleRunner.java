package com.javarush.baliuk.islandofanimals.runners;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.InfoThread;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;

import com.javarush.baliuk.islandofanimals.settings.Params;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConsoleRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleRunner.class);

    private static Island island;
    private static Area[][] areas;

    public static void main(String[] args) throws InterruptedException  {

        island = new Island(Params.LENGTH, Params.WIDTH);
        island.init();
        areas = island.getAreas();



        AreaThread[][] areaThreads = new AreaThread[Params.LENGTH][Params.WIDTH];
        PlantThread[][] plantThreads = new PlantThread[Params.LENGTH][Params.WIDTH];
        InfoThread infoThread = new InfoThread(island, Params.ITERATION);

        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                plantThreads[i][j] = new PlantThread(area);
                areaThreads[i][j] =  new AreaThread(area, island);
            }
        }

        infoThread.run();

        while (true) {

            for (int i = 0; i < areas.length; i++) {
                for (int j = 0; j < areas[i].length; j++) {
                    plantThreads[i][j].run();
                    areaThreads[i][j].run();
                }
            }

            infoThread.run();

            try {
                Thread.sleep(Params.DELAY_SEC);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0 || infoThread.getCurrentIteration() > Params.MAX_ITERATION) {
                infoThread.printEndGame();
                break;
            }

        }

    }

}