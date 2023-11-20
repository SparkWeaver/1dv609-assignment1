package com.dicegame;

import java.util.List;
import java.util.Scanner;

import com.dicegame.Rule.Decision;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public void printTitleAndInstructions() {
        System.out.println("");
        System.out.println("[ title ]");
        System.out.println("[ instructions ]");
        System.out.println("");
    }

    public String promptForPlayerName() {
        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();

            if (name != null && !name.trim().isEmpty() && name.trim().length() < 30) {
                return name;
            }
        }
    }

    public Decision promptForPlayerDefaultDecision(int score, int diceSum) {

        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("1. Throw again");
        System.out.println("2. Stay");
        System.out.println("3. Hold");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Decision.ROLL;
            case 2:
                return Rule.Decision.STAY;
            case 3:
                return Rule.Decision.HOLD;
            default:
                return Rule.Decision.BUST;
        }
    }

    public Decision promptForPlayerBustDecision(int score, int diceSum) {
        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("This is a BUST!");
        System.out.println("1. Continue");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Decision.BUST;
            default:
                return Rule.Decision.BUST;
        }
    }

    public Decision promptForPlayerNoMoreTurnsDecision(int score, int diceSum) {
        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("No more roll's");
        System.out.println("1. Continue");
        System.out.println("2. Hold");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Decision.STAY;
            case 2:
                return Rule.Decision.HOLD;
            default:
                return Rule.Decision.STAY;
        }
    }

    public void printBotState(Player player, List<Dice> dices) {

        String output = String.format("%s: rolled %d and %d new score %d", player.getName(), dices.get(0).getValue(),dices.get(1).getValue(), player.getScore());
        if (player.getState() == Player.State.ACTIVE) {
            output += " active";
        } else {
            output += " non-active";
        }
        System.out.println(output);
	}

    public void printWinner(Player player) {
        String output = String.format("\nThe winner is %s, with a score of %d", player.getName(), player.getScore());
        System.out.println(output);
    }

    /** Methods below should be removed when done */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
