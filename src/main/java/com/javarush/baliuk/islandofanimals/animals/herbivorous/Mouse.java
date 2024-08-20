package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.settings.PresetData;

import java.util.Map;

@PresetData(weight = 0.05, maxSatiety = 0.01, maxAreaPopulation = 500, speed = 1)
public class Mouse extends Herbivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(
                      Caterpillar.class, 90
            );

    // Одно из условий проекта: 3. В классах травоядного и хищника можно реализовать метод покушать. Но обрати внимание, есть травоядное утка, которое ест гусеницу.
    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Mouse";
    }


}
