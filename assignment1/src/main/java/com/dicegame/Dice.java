package com.dicegame;

import java.util.Random;

public class Dice {

    private Random random = new Random();
    private int value;
    private final int MIN = 1;
    private int max;

    public Dice() {
        this(6);
    }

    public Dice(int diceSize) {
        max = diceSize;
        value = roll();
    }

    public int getValue() {
        return value;
    }

    public int roll() {
        value = random.nextInt((max - MIN) + 1) + MIN;
        return getValue();
    }
}
