package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.PresetData;

@PresetData(weight = 400, maxSatiety = 60, maxAreaPopulation = 20, speed = 4)
public class Horse extends Herbivorous {

    @Override
    public String toString() {
        return "Horse";
    }
}