package com.dicegame;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private enum BotName {
        Emma,
        James,
        Sophia,
        Jon
    }

    private LinkedList<Player> players;
    private LinkedList<Dice> dices;
    private View view;
    private Rule rule;

    // Initialize the game.
    public Game(LinkedList<Player> players, LinkedList<Dice> dices, View view, Rule rule) {

        this.players = players;
        this.dices = dices;
        this.view = view;
        this.rule = rule;

        for (BotName playerName : BotName.values()) {
            Player newPlayer = new BotPlayer(playerName.name());
            if(!players.contains(newPlayer)) {
                players.add(newPlayer);
            }
        }
    }

    // Starts the game.
    public void start() {
        while(true) {
            for(Player player : players) {

                if(player instanceof HumanPlayer && player.getState() == Player.State.ACTIVE) {
                    player.rollDice(dices);
                } else if (player.getState() == Player.State.ACTIVE) {
                    player.rollDice(dices);
                    view.printBotState(player, dices);
                }
            }
            if(rule.isGameOver(players)) {
                break;
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
}
