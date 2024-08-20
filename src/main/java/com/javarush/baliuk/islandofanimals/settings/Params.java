package com.javarush.baliuk.islandofanimals.settings;

public final class Params {

    public static final int LENGTH = 10; //10;
    public static final int WIDTH = 20;  //20;

    // длительность такта симуляции
    public static final int DELAY_SEC = 3000;
    public static final int DELAY_MILLIS = 3;

    public static final int CORE_POOL_SIZE = 1;
    public static final int ITERATION = 1;

    public static final int INFO_INITIAL_DELAY = 1;
    public static final int INFO_DELAY = 2;

    public static final int AREA_INITIAL_DELAY = 2;
    public static final int AREA_DELAY = 1;

    public static final int PLANT_INITIAL_DELAY = 3;
    public static final int PLANT_DELAY = 3;

    public static final int MAX_ITERATION = 50;

    private static final Params INSTANCE = new Params();

    private Params() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Params getInstance() {
        return INSTANCE;
    }

}
