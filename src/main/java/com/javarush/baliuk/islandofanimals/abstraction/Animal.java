package com.javarush.baliuk.islandofanimals.abstraction;

import com.javarush.baliuk.islandofanimals.entities.Island;
import com.javarush.baliuk.islandofanimals.utils.RandomProbabilityGenerator;

public abstract class Animal implements  Organism{

    private static Island island = new Island();
    private static RandomProbabilityGenerator generator = new RandomProbabilityGenerator();
    private static int randomRow;
    private static int randomColumn;
    protected double weight;
    protected int maxQuantityPerCell;
    protected int movementSpeedPerTurn;
    protected double foodRequired;

    protected String nameRus;

    public Animal(int weight, int maxQuantityPerCell, int movementSpeedPerTurn, double foodRequired, String nameRus) {
        this.weight = weight;
        this.maxQuantityPerCell = maxQuantityPerCell;
        this.movementSpeedPerTurn = movementSpeedPerTurn;
        this.foodRequired = foodRequired;
        this.nameRus = nameRus;
    }
    public Animal() {
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxQuantityPerCell() {
        return maxQuantityPerCell;
    }

    public double getFoodRequired() {
        return foodRequired;
    }

    public void setFoodRequired(double foodRequired) {
        this.foodRequired = foodRequired;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

}
