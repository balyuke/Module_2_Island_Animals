package com.javarush.baliuk.islandofanimals.entities.organisms.herbivores;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Animal;
import com.javarush.baliuk.islandofanimals.abstraction.Herbivore;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;

public class Boar extends Animal implements Herbivore {
    public Boar() {
        super(0, 0, 0, 0.0, "");
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode boarNode = rootNode.get("Boar");
            this.weight = boarNode.get("weight_kg").asDouble();
            this.maxQuantityPerCell = boarNode.get("max_quantity_per_cell").asInt();
            this.movementSpeedPerTurn = boarNode.get("movement_speed_per_turn").asInt();
            this.foodRequired = boarNode.get("food_required_kg").asDouble();
            this.nameRus = boarNode.get("name_rus").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
