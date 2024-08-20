package com.javarush.baliuk.islandofanimals.animals.amphibian;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.Gender;
import com.javarush.baliuk.islandofanimals.island.Area;

import java.util.List;


public abstract class Amphibian extends Animal {

    @Override
    public boolean reproduce(Area area) {
        area.getLock().lock();
        try {
            // получаем список женских особей
            List<Amphibian> amphibianFemales = area.getAmphibian().stream().filter(amphibian ->
                    amphibian.getGender() == Gender.FEMALE && !amphibian.isReproduce()).toList();
            if (amphibianFemales.isEmpty()) {
                return false;
            }
            for (int i = 0; i < amphibianFemales.size(); i++) {
                Amphibian amphibianFemale = amphibianFemales.get(i);
                if (this.getGender() == Gender.MALE && !this.isReproduce() && this.equals(amphibianFemale)) {
                    // не забываем, отмечать животных после размножения, что они уже это сделали
                    // иначе одно и тоже животное будет участвовать в размножении с разными партнерами
                    this.setReproduce(true);
                    this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
                    amphibianFemale.setReproduce(true);
                    amphibianFemale.setSatiety(Math.max(0, amphibianFemale.getSatiety() - amphibianFemale.getMaxSatiety() / 10));
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