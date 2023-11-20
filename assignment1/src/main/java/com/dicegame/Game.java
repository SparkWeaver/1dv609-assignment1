package com.dicegame;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private enum BotName {
        Emma,
        James,
        Sophia
    }

    private final String RESERVENAME = "Jon";
    private LinkedList<Player> players;
    private LinkedList<Dice> dices;
    private boolean isGameActive;
    private boolean isGameOver;
    private View view;
    private Rule rule;

    // Initialize the game.
    public Game(LinkedList<Player> players, LinkedList<Dice> dices, View view, Rule rule) {

        this.players = players;
        this.dices = dices;
        this.view = view;
        this.rule = rule;

        // Build Bots
        for (BotName playerName : BotName.values()) {
            Player newPlayer = new BotPlayer(playerName.name());
            if(players.contains(newPlayer)) {
                players.add(new BotPlayer(RESERVENAME));
            } else {
                players.add(newPlayer);
            }
        }

        isGameActive = false;
        isGameOver = false;
    }

    // Starts the game.
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
            if(rule.isGameOver(players)) {
                isGameActive = false;
                isGameOver = true;
            }
    
        }
        view.printWinner(rule.determineWinner(players));
    }

    /**
     * Methods I might remove or keep.
     */
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
}
