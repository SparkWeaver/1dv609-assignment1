package com.dicegame;

import java.util.List;

public class Rule {

    public enum Decision {
        ROLL,
        STAY,
        HOLD,
        BUST
    }

    private static int scoreLimit = 21;

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
            return Decision.ROLL;
        }
    }

    public static Player determineTheWinner(List<Player> players) {
        Player winner = players.get(0);
        for (int i = 1; i < players.size(); i++) {
            Player currentPlayer = players.get(i);

            if (currentPlayer.getScore() <= scoreLimit && 
                (scoreLimit - currentPlayer.getScore() < scoreLimit - winner.getScore() || winner.getScore() > scoreLimit)) {
                winner = currentPlayer;
            }
        }
        return winner;
    }
}
