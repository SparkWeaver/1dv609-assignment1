package com.dicegame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * App is excluded from the testing due to it aligning more towards integration testing
 * which I do not consider unit testing.
 */
public class App {

    public static void main(String[] args) {

        // User interaction
        View view = new View(new Scanner(System.in));
        view.printTitleAndInstructions();
        String name = view.promptForPlayerName();

        // Player and Dice
        LinkedList<Player> players = new LinkedList<>();
        players.add(new HumanPlayer(name, view));

        LinkedList<Dice> dices = new LinkedList<>();
        dices.addAll(Arrays.asList(new Dice(), new Dice()));

        // Rules was meant to extend to different type of rule
        // had to accept that I dont have the time.
        Rule rule = new Rule(21);

        Game game = new Game(players, dices, view, rule);
        game.start();
    }
}
