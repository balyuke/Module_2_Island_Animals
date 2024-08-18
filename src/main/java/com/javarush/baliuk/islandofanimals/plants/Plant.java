package com.javarush.baliuk.islandofanimals.plants;

import com.javarush.baliuk.islandofanimals.settings.PresetData;
import com.javarush.baliuk.islandofanimals.exceptions.NoSuchAnnotationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@PresetData(weight = 1, maxAreaPopulation = 200)
public class Plant {
    private static final Logger LOG = LoggerFactory.getLogger(Plant.class);

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
            LOG.error("No such annotation {} for {}", PresetData.class, this.getClass().getName());
            throw new NoSuchAnnotationException("No such annotation " + PresetData.class + " for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(PresetData.class);
    }
}

