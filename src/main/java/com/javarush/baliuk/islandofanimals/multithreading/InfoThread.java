package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.Main;
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
public class InfoThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(InfoThread.class);

    private final Island island;
    private final Area[][] areas;

    //private final int iteration;

    public InfoThread(Island island/*, int iteration*/) {
        this.island = island;
        this.areas = island.getAreas();
        //this.iteration = iteration;
    }

    @Override
    public void run() {
        printInfo();
    }

    public void printInfo() {

//        System.out.println("Carnivorous population: " + island.getCarnivorousPopulation());
//        System.out.println("Herbivorous population: " + island.getHerbivorousPopulation());
//        System.out.println("Animals in total: " + island.getAnimalsPopulation());
        LOG.info("Carnivorous population: {}", island.getCarnivorousPopulation());
        LOG.info("Herbivorous population: {}", island.getHerbivorousPopulation());
        LOG.info("Animals in total: {}", island.getAnimalsPopulation());
        //System.out.println("Iteration="+ iteration);
        System.out.println("***********************************************");
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
//                System.out.println("Area [" + (area.getPosition().getX() + 1) + ", " + (area.getPosition().getY() + 1) + "]");
//                System.out.println(" - Carnivorous:");
                LOG.trace("Area [{}, {}]", area.getPosition().getX() + 1, area.getPosition().getY() + 1);
                LOG.trace(" - Carnivorous:");
                Main.printCountCarnivorous(area);
//                System.out.println(" - Herbivorous:");
                LOG.trace(" - Herbivorous:");
                Main.printCountHerbivorous(area);
//                System.out.println(" - Plants : " + area.getPlants().size());
                LOG.trace(" - Plants : {}", area.getPlants().size());
            }
        }
    }
}

