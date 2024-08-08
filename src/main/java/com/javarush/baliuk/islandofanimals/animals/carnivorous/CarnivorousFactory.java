package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.Species;

public class CarnivorousFactory implements AnimalFactory {
    @Override
    public Carnivorous createAnimal(Species species) {
        Carnivorous carnivorous;
        switch (species) {
            case BEAR -> carnivorous = new Bear();
            case BOA -> carnivorous = new Boa();
            case EAGLE -> carnivorous = new Eagle();
            case FOX -> carnivorous = new Fox();
            case WOLF -> carnivorous = new Wolf();
            default -> carnivorous = null;
        }
        return carnivorous;
    }
}