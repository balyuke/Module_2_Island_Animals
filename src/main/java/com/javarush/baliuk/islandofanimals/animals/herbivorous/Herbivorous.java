package com.javarush.baliuk.islandofanimals.animals.herbivorous;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.Gender;
import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.island.Area;

import java.util.List;

public abstract class Herbivorous extends Animal {

    @Override
    public boolean reproduce(Area area) {
        area.getLock().lock();
        try {
            List<Herbivorous> herbivorousFemales = area.getHerbivorous().stream().filter(herbivorous ->
                    herbivorous.getGender() == Gender.FEMALE && !herbivorous.isReproduce()).toList();
            if (herbivorousFemales.isEmpty()) {
                return false;
            }
            for (int i = 0; i < herbivorousFemales.size(); i++) {
                Herbivorous herbivorousFemale = herbivorousFemales.get(i);
                if (this.getGender() == Gender.MALE && !this.isReproduce() && this.equals(herbivorousFemale)) {
                    this.setReproduce(true);
                    this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                    herbivorousFemale.setReproduce(true);
                    herbivorousFemale.setSatiety(Math.max(0, herbivorousFemale.getSatiety() - herbivorousFemale.getMaxSatiety() / 10));
                    return true;
                }
            }
            return false;
        } finally {
            area.getLock().unlock();
        }
    }

//    @Override
//    public Herbivorous createAnimal(Species species) {
//        Herbivorous herbivorous;
//        switch (species) {
//            case BOAR -> herbivorous = new Boar();
//            case BUFFALO -> herbivorous = new Buffalo();
//            case CATERPILLAR -> herbivorous = new Caterpillar();
//            case DEER -> herbivorous = new Deer();
//            case DUCK -> herbivorous = new Duck();
//            case GOAT -> herbivorous = new Goat();
//            case HORSE -> herbivorous = new Horse();
//            case MOUSE -> herbivorous = new Mouse();
//            case RABBIT -> herbivorous = new Rabbit();
//            case SHEEP -> herbivorous = new Sheep();
//            default -> herbivorous = null;
//        }
//        return herbivorous;
//    }
}