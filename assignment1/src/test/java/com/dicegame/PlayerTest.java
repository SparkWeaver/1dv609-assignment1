package com.dicegame;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    
    @Test
    public void getNameShouldReturnCorrectName() {
        String name = "Jon Goodman";
        Player player = new Player(name);
        assertEquals(name, player.getName());
    }

    @Test
    public void getScoreShouldReturnZeroWhenPlayerIsInitialized() {
        Player player = new Player("Jon");
        assertEquals(0, player.getScore());
    }

    @Test
    public void rollDiceShouldUpdateScoreCorrectly() {
        Player player = new Player("Jon");

        Dice mockDice1 = Mockito.mock(Dice.class);
        Dice mockDice2 = Mockito.mock(Dice.class);

        when(mockDice1.roll()).thenReturn(7);
        when(mockDice2.roll()).thenReturn(7);

        Dice[] mockedDices = { mockDice1, mockDice2 };

        player.throwDice(mockedDices);
        assertEquals(14, player.getScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwDiceShouldThrowExceptionForEmptyDiceArray() {
        Player player = new Player("Jon");
        Dice[] emptyDices = new Dice[0];
        player.throwDice(emptyDices);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionForEmptyName() {
        new Player("");
    }
}
