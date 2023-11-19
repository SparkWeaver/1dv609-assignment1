package com.dicegame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BotPlayerTest {

    private BotPlayer botPlayer;
    private String name;
    private Dice mockDice1;
    private Dice mockDice2;
    private List<Dice> mockedDices;


    @Before
    public void setUp() {
        name = "TED";
        botPlayer = new BotPlayer(name);

        mockDice1 = Mockito.mock(Dice.class);
        mockDice2 = Mockito.mock(Dice.class);

        mockedDices = Arrays.asList(mockDice1, mockDice2);
    }
    
    @Test
    public void testBotDecisionToRollAgain() {
        when(mockDice1.roll()).thenReturn(1,2,3,4);
        when(mockDice2.roll()).thenReturn(1,2,3,4);

        botPlayer.rollDice(mockedDices);

        assertEquals(Player.State.ACTIVE, botPlayer.getState());
        assertEquals(6, botPlayer.getScore());
    }

    @Test
    public void testBotDecisionToStay() {
        when(mockDice1.roll()).thenReturn(1);
        when(mockDice2.roll()).thenReturn(1);

        botPlayer.rollDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        botPlayer.rollDice(mockedDices);

        assertEquals(Player.State.ACTIVE, botPlayer.getState());
        assertEquals(12, botPlayer.getScore());
    }

    @Test
    public void testBotDecisionToHold() {
        when(mockDice1.roll()).thenReturn(5);
        when(mockDice2.roll()).thenReturn(5);

        botPlayer.rollDice(mockedDices);

        when(mockDice1.roll()).thenReturn(5, 6, 7);
        when(mockDice2.roll()).thenReturn(5, 6, 7);

        botPlayer.rollDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, botPlayer.getState());
        assertEquals(20, botPlayer.getScore());
    }

    @Test
    public void testBotDecisionToBust() {
        when(mockDice1.roll()).thenReturn(6);
        when(mockDice2.roll()).thenReturn(6);

        botPlayer.rollDice(mockedDices);
        botPlayer.rollDice(mockedDices);

        assertEquals(Player.State.NON_ACTIVE, botPlayer.getState());
        assertEquals(24, botPlayer.getScore());
    }
}
