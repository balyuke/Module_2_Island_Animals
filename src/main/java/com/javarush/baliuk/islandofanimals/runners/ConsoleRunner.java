package com.javarush.baliuk.islandofanimals.runners;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import com.javarush.baliuk.islandofanimals.multithreading.InfoThread;
import com.javarush.baliuk.islandofanimals.multithreading.PlantThread;

//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.javarush.baliuk.islandofanimals.settings.Params.*;

public class ConsoleRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleRunner.class);

    private static Island island;
    private static Area[][] areas;

    public static void main(String[] args) throws InterruptedException  {

        island = new Island(LENGTH, WIDTH);
        island.init();
        areas = island.getAreas();

        int iteration = 1;

        AreaThread[][] areaThreads = new AreaThread[LENGTH][WIDTH];
        PlantThread[][] plantThreads = new PlantThread[LENGTH][WIDTH];
        InfoThread infoThread = new InfoThread(island, iteration);

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
                Thread.sleep(DELAY_SEC);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0) {
                infoThread.printEndGame();
                break;
            }

        }

    }

//    private static void infoStat(int iteration) {
//        int plantCount = 0;
//        for (int i = 0; i < areas.length; i++) {
//            for (int j = 0; j < areas[i].length; j++) {
//                Area area = areas[i][j];
//                LOG.trace("Area [{}, {}]", area.getPosition().getX() + 1, area.getPosition().getY() + 1);
//                LOG.trace(" - Carnivorous:");
//                printCountCarnivorous(area);
//                LOG.trace(" - Herbivorous:");
//                printCountHerbivorous(area);
//                plantCount = plantCount + area.getPlants().size();
//                LOG.trace(" - Plants : {}", area.getPlants().size());
//            }
//        }
//        LOG.info("Plants in total: {}", plantCount);
//        LOG.info("Carnivorous population: {}", island.getCarnivorousPopulation());
//        LOG.info("Herbivorous population: {}", island.getHerbivorousPopulation());
//        LOG.info("Animals in total: {}", island.getAnimalsPopulation());
//        LOG.info("Iteration={}", iteration);
//        LOG.info("***********************************************");
//        //return plantCount;
//    }
//
//    private void printCountHerbivorous(Area area) {
//        for (Species species : Species.values()) {
//            List<Herbivorous> herbivorous = new ArrayList<>(area.getHerbivorous());
//            int herbivorousNumber = (int) herbivorous.stream().filter(h -> h.toString().equalsIgnoreCase(species.toString())).count();
//            if (herbivorousNumber > 0) {
//                LOG.trace("   - {} : {}", species, herbivorousNumber);
//            }
//        }
//    }
//
//    private void printCountCarnivorous(Area area) {
//        for (Species species : Species.values()) {
//            List<Carnivorous> carnivorous = new ArrayList<>(area.getCarnivorous());
//            int carnivorousNumber = (int) carnivorous.stream().filter(c -> c.toString().equalsIgnoreCase(species.toString())).count();
//            if (carnivorousNumber > 0) {
//                LOG.trace("   - {} : {}", species, carnivorousNumber);
//            }
//        }
//    }
}