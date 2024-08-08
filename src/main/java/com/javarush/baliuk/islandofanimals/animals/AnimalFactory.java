package com.javarush.baliuk.islandofanimals.animals;

public interface AnimalFactory {
    Animal createAnimal(Species species);
}
