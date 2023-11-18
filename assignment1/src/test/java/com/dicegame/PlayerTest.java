package com.dicegame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerTest {

    private Player player;
    private Player bot;
    private String name;

    @Before
    public void setUp() {
        name = "TED";
        player = new Player(name);
        bot = new Player(name);
    }

    
    @Test
    public void getNameShouldReturnCorrectName() {
        assertEquals(name, player.getName());
    }

    @Test
    public void getScoreShouldReturnZeroWhenPlayerIsInitialized() {
        assertEquals(0, player.getScore());
    }

    @Test
    public void rollDiceShouldUpdateScoreCorrectly() {
        Dice mockDice1 = Mockito.mock(Dice.class);
        Dice mockDice2 = Mockito.mock(Dice.class);

        when(mockDice1.roll()).thenReturn(7);
        when(mockDice2.roll()).thenReturn(7);

        List<Dice> mockedDices = Arrays.asList(mockDice1, mockDice2);

        player.throwDice(mockedDices);
        assertEquals(14, player.getScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwDiceShouldThrowExceptionForEmptyDiceArray() {
        List<Dice> emptyDices = Arrays.asList( new Dice[0] );
        player.throwDice(emptyDices);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionForEmptyName() {
        new Player("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionForNullName() {
        new Player(null);
    }
}
