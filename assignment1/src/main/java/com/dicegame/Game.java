package com.dicegame;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private enum PlayerName {
        Emma,
        James,
        Sophia
    }

    private final String RESERVENAME = "Jon";
    private List<Player> players;
    private List<Dice> dices;
    private boolean isGameActive;
    private boolean isGameOver;

    public Game(String name) {
        players = new LinkedList<Player>();
        dices = new LinkedList<Dice>();

        players.add(new Player(name));
        for (PlayerName playerName : PlayerName.values()) {
            Player newPlayer = new Player(playerName.name());
            if(players.contains(newPlayer)) {
                players.add(new Player(RESERVENAME));
            } else {
                players.add(newPlayer);
            }
        }

        for(int i = 0; i < 1; i++) {
            dices.add(new Dice());
        }

        isGameActive = false;
        isGameOver = false;
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

    /** Below should be removed later on */

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

    public void setDice(List<Dice> dices) {
        this.dices = dices;
    }
}
