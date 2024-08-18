package com.javarush.baliuk.islandofanimals.island;

import java.util.Arrays;

// класс Остров
public class Island {
    private final int length;       // длина острова
    private final int width;        // ширина острова
    private final Area[][] areas;   // матрица локаций острова

    @Override
    public String toString() {
        return String.format("Island created with length = %s, width = %s", length, width);
    }

    public Island(int length, int width) {
        this.length = length;
        this.width = width;
        this.areas = new Area[length][width];
    }

    public Area[][] getAreas() {
        return areas;
    }

    public void init() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                areas[i][j] = new Area(new Position(i, j));
            }
        }
    }

    // Количесвто\популяция хищников
    public int getCarnivorousPopulation() {
        int number = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                number += areas[i][j].getCarnivorous().size();
            }
        }
        return number;
    }

    // Популяция травоядных
    public int getHerbivorousPopulation() {
        int number = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                number += areas[i][j].getHerbivorous().size();
            }
        }
        return number;
    }

    // Популяция всех животных. Если 0, то игра закончена
    public int getAnimalsPopulation() {
        return getCarnivorousPopulation() + getHerbivorousPopulation();
    }
}

