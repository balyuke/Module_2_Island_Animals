package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.settings.PresetData;

@PresetData(weight = 700, maxSatiety = 100, maxAreaPopulation = 10, speed = 3)
public class Buffalo extends Herbivorous {

    @Override
    public String toString() {
        return "Buffalo";
    }
}
