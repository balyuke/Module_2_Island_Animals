package com.javarush.baliuk.islandofanimals.animals.carnivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.Gender;
import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.island.Area;

import java.util.List;

public abstract class Carnivorous extends Animal {

    @Override
    public boolean reproduce(Area area) {
        area.getLock().lock();
        try {
            List<Carnivorous> carnivorousFemales = area.getCarnivorous().stream().filter(carnivorous ->
                    carnivorous.getGender() == Gender.FEMALE && !carnivorous.isReproduce()).toList();
            if (carnivorousFemales.isEmpty()) {
                return false;
            }
            for (int i = 0; i < carnivorousFemales.size(); i++) {
                Carnivorous carnivorousFemale = carnivorousFemales.get(i);
                if (this.getGender() == Gender.MALE && !this.isReproduce() && this.equals(carnivorousFemale)) {
                    this.setReproduce(true);
                    this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                    carnivorousFemale.setReproduce(true);
                    carnivorousFemale.setSatiety(Math.max(0, carnivorousFemale.getSatiety() - carnivorousFemale.getMaxSatiety() / 10));
                    return true;
                }
            }
            return false;
        } finally {
            area.getLock().unlock();
        }
    }

//    @Override
//    public Carnivorous createAnimal(Species species) {
//        Carnivorous carnivorous;
//        switch (species) {
//            case BEAR -> carnivorous = new Bear();
//            case BOA -> carnivorous = new Boa();
//            case EAGLE -> carnivorous = new Eagle();
//            case FOX -> carnivorous = new Fox();
//            case WOLF -> carnivorous = new Wolf();
//            default -> carnivorous = null;
//        }
//        return carnivorous;
//    }
}
