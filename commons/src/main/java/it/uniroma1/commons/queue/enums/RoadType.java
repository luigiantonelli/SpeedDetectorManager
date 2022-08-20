package it.uniroma1.commons.queue.enums;

import java.util.Random;

public enum RoadType {
    THROUGHWAY,
    HIGHWAY,
    CITYROAD;

    public static RoadType getRandomRoadType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
