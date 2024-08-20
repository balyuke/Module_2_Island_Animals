package com.javarush.baliuk.islandofanimals.animals;

// Определяет общий интерфейс для всех конкретных животных
public interface AnimalFactory {
    Animal createAnimal(Species species);
}
