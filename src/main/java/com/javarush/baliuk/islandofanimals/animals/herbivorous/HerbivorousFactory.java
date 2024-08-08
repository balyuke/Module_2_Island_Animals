package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.Species;

public class HerbivorousFactory implements AnimalFactory {
    @Override
    public Herbivorous createAnimal(Species species) {
        Herbivorous herbivorous;
        switch (species) {
            case BOAR -> herbivorous = new Boar();
            case BUFFALO -> herbivorous = new Buffalo();
            case CATERPILLAR -> herbivorous = new Caterpillar();
            case DEER -> herbivorous = new Deer();
            case DUCK -> herbivorous = new Duck();
            case GOAT -> herbivorous = new Goat();
            case HORSE -> herbivorous = new Horse();
            case MOUSE -> herbivorous = new Mouse();
            case RABBIT -> herbivorous = new Rabbit();
            case SHEEP -> herbivorous = new Sheep();
            default -> herbivorous = null;
        }
        return herbivorous;
    }
}

