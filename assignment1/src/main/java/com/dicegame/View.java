package com.dicegame;

import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public String promptForPlayerName() {
        
        String name;
        while(true) {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();

            if(!name.trim().isEmpty() && name.trim().length() < 30) {
                return name;
            }
        }
    }
    
    /** Methods below should be removed when done TODO */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
