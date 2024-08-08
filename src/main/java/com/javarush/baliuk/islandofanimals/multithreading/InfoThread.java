package com.javarush.baliuk.islandofanimals.multithreading;

import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.Herbivorous;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.Carnivorous;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;

import java.util.ArrayList;
import java.util.List;

// Статистика по локациям острова
public class InfoThread implements Runnable {
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

        System.out.println("Carnivorous population: " + island.getCarnivorousPopulation());
        System.out.println("Herbivorous population: " + island.getHerbivorousPopulation());
        System.out.println("Animals in total: " + island.getAnimalsPopulation());
        //System.out.println("Iteration="+ iteration);
        System.out.println("***********************************************");
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
                System.out.println(" - Plants : " + area.getPlants().size());
            }
        }
    }
}

