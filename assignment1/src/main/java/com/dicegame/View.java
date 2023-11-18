package com.dicegame;

import java.util.Scanner;

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
        System.out.println("[ title ]");
        System.out.println("[ instructions ]");
    }
    
    /** Methods below should be removed when done TODO */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
