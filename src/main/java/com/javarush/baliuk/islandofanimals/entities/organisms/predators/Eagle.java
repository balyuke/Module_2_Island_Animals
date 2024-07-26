package com.javarush.baliuk.islandofanimals.entities.organisms.predators;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Predator;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;

public class Eagle extends Animal implements Predator {
    public Eagle() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode eagleNode = rootNode.get("Eagle");
            this.weight = eagleNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = eagleNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = eagleNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = eagleNode.get("food_required_kg").asDouble();
            this.nameRus = eagleNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
