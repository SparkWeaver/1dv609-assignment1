package com.dicegame;

public class Player {
    
    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void throwDice(Dice[] dices) {
        for(Dice dice : dices) {
            score += dice.roll();
        }
    }
}
