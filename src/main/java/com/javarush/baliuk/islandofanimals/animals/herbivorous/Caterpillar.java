package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.PresetData;
import com.javarush.baliuk.islandofanimals.island.Area;

@PresetData(weight = 0.01, maxSatiety = 0, maxAreaPopulation = 1000)
public class Caterpillar extends Herbivorous {

    @Override
    public boolean move(Area currentArea, Area[][] areas) {
        return false;
    }

    @Override
    public String toString() {
        return "Caterpillar";
    }
}
