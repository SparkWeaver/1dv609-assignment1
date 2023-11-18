package com.dicegame;

import java.util.Random;

public class Dice {

    private Random random = new Random();
    private final int MIN = 1;
    private final int MAX = 6;
    private int value;

    public Dice() {
        value = roll();
    }

    public int getValue() {
        return value;
    }

    public int roll() {
        value = random.nextInt((MAX - MIN) + 1) + MIN;
        return getValue();
    }
}
