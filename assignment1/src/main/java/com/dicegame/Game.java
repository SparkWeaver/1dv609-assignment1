package com.dicegame;

import java.util.List;

public class Game {

    private List<Player> players;
    private List<Dice> dices;
    private boolean isGameActive;
    private boolean isGameOver;

    public Game(List<Player> players, List<Dice> dices) {
        this.players = players;
        this.dices = dices;
        isGameActive = false;
        isGameOver = false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Dice> getDices() {
        return dices;
    }
    
    public boolean isActive() {
        return isGameActive;
    }

    public boolean isOver() {
        return isGameOver;
    }

    public void start() {
        isGameActive = true;

        while(isGameActive) {
            for(Player player : players) {
                player.throwDice(dices);
            }
            checkForWinner();
        }

    }

    private void checkForWinner() {
        for(Player player : players) {
            if(player.getScore() >= 21) {
                isGameActive = false;
                isGameOver = true;
            }
        }
    }
}
