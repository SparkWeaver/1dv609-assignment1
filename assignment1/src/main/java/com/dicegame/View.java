package com.dicegame;

import java.util.List;
import java.util.Scanner;

import com.dicegame.Rule.Action;

public class View {

    private Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    // Print the important information to the player
    public void printTitleAndInstructions() {
        System.out.println("");
        System.out.println("Welcome to Dice Blackjack!");
        System.out.println("");
        System.out.println("The rules are simple:");
        System.out.println(" - Enter your player name to begin.");
        System.out.println(" - You have three attempts each turn.");
        System.out.println(" - The player that gets closest to 21 wins.");
        System.out.println(" - If you get over 21 you lose.");
        System.out.println("");
    }

    // Prompt the player for there name
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


    public Action promptForPlayerDefaultAction(int score, int diceSum) {

        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("1. Throw again");
        System.out.println("2. Stay");
        System.out.println("3. Hold");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Action.ROLL;
            case 2:
                return Rule.Action.STAY;
            case 3:
                return Rule.Action.HOLD;
            default:
                return Rule.Action.END;
        }
    }

    public Action promptForPlayerBustAction(int score, int diceSum) {
        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("This is a BUST!");
        System.out.println("1. Continue");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Action.BUST;
            default:
                return Rule.Action.END;
        }
    }

    public Action promptForPlayerNoMoreTurnsAction(int score, int diceSum) {
        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("No more roll's");
        System.out.println("1. Continue");
        System.out.println("2. Hold");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Action.STAY;
            case 2:
                return Rule.Action.HOLD;
            default:
                return Rule.Action.END;
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
        String output;
        if(player != null) {
            output = String.format("\nThe winner is %s, with a score of %d", player.getName(), player.getScore());
        } else {
            output = "\nThe where no winner's this game.";
        }
        System.out.println(output);
    }

}
