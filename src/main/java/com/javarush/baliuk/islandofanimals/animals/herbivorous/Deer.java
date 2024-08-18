package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.settings.PresetData;

@PresetData(weight = 300, maxSatiety = 50, maxAreaPopulation = 20, speed = 4)
public class Deer extends Herbivorous {

    @Override
    public String toString() {
        return "Deer";
    }
}
