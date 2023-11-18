package com.dicegame;

public class Rule {
    private final int scoreLimit;

    public Rule() {
        scoreLimit = 21;
    }

    public Decision makeDecision(int playerScore, int diceValue) {
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
