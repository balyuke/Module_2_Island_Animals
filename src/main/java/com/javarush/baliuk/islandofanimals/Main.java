package com.javarush.baliuk.islandofanimals;

import com.javarush.baliuk.islandofanimals.multithreading.AreaThread;
import com.javarush.baliuk.islandofanimals.island.Island;
import com.javarush.baliuk.islandofanimals.island.Area;


public class Main {

    public static void main(String[] args) {

        System.err.println("Start simulation game");
        System.err.println("===============================================");

        Island island = new Island(10, 20);
        island.init();

        AreaThread[][] areaThreads = new AreaThread[10][20];
        for (int i = 0; i < island.getAreas().length; i++) {
            for (int j = 0; j < island.getAreas()[i].length; j++) {
                Area area = island.getAreas()[i][j];
                areaThreads[i][j] =  new AreaThread(area, island);
            }
        }

        int iterat = 1;
        while (true) {
            for (int i = 0; i < island.getAreas().length; i++) {
                for (int j = 0; j < island.getAreas()[i].length; j++) {
                    areaThreads[i][j].run();
                }
            }
            System.out.println("iterat="+iterat+" getAnimalsPopulation()="+island.getAnimalsPopulation());
            if (island.getAnimalsPopulation() == 0) {
                break;
            }
            iterat++;
        }

        System.err.println("===============================================");
        System.err.println("No animals. End of simulation");
    }
}