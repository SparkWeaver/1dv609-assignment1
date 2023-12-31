package com.dicegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private HumanPlayer humanPlayer;
    private String name;
    private View view;
    private Dice mockDice1;
    private Dice mockDice2;
    private List<Dice> mockedDices;
    private Scanner mockScanner;

    @Before
    public void setUp() {
        name = "TED";

        System.setOut(new PrintStream(outContent));
        mockScanner = Mockito.mock(Scanner.class);
        view = new View(mockScanner);

        humanPlayer = new HumanPlayer(name, view);

        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);

        mockedDices = Arrays.asList(mockDice1, mockDice2);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testHumanDecisionToRollAgain() {
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockDice1.roll()).thenReturn(1,2,3,4);
        when(mockDice2.roll()).thenReturn(1,2,3,4);

        humanPlayer.rollDice(mockedDices);

        assertEquals(Player.State.ACTIVE, humanPlayer.getState());
        assertEquals(6, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToStay() {
        when(mockScanner.nextInt()).thenReturn(2, 2);
        when(mockDice1.roll()).thenReturn(1);
        when(mockDice2.roll()).thenReturn(1);

        humanPlayer.rollDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        humanPlayer.rollDice(mockedDices);

        assertEquals(Player.State.ACTIVE, humanPlayer.getState());
        assertEquals(12, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToHold() {
        when(mockScanner.nextInt()).thenReturn(2, 3);
        when(mockDice1.roll()).thenReturn(5);
        when(mockDice2.roll()).thenReturn(5);

        humanPlayer.rollDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        humanPlayer.rollDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, humanPlayer.getState());
        assertEquals(20, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToBust() {
        when(mockScanner.nextInt()).thenReturn(1, 1, 1);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);

        humanPlayer.rollDice(mockedDices);
        humanPlayer.rollDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, humanPlayer.getState());
        assertEquals(24, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionDuringNoMoreTurnsOutput() {
        when(mockScanner.nextInt()).thenReturn(1, 1, 2);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);

        humanPlayer.rollDice(mockedDices);

        String expectedOutput = createDefaultDecisionOutput() +
                            createDefaultDecisionOutput() +
                            createNoMoreTurnsDecisionOutput();

        assertEquals(expectedOutput, outContent.toString());
        assertEquals(Player.State.NON_ACTIVE, humanPlayer.getState());
    }

    private String createDefaultDecisionOutput() {
        return System.lineSeparator() +
                "Score: 0 Dice roll: 12" + System.lineSeparator() +
                System.lineSeparator() + "1. Throw again" +
                System.lineSeparator() + "2. Stay" +
                System.lineSeparator() + "3. Hold" +
                System.lineSeparator() + System.lineSeparator();
    }

    private String createNoMoreTurnsDecisionOutput() {
        return System.lineSeparator() +
                "Score: 0 Dice roll: 12" + System.lineSeparator() +
                System.lineSeparator() + "No more roll's" +
                System.lineSeparator() + "1. Continue" +
                System.lineSeparator() + "2. Hold" +
                System.lineSeparator() + System.lineSeparator();
    }

}
