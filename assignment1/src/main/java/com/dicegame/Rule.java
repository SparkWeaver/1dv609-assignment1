package com.dicegame;

public class Rule {
    private final int scoreLimit;

    public Rule() {
        scoreLimit = 21;
    }

    public Decision makeDecision(int playerScore, int diceValue) {
        if(playerScore + diceValue < scoreLimit) {
            return Decision.THROW_AGAIN;
        }
        return Decision.BUST;
    }

    public enum Decision {
        THROW_AGAIN,
        STAY,
        BUST
    }
}
