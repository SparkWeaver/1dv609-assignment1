package com.dicegame;

import java.util.List;

import com.dicegame.Player.State;

public class Rule {

    public enum Decision {
        ROLL,
        STAY,
        HOLD,
        BUST
    }

    private static int scoreLimit = 21;

    public Rule(int scoreLimit) {
        if (scoreLimit < 2) {
            throw new IllegalArgumentException("Rule score limit cant be less than minimum dice score");
        }
        this.scoreLimit = scoreLimit;
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public Decision decideAction(int playerScore, int diceScore) {
        if (playerScore < 0 || diceScore < 0) {
            throw new IllegalArgumentException("Player score and dice value must be non-negative.");
        }

        int newScore = playerScore + diceScore;
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

    public boolean isGameOver(List<Player> players) {
        for(Player player : players) {
            if(player.getState() != State.NON_ACTIVE) {
                return false;
            }
        }
        return true;
    }

    public static Player determineWinner(List<Player> players) {
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
