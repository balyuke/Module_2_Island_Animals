package com.javarush.baliuk.islandofanimals.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.baliuk.islandofanimals.abstraction.Organism;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RandomProbabilityGenerator {
    private String filePath = "src/main/resources/probabilityTable.json";
    private ObjectMapper objectMapper = new ObjectMapper();
public boolean whetherEaten(Organism whoEats, Organism canBeEaten){
    try {
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));
        String whoEatsName = whoEats.getClass().getSimpleName();
        String canBeEatenName = canBeEaten.getClass().getSimpleName();
        JsonNode canBeEatenNode = jsonNode.get("predatorPreyMatrix").get(whoEatsName).get(canBeEatenName);
        if (canBeEatenNode.isNumber()) {
            int canBeEatenValue = canBeEatenNode.asInt();
            int randomPossibility = new Random().nextInt(0,101);
            return randomPossibility>(100-canBeEatenValue);
        } else {
            System.out.println("The value for a potentially eaten organism is not a number.");
        }
    } catch (
            IOException e) {
        e.printStackTrace();
    }
    return false;
}
        
}
