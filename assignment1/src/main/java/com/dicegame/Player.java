package com.dicegame;

import java.util.List;
import java.util.Objects;

public class Player {

    public enum State {
        ACTIVE,
        NON_ACTIVE
    }
    
    protected Rule rule;
    protected State state;
    private final String name;
    protected int score;

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        rule = new Rule(21);
        this.name = name;
        state = State.ACTIVE;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public State getState() {
        return state;
    }

    public void rollDice(List<Dice> dices) {
        if (dices == null || dices.size() == 0) {
            throw new IllegalArgumentException("Dice array cannot be empty");
        }

        for(Dice dice : dices) {
            score += dice.roll();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return Objects.equals(name, player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
