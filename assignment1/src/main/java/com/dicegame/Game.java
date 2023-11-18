package com.dicegame;

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
    
    
}
