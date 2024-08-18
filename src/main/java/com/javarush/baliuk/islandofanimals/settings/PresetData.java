package com.javarush.baliuk.islandofanimals.settings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// Предустановки
public @interface PresetData {
    // Вес одного животного, кг
    double weight();

    // Сколько килограммов пищи нужно животному для полного насыщения
    double maxSatiety() default 0;

    // Максимальное количество животных этого вида на одной клетке
    int maxAreaPopulation();

    // скорость перемещения, не более чем, клеток за ход
    int speed() default 0;
}