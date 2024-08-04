package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.PresetData;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.*;

import java.util.Map;

@PresetData(weight = 500, maxSatiety = 80, maxAreaPopulation = 5, speed = 2)
public class Bear extends Carnivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(
                      Boa.class, 80
                    , Horse.class, 40
                    , Deer.class, 80
                    , Rabbit.class, 80
                    , Mouse.class, 90
                    , Goat.class, 70
                    , Sheep.class, 70
                    , Boar.class, 50
                    , Buffalo.class, 20
                    , Duck.class, 70)
            ;

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Bear";
    }
}