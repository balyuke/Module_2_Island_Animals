package com.javarush.baliuk.islandofanimals.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Herbivore;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;

public class Rabbit extends Animal implements Herbivore {
    public Rabbit() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode rabbitNode = rootNode.get("Rabbit");
            this.weight = rabbitNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = rabbitNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = rabbitNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = rabbitNode.get("food_required_kg").asDouble();
            this.nameRus = rabbitNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
