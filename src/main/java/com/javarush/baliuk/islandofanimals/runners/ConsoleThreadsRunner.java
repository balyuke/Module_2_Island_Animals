package com.javarush.baliuk.islandofanimals.runners;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.InfoThread;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;

//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.javarush.baliuk.islandofanimals.settings.Params.*;

public class ConsoleThreadsRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleThreadsRunner.class);

    private static Island island;
    private static Area[][] areas;

    public static void main(String[] args) throws InterruptedException  {

        island = new Island(LENGTH, WIDTH);
        island.init();
        areas = island.getAreas();

        int iteration = 1;

        ScheduledExecutorService executorInfoThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorAreaThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorPlantThread = Executors.newScheduledThreadPool(1);

        executorInfoThread.scheduleWithFixedDelay(new InfoThread(island, iteration), 1, 2, TimeUnit.SECONDS);

//        Thread[][] threadsAreas = new Thread[LENGTH][WIDTH];
//        Thread[][] threadsPlants = new Thread[LENGTH][WIDTH];
//        Thread treadStat = new Thread(new InfoThread(island, iteration));

        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
//                threadsAreas[i][j] = new Thread(new PlantThread(area));
//                threadsPlants[i][j] = new Thread(new AreaThread(area, island));
//                threadsAreas[i][j].start();
//                threadsAreas[i][j].join();
//                threadsPlants[i][j].start();
//                threadsPlants[i][j].join();
                executorAreaThread.scheduleWithFixedDelay(new AreaThread(area, island), 2, 1, TimeUnit.SECONDS);
                executorPlantThread.scheduleWithFixedDelay(new PlantThread(area), 3, 3, TimeUnit.SECONDS);
            }
        }

//        treadStat.start();
//        treadStat.join();

        while (true) {

//            treadStat.join();
            iteration++;

            try {
                Thread.sleep(DELAY);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0) {
                break;
            }

        }

        //executorInfoThread.execute();

        executorInfoThread.shutdown();
        executorAreaThread.shutdown();
        executorPlantThread.shutdown();

        LOG.info("===============================================");
        LOG.info("No animals. End of simulation");


    }

}