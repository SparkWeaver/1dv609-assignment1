package com.dicegame;

import java.util.Random;

public class Dice {

    private static int DEFAULT_SIZE = 6;
    private Random random = new Random();
    private int value;

    public Dice() {
        value = random.nextInt(DEFAULT_SIZE) + 1;
    }

    public int roll() {
        value = random.nextInt(DEFAULT_SIZE) + 1;
        return value;
    }
}
