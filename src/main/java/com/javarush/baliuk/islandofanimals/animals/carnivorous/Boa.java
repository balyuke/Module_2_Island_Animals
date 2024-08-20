package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.amphibian.Frog;
import com.javarush.baliuk.islandofanimals.settings.PresetData;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.*;

import java.util.Map;

@PresetData(weight = 15, maxSatiety = 3, maxAreaPopulation = 30, speed = 1)
public class Boa extends Carnivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(
                      Fox.class, 15
                    , Rabbit.class, 20
                    , Mouse.class, 40
                    , Duck.class, 10

                    , Frog.class, 40
            );

    // Одно из условий проекта: 3. В классах травоядного и хищника можно реализовать метод покушать. Но обрати внимание, есть травоядное утка, которое ест гусеницу.
    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Boa";
    }
}