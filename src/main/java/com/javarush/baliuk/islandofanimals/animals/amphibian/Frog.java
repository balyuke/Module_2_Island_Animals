package com.javarush.baliuk.islandofanimals.animals.amphibian;

import com.javarush.baliuk.islandofanimals.settings.PresetData;

@PresetData(weight = 0.1, maxSatiety = 0.01, maxAreaPopulation = 500, speed = 1)
public class Frog extends Amphibian {

    @Override
    public String toString() {
        return "Frog";
    }
}