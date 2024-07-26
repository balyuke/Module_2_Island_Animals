package com.javarush.baliuk.islandofanimals.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Herbivore;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;


public class Horse  extends Animal implements Herbivore {
    public Horse() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode horseNode = rootNode.get("Horse");
            this.weight = horseNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = horseNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = horseNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = horseNode.get("food_required_kg").asDouble();
            this.nameRus = horseNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
