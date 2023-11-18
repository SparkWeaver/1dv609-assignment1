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

        Dice mockDice1 = Mockito.mock(Dice.class);
        Dice mockDice2 = Mockito.mock(Dice.class);

        when(mockDice1.roll()).thenReturn(5);
        when(mockDice2.roll()).thenReturn(5);

        mockedDices = Arrays.asList(mockDice1, mockDice2);
    }

    
    @Test
    public void testBotDecisionToRollAgain() {
        botPlayer.throwDice(mockedDices);
        assertEquals(botPlayer.State.ACTIVE, botPlayer.getState());
        assertEquals(10, botPlayer.getScore());
    }
}
