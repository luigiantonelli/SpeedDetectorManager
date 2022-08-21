package it.uniroma1.commons.queue.enums;

import java.util.Random;

public enum FuelType {
    GASOLINE,
    DIESEL,
    LPG,
    ETHANOL,
    HYBRID;

    public static FuelType getRandomFuelType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
