package com.dicegame;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private enum PlayerName {
        Emma,
        James,
        Sophia,
        Michael,
        Olivia
    }

    private List<Player> players;
    private List<Dice> dices;
    private boolean isGameActive;
    private boolean isGameOver;

    public Game(String name, int numDices) {
        players = new LinkedList<Player>();
        dices = new LinkedList<Dice>();

        players.add(new Player(name));
        for (PlayerName playerName : PlayerName.values()) {
            players.add(new Player(playerName.name()));
        }

        for(int i = 0; i < numDices; i++) {
            dices.add(new Dice());
        }

        isGameActive = false;
        isGameOver = false;
    }

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
