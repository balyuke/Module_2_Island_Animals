package com.javarush.baliuk.islandofanimals.animals.amphibian;

import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.Species;

public class AmphibianFactory implements AnimalFactory {
    @Override
    public Amphibian createAnimal(Species species) {
        Amphibian amphibian;
        switch (species) {
            case FROG -> amphibian = new Frog();
            default -> amphibian = null;
        }
        return amphibian;
    }
}