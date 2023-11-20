package com.dicegame;

import java.util.List;

import com.dicegame.Player.State;

public class Rule {

    // Action for bot to take
    public enum Action {
        ROLL,
        STAY,
        HOLD,
        BUST,
        END
    }

    private int scoreLimit;

    // Constructor
    public Rule(int scoreLimit) {
        if (scoreLimit < 2) {
            throw new IllegalArgumentException("Rule score limit cant be less than minimum dice score");
        }
        this.scoreLimit = scoreLimit;
    }

    // Return the score limit
    public int getScoreLimit() {
        return scoreLimit;
    }

    // Decide the action of the bot
    public Action decideAction(int playerScore, int diceScore) {
        if (playerScore < 0 || diceScore < 0) {
            throw new IllegalArgumentException("Player score and dice value must be non-negative.");
        }

        int newScore = playerScore + diceScore;
        if(newScore > scoreLimit) {
            return Action.BUST;
        } else if (newScore > scoreLimit - 3 && newScore <= scoreLimit) {
            return Action.HOLD;
        } else if (newScore > scoreLimit - 10) {
            return Action.STAY;
        } else {
            return Action.ROLL;
        }
    }

    // Checks if the game is over
    public boolean isGameOver(List<Player> players) {
        if (players == null || players.size() == 0) {
            throw new IllegalArgumentException("Player list can not be empty or null.");
        }

        for(Player player : players) {
            if(player.getState() != State.NON_ACTIVE) {
                return false;
            }
        }
        return true;
    }

    // Determine the winner
    public Player determineWinner(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Player list can not be empty  or null.");
        }

        Player winner = null;
        for (Player currentPlayer : players) {
            if (currentPlayer.getScore() <= scoreLimit) {
                if (winner == null || 
                    (scoreLimit - currentPlayer.getScore() < scoreLimit - winner.getScore())) {
                    winner = currentPlayer;
                }
            }
        }

        return winner;
    }
}
