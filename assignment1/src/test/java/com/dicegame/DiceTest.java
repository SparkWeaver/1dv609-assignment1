package com.dicegame;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() {
        dice = new Dice();
    }
    
    @Test
    public void returnNumbersFromOneToDefaultSizeOnDiceRoll() {
        final int defaultSize = 6;

        int result;
        for(int i = 0; i < 100; i++) {
            result = dice.roll();
            assertTrue(result >= 1 && result <=defaultSize);
        }
    }

    @Test
    public void rollRandomlyAssignNewValueToTheDice() {
        int previousRoll = dice.roll();
        Boolean variation = false;

        int currentRoll;
        for(int i = 0; i < 100; i++) {
            currentRoll = dice.roll();
            if(currentRoll != previousRoll) {
               variation = true;
               break; 
            }
            previousRoll = currentRoll;
        }
        assertTrue(variation);
    }

    @Test
    public void getValueShouldReturnTheSameValueAsRoll() {
        for(int i = 0; i < 100; i++) {
            assertTrue(dice.roll() == dice.getValue());
        }
    }
}
