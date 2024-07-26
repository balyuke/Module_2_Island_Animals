package com.javarush.baliuk.islandofanimals.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.javarush.baliuk.islandofanimals.abstraction.Organism;
import com.javarush.baliuk.islandofanimals.utils.ReaderJacksonFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Island {

    private int rows;
    private int columns;

    private List<Organism>[][] locationOfSimulationIsland;

    public Island()  {
        try {
            JsonNode rootNode = ReaderJacksonFile.getInstance().mapperJS();
            JsonNode islandNode = rootNode.get("Island");
            this.rows = islandNode.get("rows").asInt();
            this.columns = islandNode.get("columns").asInt();
            locationOfSimulationIsland = new ArrayList[rows][columns];
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибки, если не удалось прочитать JSON файл
        }
    }

    public List<Organism>[][] getLocationOfSimulationIsland() {
        return locationOfSimulationIsland;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}
