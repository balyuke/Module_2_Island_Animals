package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.amphibian.*;
import com.javarush.baliuk.islandofanimals.settings.PresetData;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.*;

import java.util.Map;

@PresetData(weight = 6, maxSatiety = 1, maxAreaPopulation = 20, speed = 3)
public class Eagle extends Carnivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(
                      Fox.class, 10
                    , Rabbit.class, 90
                    , Mouse.class, 90
                    , Duck.class, 80
            );

    // Одно из условий проекта: 3. В классах травоядного и хищника можно реализовать метод покушать. Но обрати внимание, есть травоядное утка, которое ест гусеницу.
    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Eagle";
    }
}
