package com.javarush.baliuk.islandofanimals;


import com.javarush.baliuk.islandofanimals.abstraction.Organism;
import com.javarush.baliuk.islandofanimals.utils.InitializingIslandLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        InitializingIslandLocation islandLocation = new InitializingIslandLocation();
        List<Organism>[][] island = islandLocation.initGameFields();
    }
}