package com.dicegame;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlayerTest {
    
    @Test
    public void getNameReturnCorrectName() {
        String name = "Jon Goodman";
        Player player = new Player(name);
        assertEquals(name, player.getName());
    }

    @Test
    public void getScoreReturnZeroWhenPlayerIsInitialized() {
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

    @Test
    public void throwDiceThrowExceptionForEmptyDiceArray() {
        Player player = new Player("Jon");
        Dice[] emptyDices = new Dice[0];
        try {
            player.throwDice(emptyDices);
            fail();
        } catch (IllegalArgumentException e) {
            //Passes
        }
    }

    @Test
    public void constructorThrowExceptionIFNameIsToShort() {
        try {
            new Player("");
            fail();
        } catch (IllegalArgumentException e) {
            //Passes
        }
    }
}
