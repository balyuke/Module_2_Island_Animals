package com.javarush.baliuk.islandofanimals.runners;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.InfoThread;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;

import com.javarush.baliuk.islandofanimals.settings.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ConsoleThreadsRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleThreadsRunner.class);

    private static Island island;
    private static Area[][] areas;

    public static void main(String[] args) throws InterruptedException  {

        island = new Island(Params.LENGTH, Params.WIDTH);
        island.init();
        areas = island.getAreas();

        ScheduledExecutorService executorInfoThread = Executors.newScheduledThreadPool(Params.CORE_POOL_SIZE);
        ScheduledExecutorService executorAreaThread = Executors.newScheduledThreadPool(Params.CORE_POOL_SIZE);
        ScheduledExecutorService executorPlantThread = Executors.newScheduledThreadPool(Params.CORE_POOL_SIZE);

        InfoThread infoThread = new InfoThread(island, Params.ITERATION);
        // задача InfoThread будет запланирована на выполнение через 1 секунду после запуска, а затем будет выполняться каждые 2 секунды после завершения предыдущего выполнения.
        executorInfoThread.scheduleWithFixedDelay(infoThread, Params.INFO_INITIAL_DELAY, Params.INFO_DELAY, TimeUnit.SECONDS);

//        Thread[][] threadsAreas = new Thread[LENGTH][WIDTH];
//        Thread[][] threadsPlants = new Thread[LENGTH][WIDTH];
//        Thread treadStat = new Thread(new InfoThread(island, iteration));

        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                // задача AreaThread будет запланирована на выполнение через 2 секунду после запуска, а затем будет выполняться каждые 1 секунды после завершения предыдущего выполнения.
                executorAreaThread.scheduleWithFixedDelay(new AreaThread(area, island), Params.AREA_INITIAL_DELAY, Params.AREA_DELAY, TimeUnit.SECONDS);
                // задача PlantThread будет запланирована на выполнение через 3 секунду после запуска, а затем будет выполняться каждые 3 секунды после завершения предыдущего выполнения.
                executorPlantThread.scheduleWithFixedDelay(new PlantThread(area), Params.PLANT_INITIAL_DELAY, Params.PLANT_DELAY, TimeUnit.SECONDS);
//                threadsAreas[i][j] = new Thread(new PlantThread(area));
//                threadsPlants[i][j] = new Thread(new AreaThread(area, island));
//                threadsAreas[i][j].start();
//                threadsAreas[i][j].join();
//                threadsPlants[i][j].start();
//                threadsPlants[i][j].join();
            }
        }

//        treadStat.start();
//        treadStat.join();

        while (true) {

//            treadStat.join();

            try {
                Thread.sleep(Params.DELAY_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0 || infoThread.getCurrentIteration() > Params.MAX_ITERATION) {
                break;
            }

        }

        executorInfoThread.shutdown();
        executorAreaThread.shutdown();
        executorPlantThread.shutdown();

        LOG.info("===============================================");
        LOG.info("No animals or iteration limit reached. End of simulation");


    }

}