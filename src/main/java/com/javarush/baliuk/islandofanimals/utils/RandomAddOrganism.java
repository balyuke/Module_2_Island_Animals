package com.javarush.baliuk.islandofanimals.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Organism;

import java.io.IOException;
import java.util.Random;

public  class RandomAddOrganism {
    public static int randomHabitants(Organism organism){
        int maxQuantityPerCell = 0;
        Random random = new Random();
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode anyOrganismNode = rootNode.get(organism.getClass().getSimpleName());
            maxQuantityPerCell = anyOrganismNode.get("max_quantity_per_cell").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return random.nextInt(maxQuantityPerCell) + 1;
    }

}
