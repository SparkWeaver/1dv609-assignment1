package com.dicegame;

import java.util.Scanner;

import com.dicegame.Rule.Decision;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public String promptForPlayerName() {
        
        String name;
        while(true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();

            if(name != null && !name.trim().isEmpty() && name.trim().length() < 30) {
                return name;
            }
        }
    }

    public void printTitleAndInstructions() {
        System.out.println("");
        System.out.println("[ title ]");
        System.out.println("[ instructions ]");
        System.out.println("");
    }

    public Decision promptForPlayerDecision(int score, int diceSum) {
        
        System.out.println("");
        System.out.println("Score: " + score + " Dice roll: " + diceSum);
        System.out.println("");
        System.out.println("1. Throw again");
        System.out.println("2. Stay");
        System.out.println("3. Hold");
        System.out.println("0. End game");
        System.out.println("");

        switch (scanner.nextInt()) {
            case 1:
                return Rule.Decision.THROW_AGAIN;
            case 2:
                return Rule.Decision.STAY;
            case 3:
                return Rule.Decision.HOLD;
            default:
                return Rule.Decision.BUST;
        }
    }
    
    /** Methods below should be removed when done TODO */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
