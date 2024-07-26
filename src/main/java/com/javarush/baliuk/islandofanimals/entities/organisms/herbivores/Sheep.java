package com.javarush.baliuk.islandofanimals.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Herbivore;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;

public class Sheep extends Animal implements Herbivore {
    public Sheep() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode sheepNode = rootNode.get("Sheep");
            this.weight = sheepNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = sheepNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = sheepNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = sheepNode.get("food_required_kg").asDouble();
            this.nameRus = sheepNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
