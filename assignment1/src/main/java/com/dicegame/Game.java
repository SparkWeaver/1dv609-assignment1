package com.dicegame;

import java.util.Arrays;
import java.util.List;

public class Game {

    private List<Player> players;
    private List<Dice> dices;
    private boolean isGameActive;

    public Game(List<Player> players, List<Dice> dices) {
        this.players = players;
        this.dices = dices;
        isGameActive = false;
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

    public void start() {
        isGameActive = true;
        Dice[] arrDices = dices.toArray(new Dice[0]);

        for(Player player : players) {
            player.throwDice(arrDices);
        }
    }

}
