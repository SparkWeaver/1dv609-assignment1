package com.dicegame;

import java.util.Random;

public class Dice {

    private Random random = new Random();
    private final int MIN_SIZE = 4;
    private final int MIN = 1;
    private final int max;
    private int value;

    public Dice() {
        this(6);
    }

    public Dice(int diceSize) {
        if (diceSize < MIN_SIZE) {
            throw new IllegalArgumentException("Dice size must be at least 4");
        }

        max = diceSize;
        value = roll();
    }

    public int value() {
        return value;
    }

    public int roll() {
        value = random.nextInt((max - MIN) + 1) + MIN;
        return value();
    }
}
