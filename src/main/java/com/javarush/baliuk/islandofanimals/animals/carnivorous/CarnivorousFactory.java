package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.AnimalSpecies;

public class CarnivorousFactory implements AnimalFactory {
    @Override
    public Carnivorous createAnimal(AnimalSpecies species) {
        Carnivorous carnivorous;
        switch (species) {
            case BEAR -> carnivorous = new Bear();
            case ANACONDA -> carnivorous = new Anaconda();
            case EAGLE -> carnivorous = new Eagle();
            case FOX -> carnivorous = new Fox();
            case WOLF -> carnivorous = new Wolf();
            default -> carnivorous = null;
        }
        return carnivorous;
    }
}