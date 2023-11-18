package com.dicegame;

import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public String promptForPlayerName() {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }
    
}
