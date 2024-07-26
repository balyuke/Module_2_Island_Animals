package com.javarush.baliuk.islandofanimals.entities.organisms;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Organism;
import com.javarush.baliuk.islandofanimals.abstraction.Plant;
import com.javarush.baliuk.islandofanimals.entities.Island;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Herb extends Plant {
    private static Island island = new Island();

    public Herb() {
        super(0,0);
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode herbNode = rootNode.get("Herb");
            this.weight = herbNode.get("weight_kg").asInt();
            this.maxQuantityPerCell = herbNode.get("max_quantity_per_cell").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reproduce(List<Organism>[][] result, Plant plant) {
        for (int row = 0; row < island.getRows(); row++) {
            for (int column = 0; column < island.getColumns(); column++) {
                List<Organism> innerListForHandling = new ArrayList<>(result[row][column]);
                int numberOfSameAnimalsInCell =  0;
                for (Organism organism : innerListForHandling) {
                    if (organism instanceof Plant) {
                        numberOfSameAnimalsInCell++;
                    }
                }
                if((numberOfSameAnimalsInCell+(numberOfSameAnimalsInCell/2))<=maxQuantityPerCell){
                    int countExistentPlants = numberOfSameAnimalsInCell/2;
                    for (int countNewCreation = 0;countNewCreation<countExistentPlants;countNewCreation++){
                        innerListForHandling.add(new Herb());
                    }
                } else if ((numberOfSameAnimalsInCell+(numberOfSameAnimalsInCell/2))>maxQuantityPerCell) {
                    int countExistentAnimals = maxQuantityPerCell- numberOfSameAnimalsInCell;
                    for (int countNewCreation = 0;countNewCreation<countExistentAnimals;countNewCreation++){
                        innerListForHandling.add(plant);
                    }
                }
                result[row][column]= innerListForHandling;
            }
        }
    }
}
