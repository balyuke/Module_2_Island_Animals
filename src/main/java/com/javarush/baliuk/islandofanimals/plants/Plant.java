package com.javarush.baliuk.islandofanimals.plants;

import com.javarush.baliuk.islandofanimals.animals.PresetData;
import com.javarush.baliuk.islandofanimals.exceptions.NoSuchAnnotationException;

import java.util.Objects;

@PresetData(weight = 50, maxSatiety = 0, maxAreaPopulation = 200)
public class Plant {

    public Plant create() {
        return new Plant();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public String toString() {
        return "Plant";
    }

    // учитывается когда животные едят
    public double getWeight() {
        return this.getPresetData().weight();
    }

    private PresetData getPresetData() {
        if (!this.getClass().isAnnotationPresent(PresetData.class)) {
            throw new NoSuchAnnotationException("No such annotation " + PresetData.class + " for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(PresetData.class);
    }
}

