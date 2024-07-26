package com.javarush.baliuk.islandofanimals.entities.organisms.predators;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Predator;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;

public class Boa extends Animal implements Predator {
    public Boa() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode boaNode = rootNode.get("Boa");
            this.weight = boaNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = boaNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = boaNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = boaNode.get("food_required_kg").asDouble();
            this.nameRus = boaNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
