package com.dicegame;

import java.util.List;

public class Player {
    
    private final String name;
    private int score;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void throwDice(List<Dice> dices) {
        if (dices == null || dices.size() == 0) {
            throw new IllegalArgumentException("Dice array cannot be empty");
        }

        for(Dice dice : dices) {
            score += dice.roll();
        }
    }
}
