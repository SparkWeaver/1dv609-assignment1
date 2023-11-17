package com.dicegame;

import java.util.List;

public class Game {

    private List<Player> players;
    private boolean isGameActive;

    public Game(List<Player> players) {
        this.players = players;
        isGameActive = false;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
}
