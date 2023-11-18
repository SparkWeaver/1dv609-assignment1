package com.dicegame;

public class Rule {
    private final int scoreLimit;

    public Rule() {
        scoreLimit = 21;
    }

    public Decision makeDecision(int playerScore, int diceValue) {
        if (playerScore < 0 || diceValue < 0) {
            throw new IllegalArgumentException("Player score and dice value must be non-negative.");
        }

        int newScore = playerScore + diceValue;
        if(newScore > scoreLimit) {
            return Decision.BUST;
        } else if (newScore > scoreLimit - 3 && newScore <= scoreLimit) {
            return Decision.HOLD;
        } else if (newScore > scoreLimit - 10) {
            return Decision.STAY;
        } else {
            return Decision.THROW_AGAIN;
        }
    }

    public enum Decision {
        THROW_AGAIN,
        STAY,
        HOLD,
        BUST
    }
}
