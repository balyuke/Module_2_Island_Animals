package com.javarush.baliuk.islandofanimals.animals;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PresetData {
    // Вес одного животного, кг
    double weight();

    // Сколько килограммов пищи нужно животному для полного насыщения
    double maxSatiety();

    // Максимальное количество животных этого вида на одной клетке
    int maxAreaPopulation();

    // скорость перемещения, не более чем, клеток за ход
    int speed() default 0;
}