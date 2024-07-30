package com.javarush.baliuk.islandofanimals.island;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.AnimalSpecies;
import com.javarush.baliuk.islandofanimals.animals.PresetData;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.Carnivorous;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.CarnivorousFactory;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.Herbivorous;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.HerbivorousFactory;
import com.javarush.baliuk.islandofanimals.exceptions.NoSuchAnnotationException;
import com.javarush.baliuk.islandofanimals.plants.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unchecked")
public class Area {
    private final Position position;                // координаты
    private final List<Carnivorous> carnivorous;    // массив хищников
    private final List<Herbivorous> herbivorous;    // массив травоядных
    private final List<Plant> plants;               // растения

    public Area(Position position) {
        this.position = position;
        carnivorous = (List<Carnivorous>) createAnimals(new CarnivorousFactory());
        herbivorous = (List<Herbivorous>) createAnimals(new HerbivorousFactory());
        plants = createPlants();
        Collections.shuffle(carnivorous);
        Collections.shuffle(herbivorous);
    }

    public Position getPosition() {
        return position;
    }

    public List<Carnivorous> getCarnivorous() {
        return carnivorous;
    }

    public List<Herbivorous> getHerbivorous() {
        return herbivorous;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void eat() {
        // хищники едят травоядных
        for (int i = 0; i < carnivorous.size(); i++) {
            carnivorous.get(i).eat(herbivorous, this);
        }
        // хищники едят хищников
        for (int i = 0; i < carnivorous.size(); i++) {
            carnivorous.get(i).eat(carnivorous, this);
        }
        // травоядные едят растения
        for (int i = 0; i < herbivorous.size(); i++) {
            herbivorous.get(i).eat(plants, this);
        }
    }

    public void move(Area[][] areas) {
        // если для животного существует возможность переместиться,
        // то удаляем его из списка, принадлежащих текущей локации
        carnivorous.removeIf(carnivorous -> carnivorous.move(this, areas));
        herbivorous.removeIf(herbivorous -> herbivorous.move(this, areas));
    }

    public void reproduce() {
        for (int i = 0; i < carnivorous.size(); i++) {
            if (carnivorous.get(i).reproduce(this)) {
                carnivorous.add(carnivorous.get(i));
            }
        }
        for (int i = 0; i < herbivorous.size(); i++) {
            if (herbivorous.get(i).reproduce(this)) {
                herbivorous.add(herbivorous.get(i));
            }
        }
        getCarnivorous().forEach(carnivorous -> carnivorous.setReproduce(false));
        getHerbivorous().forEach(herbivorous -> herbivorous.setReproduce(false));
    }

    // умирают от перенаселения
    public void die() {
        carnivorous.removeIf(carnivorous -> carnivorous.die(this));
        herbivorous.removeIf(herbivorous -> herbivorous.die(this));
    }

    // в локацию пришло животное или создали его
    public void addAnimal(Animal animal) {
        if (animal instanceof Herbivorous herbivorous) {
            this.herbivorous.add(herbivorous);
        } else if (animal instanceof Carnivorous carnivorous) {
            this.carnivorous.add(carnivorous);
        }
    }

    public int getMaxAreaPopulation(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(PresetData.class)) {
            throw new NoSuchAnnotationException("No such annotation " + PresetData.class + " for " + clazz.getName());
        }
        PresetData presetData = clazz.getAnnotation(PresetData.class);
        return presetData.maxAreaPopulation();
    }

    public int getRandomPopulation(int maxAreaPopulation) {
        return ThreadLocalRandom.current().nextInt(maxAreaPopulation);
    }

    private List<? extends Animal> createAnimals(AnimalFactory factory) {
        List<Animal> animals = new ArrayList<>();
        AnimalSpecies[] animalSpecies = AnimalSpecies.values();
        for (AnimalSpecies animalType : animalSpecies) {
            Animal animal = factory.createAnimal(animalType);
            if (animal == null) {
                continue;
            }
            int animalCount = getRandomPopulation(getMaxAreaPopulation(animal.getClass()));
            for (int i = 0; i < animalCount; i++) {
                animals.add(factory.createAnimal(animalType));
            }
        }
        return animals;
    }

    private List<Plant> createPlants() {
        List<Plant> plants = new ArrayList<>();
        int plantCount = getRandomPopulation(getMaxAreaPopulation(Plant.class));
        for (int i = 0; i < plantCount; i++) {
            plants.add(new Plant());
        }
        return plants;
    }
}

