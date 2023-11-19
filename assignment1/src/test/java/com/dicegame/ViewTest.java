package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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
    private View view;

    @Before
    public void setUp() {
        view = new View();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnNull() {
        String expected = "Jon";
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn(null, expected);
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals(expected, result);
    }
    
    @Test
    public void promptForPlayerNameDoesNotReturnEmptyNameString() {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("", "Jon");
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals("Jon", result);
    }

    @Test
    public void promptForPlayerNameDoesNotReturnLargeNameString() {
        String expected = "Jon the Magnificent";
        Scanner mockScanner = Mockito.mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("0123456789-0123456789-0123456789", expected);
        view.setScanner(mockScanner);

        String result = view.promptForPlayerName();
        assertEquals(expected, result);
    }

    @Test
    public void testPrintTitleAndInstructions() {
        View view = new View();
        view.printTitleAndInstructions();

        String expectedOutput = System.lineSeparator() + 
        "[ title ]" + System.lineSeparator() + 
        "[ instructions ]" + System.lineSeparator() +
        System.lineSeparator();
        
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintWinner() {
        Player winner = Mockito.mock(Player.class);
        when(winner.getName()).thenReturn("Emma");
        when(winner.getScore()).thenReturn(21);

        String expectedOutput = "The winner is Emma, with a score of 21" + System.lineSeparator();
        view.printWinner(winner);

        assertEquals(expectedOutput, outContent.toString());
    }
}
