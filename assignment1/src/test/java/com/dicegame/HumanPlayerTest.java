package com.dicegame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

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

        mockScanner = Mockito.mock(Scanner.class);
        view = new View();
        view.setScanner(mockScanner);
        
        humanPlayer = new HumanPlayer(name, view);

        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);

        mockedDices = Arrays.asList(mockDice1, mockDice2);
    }
    
    @Test
    public void testHumanDecisionToRollAgain() {
        when(mockScanner.nextInt()).thenReturn(1);
        when(mockDice1.roll()).thenReturn(1,2,3,4);
        when(mockDice2.roll()).thenReturn(1,2,3,4);

        humanPlayer.throwDice(mockedDices);

        assertEquals(Player.State.ACTIVE, humanPlayer.getState());
        assertEquals(6, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToStay() {
        when(mockScanner.nextInt()).thenReturn(2, 2);
        when(mockDice1.roll()).thenReturn(1);
        when(mockDice2.roll()).thenReturn(1);

        humanPlayer.throwDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        humanPlayer.throwDice(mockedDices);

        assertEquals(Player.State.ACTIVE, humanPlayer.getState());
        assertEquals(12, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToHold() {
        when(mockScanner.nextInt()).thenReturn(2, 3);
        when(mockDice1.roll()).thenReturn(5);
        when(mockDice2.roll()).thenReturn(5);

        humanPlayer.throwDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        humanPlayer.throwDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, humanPlayer.getState());
        assertEquals(20, humanPlayer.getScore());
    }

    @Test
    public void testHumanDecisionToBust() {
        when(mockScanner.nextInt()).thenReturn(2, 4);
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);

        humanPlayer.throwDice(mockedDices);
        humanPlayer.throwDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, humanPlayer.getState());
        assertEquals(24, humanPlayer.getScore());
    }

}
