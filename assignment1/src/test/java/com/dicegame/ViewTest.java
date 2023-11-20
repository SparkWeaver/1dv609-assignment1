package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Scanner mockScanner;
    private View view;

    @Before
    public void setUp() {
        mockScanner = Mockito.mock(Scanner.class);
        view = new View(mockScanner);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnNull() {
        String expected = "Jon";
        when(mockScanner.nextLine()).thenReturn(null, expected);

        String result = view.promptForPlayerName();

        assertEquals(expected, result);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnEmptyNameString() {
        when(mockScanner.nextLine()).thenReturn("", "Jon");

        String result = view.promptForPlayerName();
        assertEquals("Jon", result);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnLargeNameString() {
        String expected = "Jon the Magnificent";

        when(mockScanner.nextLine()).thenReturn("0123456789-0123456789-0123456789", expected);

        String result = view.promptForPlayerName();
        assertEquals(expected, result);
    }

    @Test
    public void testPrintTitleAndInstructions() {

        view.printTitleAndInstructions();

        String expectedOutput = System.lineSeparator() + "Welcome to Dice Blackjack!" +
                System.lineSeparator() +
                System.lineSeparator() + "The rules are simple:" +
                System.lineSeparator() + " - Enter your player name to begin." +
                System.lineSeparator() + " - You have three attempts each turn." +
                System.lineSeparator() + " - The player that gets closest to 21 wins." +
                System.lineSeparator() + " - If you get over 21 you lose." +
                System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}