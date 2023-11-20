package com.dicegame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dicegame.Player.State;

public class ViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Scanner mockScanner;
    private View view;
    private Player mockPlayer;
    private Dice mockDice1;
    private Dice mockDice2;
    private List<Dice> mockDices;

    @Before
    public void setUp() {
        mockScanner = Mockito.mock(Scanner.class);
        view = new View(mockScanner);
        System.setOut(new PrintStream(outContent));

        mockPlayer = Mockito.mock(Player.class);

        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);
        mockDices = new LinkedList<>();
        mockDices.addAll(Arrays.asList(mockDice1, mockDice2));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
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
    public void printBotStatePrintsCorrectInformationWhenActive() {
        String expectedName = "Ted";
        int expectedScore = 12;
        int expectedDiceValue = 6;

        when(mockPlayer.getName()).thenReturn(expectedName);
        when(mockPlayer.getScore()).thenReturn(expectedScore);
        when(mockPlayer.getState()).thenReturn(State.ACTIVE);
        when(mockDice1.getValue()).thenReturn(expectedDiceValue);
        when(mockDice2.getValue()).thenReturn(expectedDiceValue);

        String expectedOutput = String.format("%s: rolled %d and %d new score %d active" + System.lineSeparator(),
                expectedName, expectedDiceValue, expectedDiceValue, expectedScore);
        view.printBotState(mockPlayer, mockDices);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printBotStatePrintsCorrectInformationWhenNonActive() {
        String expectedName = "Ted";
        int expectedScore = 12;
        int expectedDiceValue = 6;

        when(mockPlayer.getName()).thenReturn(expectedName);
        when(mockPlayer.getScore()).thenReturn(expectedScore);
        when(mockPlayer.getState()).thenReturn(State.NON_ACTIVE);
        when(mockDice1.getValue()).thenReturn(expectedDiceValue);
        when(mockDice2.getValue()).thenReturn(expectedDiceValue);

        String expectedOutput = String.format("%s: rolled %d and %d new score %d non-active" + System.lineSeparator(),
                expectedName, expectedDiceValue, expectedDiceValue, expectedScore);
        view.printBotState(mockPlayer, mockDices);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printWinnerPrintsCorrectWinnerInformation() {
        String expectedName = "Ted";
        int expectedScore = 21;

        when(mockPlayer.getName()).thenReturn(expectedName);
        when(mockPlayer.getScore()).thenReturn(expectedScore);

        String expectedOutput = String.format("\nThe winner is %s, with a score of %d" + System.lineSeparator(),
                expectedName, expectedScore);
        view.printWinner(mockPlayer);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printWinnerPrintAllLoserMessage() {
        String expectedOutput = "\nThe where no winner's this game." + System.lineSeparator();
        view.printWinner(null);

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void promptForPlayerDefaultActionShouldReturnEndStateIfPlayEnterNonOptions() {
        when(mockScanner.nextLine()).thenReturn("0");

        assertEquals(Rule.Action.END, view.promptForPlayerDefaultAction(0, 0));
    }

    @Test
    public void promptForPlayerBustActionShouldReturnEndStateIfPlayEnterNonOptions() {
        when(mockScanner.nextLine()).thenReturn("0");

        assertEquals(Rule.Action.END, view.promptForPlayerBustAction(0, 0));
    }

    @Test
    public void promptForPlayerNoMoreTurnsActionShouldReturnEndStateIfPlayEnterNonOptions() {
        when(mockScanner.nextLine()).thenReturn("0");

        assertEquals(Rule.Action.END, view.promptForPlayerNoMoreTurnsAction(0, 0));
    }
}