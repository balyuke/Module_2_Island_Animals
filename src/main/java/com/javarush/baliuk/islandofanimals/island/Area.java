package com.javarush.baliuk.islandofanimals.island;

import com.javarush.baliuk.islandofanimals.animals.Animal;
import com.javarush.baliuk.islandofanimals.animals.AnimalFactory;
import com.javarush.baliuk.islandofanimals.animals.Species;
import com.javarush.baliuk.islandofanimals.settings.PresetData;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.Carnivorous;
import com.javarush.baliuk.islandofanimals.animals.carnivorous.CarnivorousFactory;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.Herbivorous;
import com.javarush.baliuk.islandofanimals.animals.herbivorous.HerbivorousFactory;
import com.javarush.baliuk.islandofanimals.animals.amphibian.Amphibian;
import com.javarush.baliuk.islandofanimals.animals.amphibian.AmphibianFactory;

import com.javarush.baliuk.islandofanimals.exceptions.NoSuchAnnotationException;
import com.javarush.baliuk.islandofanimals.plants.Plant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unchecked")
// класс Локация острова
public class Area {
    private static final Logger LOG = LoggerFactory.getLogger(Area.class);

    private final Position position;                // координаты
    private final List<Carnivorous> carnivorous;    // массив плотоядных
    private final List<Herbivorous> herbivorous;    // массив травоядных
    private final List<Amphibian> amphibian;    // массив травоядных
    private final List<Plant> plants;               // растения

    private final Lock lock = new ReentrantLock(true);  // блокировка доступа к локации

    public Area(Position position) {
        this.position = position;
        carnivorous = (List<Carnivorous>) createAnimals(new CarnivorousFactory());
        herbivorous = (List<Herbivorous>) createAnimals(new HerbivorousFactory());
        amphibian = (List<Amphibian>) createAnimals(new AmphibianFactory());
        plants = createPlants();
        // перемешиваем плотоядных в списке
        Collections.shuffle(carnivorous);
        // перемешиваем травоядных в списке
        Collections.shuffle(herbivorous);
        // перемешиваем земноводных в списке
        Collections.shuffle(amphibian);
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

    public List<Amphibian> getAmphibian() {
        return amphibian;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    // на каждой итерации животные питаются
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

        // хищники едят земноводных
        for (int i = 0; i < carnivorous.size(); i++) {
            carnivorous.get(i).eat(amphibian, this);
        }
    }

    // на каждой итерации животные перемещаются в соседние локации
    public void move(Area[][] areas) {
        // если для животного существует возможность переместиться,
        // то удаляем его из списка животных текущей локации
        carnivorous.removeIf(carnivorous -> carnivorous.move(this, areas));
        herbivorous.removeIf(herbivorous -> herbivorous.move(this, areas));
        amphibian.removeIf(amphibian -> amphibian.move(this, areas));
    }

    // на каждой итерации животные размножаются\плодятся
    // добавляем в списки Плотоядных и Травоядных животных, для каждой пары
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
        for (int i = 0; i < amphibian.size(); i++) {
            if (amphibian.get(i).reproduce(this)) {
                amphibian.add(amphibian.get(i));
            }
        }
        getCarnivorous().forEach(carnivorous -> carnivorous.setReproduce(false));
        getHerbivorous().forEach(herbivorous -> herbivorous.setReproduce(false));
        getAmphibian().forEach(amphibian -> amphibian.setReproduce(false));
    }

    // на каждой итерации животные могут умереть от истощения
    public void die() {
        carnivorous.removeIf(carnivorous -> carnivorous.die(this));
        herbivorous.removeIf(herbivorous -> herbivorous.die(this));
        amphibian.removeIf(amphibian -> amphibian.die(this));
    }

    // в локацию пришло животное или создали его
    public void addAnimal(Animal animal) {
        if (animal instanceof Herbivorous herbivorous) {
            this.herbivorous.add(herbivorous);
        } else if (animal instanceof Carnivorous carnivorous) {
            this.carnivorous.add(carnivorous);
        }  else if (animal instanceof Amphibian amphibian) {
            this.amphibian.add(amphibian);
        }
    }

    // вызывается из getRandomPopulation() <- createAnimal()
    public int getMaxAreaPopulation(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(PresetData.class)) {
            LOG.error("No such annotation {} for {}", PresetData.class, clazz.getName());
            throw new NoSuchAnnotationException("No such annotation " + PresetData.class + " for " + clazz.getName());
        }
        PresetData presetData = clazz.getAnnotation(PresetData.class);
        return presetData.maxAreaPopulation();
    }

    // используется в createAninmal()
    public int getRandomPopulation(int maxAreaPopulation) {
        return ThreadLocalRandom.current().nextInt(maxAreaPopulation);
    }

    // создается и возвращается список Травоядных и Плотоядных
    private List<? extends Animal> createAnimals(AnimalFactory factory) {
        List<Animal> animals = new ArrayList<>();
        Species[] species = Species.values();
        for (Species animalType : species) {
            // создаем животное, чтобы считать свойство maxAreaPopulation,
            // сколько можно создать животных данного вида в одной клетке
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

    // Трава растет на каждой итерации
    public void grow() {
        int currentPlantsPopulation = getPlants().size();
        int maxPopulation = getMaxAreaPopulation(Plant.class);
        int localPlantsPopulation = getRandomPopulation(maxPopulation);
        if (currentPlantsPopulation + localPlantsPopulation > maxPopulation) {
            localPlantsPopulation = maxPopulation - currentPlantsPopulation;
        }
        for (int i = 0; i < localPlantsPopulation; i++) {
            getPlants().add(new Plant());
        }
    }

    public Lock getLock() {
        return lock;
    }


}

