package com.javarush.baliuk.islandofanimals.utils;

import com.javarush.baliuk.islandofanimals.abstraction.Organism;
import com.javarush.baliuk.islandofanimals.entities.Island;
import com.javarush.baliuk.islandofanimals.entities.organisms.Herb;
import com.javarush.baliuk.islandofanimals.entities.organisms.herbivores.*;
import com.javarush.baliuk.islandofanimals.entities.organisms.predators.*;

import java.util.ArrayList;
import java.util.List;

public class InitializingIslandLocation {
    private Island island = new Island();
    private Woolf woolf = new Woolf();
    private Bear bear = new Bear();
    private Boa boa = new Boa();
    private Eagle eagle = new Eagle();
    private Fox fox = new Fox();
    private Horse horse = new Horse();
    private Boar boar = new Boar();
    private Buffalo buffalo = new Buffalo();
    private Caterpillar caterpillar = new Caterpillar();
    private Deer deer = new Deer();
    private Duck duck = new Duck();
    private Goat goat = new Goat();
    private Mouse mouse = new Mouse();
    private Rabbit rabbit = new Rabbit();
    private Sheep sheep = new Sheep();
    private Herb herb = new Herb();

    public InitializingIslandLocation() {
    }



    private List<Organism> initOneCellHabitants(){
        List<Organism>  habitantsAndThemQuantity =new ArrayList<>();
        for (int i = 0; i<RandomAddOrganism.randomHabitants(woolf);i++){habitantsAndThemQuantity.add(woolf);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(bear);i++){habitantsAndThemQuantity.add(bear);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(boa);i++){habitantsAndThemQuantity.add(boa);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(eagle);i++){habitantsAndThemQuantity.add(eagle);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(fox);i++){habitantsAndThemQuantity.add(fox);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(horse);i++){habitantsAndThemQuantity.add(horse);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(boar);i++){habitantsAndThemQuantity.add(boar);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(buffalo);i++){habitantsAndThemQuantity.add(buffalo);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(caterpillar);i++){habitantsAndThemQuantity.add(caterpillar);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(deer);i++){habitantsAndThemQuantity.add(deer);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(duck);i++){habitantsAndThemQuantity.add(duck);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(goat);i++){habitantsAndThemQuantity.add(goat);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(mouse);i++){habitantsAndThemQuantity.add(mouse);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(rabbit);i++){habitantsAndThemQuantity.add(rabbit);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(sheep);i++){habitantsAndThemQuantity.add(sheep);}
        for (int i = 0; i<RandomAddOrganism.randomHabitants(herb);i++){habitantsAndThemQuantity.add(herb);}
        return habitantsAndThemQuantity;
    }

    public List<Organism>[][] initGameFields(){
        List<Organism>[][] locationOfGame =island.getLocationOfSimulationIsland();

        for (int row = 0; row < island.getRows(); row++) {
            for (int column = 0; column < island.getColumns(); column++) {
                locationOfGame[row][column] =  initOneCellHabitants();
            }
        }
        return locationOfGame;
    }

}
