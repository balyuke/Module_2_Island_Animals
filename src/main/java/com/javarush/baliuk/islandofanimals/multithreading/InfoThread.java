package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.Herbivorous;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.Carnivorous;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Статистика по локациям острова
public class InfoThread implements Runnable, AutoCloseable  {
//public class InfoThread extends Thread implements AutoCloseable  {

    private static final Logger LOG = LoggerFactory.getLogger(InfoThread.class);
    private final Object lock = new Object();

    private final Island island;
    private final Area[][] areas;
    private int currentIteration;

    public int getCurrentIteration() {
        return currentIteration;
    }

    public InfoThread(Island island, int iteration) {
        this.island = island;
        this.areas = island.getAreas();
        this.currentIteration = iteration;
        printStartGame(island);
    }

    private void printStartGame(Island island) {
        LOG.info("Start simulation game");
        LOG.info("===============================================");
        LOG.info(island.toString());
        LOG.info("***********************************************");
    }

    @Override
    public void run() {
        synchronized(lock) {
            infoStat(this.currentIteration);
            this.currentIteration++;
        }
    }

    private void infoStat(int iteration) {
        int plantCount = 0;
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                LOG.trace("Area [{}, {}]", area.getPosition().getX() + 1, area.getPosition().getY() + 1);
                LOG.trace(" - Carnivorous:");
                printCountCarnivorous(area);
                LOG.trace(" - Herbivorous:");
                printCountHerbivorous(area);
                plantCount = plantCount + area.getPlants().size();
                LOG.trace(" - Plants : {}", area.getPlants().size());
            }
        }
        LOG.info("Plants in total: {}", plantCount);
        LOG.info("Carnivorous population: {}", island.getCarnivorousPopulation());
        LOG.info("Herbivorous population: {}", island.getHerbivorousPopulation());
        LOG.info("Animals in total: {}", island.getAnimalsPopulation());
        LOG.info("Iteration={}", iteration);
        LOG.info("***********************************************");
    }

    private void printCountHerbivorous(Area area) {
        for (Species species : Species.values()) {
            List<Herbivorous> herbivorous = new ArrayList<>(area.getHerbivorous());
            int herbivorousNumber = (int) herbivorous.stream().filter(h -> h.toString().equalsIgnoreCase(species.toString())).count();
            if (herbivorousNumber > 0) {
                LOG.trace("   - {} : {}", species, herbivorousNumber);
            }
        }
    }

    private void printCountCarnivorous(Area area) {
        for (Species species : Species.values()) {
            List<Carnivorous> carnivorous = new ArrayList<>(area.getCarnivorous());
            int carnivorousNumber = (int) carnivorous.stream().filter(c -> c.toString().equalsIgnoreCase(species.toString())).count();
            if (carnivorousNumber > 0) {
                LOG.trace("   - {} : {}", species, carnivorousNumber);
            }
        }
    }

    @Override
    public void close() /*throws Exception*/ {
        printEndGame();
    }

    public void printEndGame() {
        LOG.info("===============================================");
        LOG.info("No animals or iteration limit reached. End of simulation");
    }
}

