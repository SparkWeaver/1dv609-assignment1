package com.dicegame;

import java.util.LinkedList;
import java.util.List;

import com.dicegame.Player.State;

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
    private View view;

    public Game(String name, View view) {
        players = new LinkedList<Player>();
        dices = new LinkedList<Dice>();

        players.add(new HumanPlayer(name, view));
        for (PlayerName playerName : PlayerName.values()) {
            Player newPlayer = new BotPlayer(playerName.name());
            if(players.contains(newPlayer)) {
                players.add(new BotPlayer(RESERVENAME));
            } else {
                players.add(newPlayer);
            }
        }

        for(int i = 0; i < 2; i++) {
            dices.add(new Dice());
        }

        this.view = view;
        isGameActive = false;
        isGameOver = false;
    }

    public void start() {
        isGameActive = true;
        while(isGameActive) {
            for(Player player : players) {

                if(player instanceof HumanPlayer && player.getState() == Player.State.ACTIVE) {
                    player.rollDice(dices);
                } else if (player.getState() == Player.State.ACTIVE) {
                    player.rollDice(dices);
                    view.printBotState(player, dices);
                }
            }
            if(allPlayersAreDone()) {
                isGameActive = false;
                isGameOver = true;
            }
        }
    }

    private boolean allPlayersAreDone() {
        for(Player player : players) {
            if(player.getState() != State.NON_ACTIVE) {
                return false;
            }
        }
        return true;
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

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
