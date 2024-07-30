package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.PresetData;

@PresetData(weight = 60, maxSatiety = 10, maxAreaPopulation = 140, speed = 3)
public class Goat extends Herbivorous {

    @Override
    public String toString() {
        return "Goat";
    }
}

