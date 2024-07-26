package com.javarush.baliuk.islandofanimals.abstraction;

public abstract class Plant implements Organism {
    protected int weight;
    protected int maxQuantityPerCell;

    public int getWeight() {
        return weight;
    }

    public Plant(int weight, int maxQuantityPerCell) {
        this.weight = weight;
        this.maxQuantityPerCell = maxQuantityPerCell;
    }


}
